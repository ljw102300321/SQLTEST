package west_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Update {

    //更改商品需求量
    public static void updateNeed(int oid,String pname,int num,String func) throws SQLException, ClassNotFoundException {
        //如果func不符合else if语句内容的任何一项，默认是增加
        String pid="x";
        double price=0;
        String sql1="select product_id,Product_Price from product where product_Name=?";
        String sql2="update product_order set quantity=quantity+? where Order_id=? and Product_id=?";//默认是增加
        String sql3="update order1 set Order_price=Order_price+? where Order_id=?";//默认是增加
        if(func.equals("decrease")){
            sql2="update product_order set quantity=quantity-? where Order_id=? and Product_id=?";
            sql3="update order1 set Order_price=Order_price-? where Order_id=?";//默认是增加
        }
        Connection conn= GetConn.getConnection();
        PreparedStatement pstmt1=conn.prepareStatement(sql1);
        pstmt1.setString(1,pname);
        try(ResultSet rs=pstmt1.executeQuery()) {
            conn.setAutoCommit(false);
            if(rs.next()) {
                pid= rs.getString("product_id");
                price=rs.getDouble("product_price");
            }
            updateOrderprice(sql3,price,num,oid);//执行sql3
            updateQuantity(sql2,num,oid,pid);//执行sql2
            if(func.equals("decrease")) updateStock(pname,num,"increase");//更改库存
            else updateStock(pname,num,"decrease");

            conn.commit();
        } catch (Exception e){
            conn.rollback();
            System.out.println("更新商品需求失败");
            throw new MyfunctionException();
        }
        finally {
            pstmt1.close();
            conn.close();
        }
    }

    public static void updateQuantity(String sql,int num,int oid,String pid) throws SQLException, ClassNotFoundException {
        //String sql2="update product_order set quantity=quantity+? where Order_id=? and Product_id=-?";//默认是增加
        Connection conn= GetConn.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,num);
        pstmt.setInt(2,oid);
        pstmt.setString(3,pid);
        try {
            pstmt.executeUpdate();
        }
        catch (Exception e){
            throw new MyfunctionException();
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }
    public static void updateOrderprice(String sql,double price,int num,int oid) throws SQLException, ClassNotFoundException {
        //String sql3="update order1 set Order_price=Order_price+? where Order_id=?";//默认是增加
        Connection conn= GetConn.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setDouble(1,price*num);
        pstmt.setInt(2,oid);
        try {
            pstmt.executeUpdate();
        }
        catch (Exception e){
            throw new MyfunctionException();
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }


    public static void updateStock(String pname,int num,String func) throws SQLException, ClassNotFoundException {//pname指要改变的商品名，num表示要添加或减少的库存数
        //默认增加
        String pid="x";
        Connection conn= GetConn.getConnection();
        String sql1="select Product_id from product where Product_Name=?";
        String sql2="update product set Product_Stock=Product_Stock+? where Product_id=?";
        if(func.equals("decrease"))
        sql2="update product set Product_Stock=Product_Stock-? where Product_id=?";
        else if(func.equals("set"))
        sql2="update product set Product_Stock=? where Product_id=?";
        PreparedStatement pstmt1=conn.prepareStatement(sql1);
        pstmt1.setString(1,pname);
        try(ResultSet rs=pstmt1.executeQuery()) {
            conn.setAutoCommit(false);
            if(rs.next()) {
                pid= rs.getString("product_id");
            }
            updateProductstock(sql2,num,pid);//执行sql2
            conn.commit();
            System.out.println("更新商品库存成功");
        }
        catch (Exception e){
            conn.rollback();
            System.out.println("更新商品库存失败");
            throw new MyfunctionException();
        }
        finally {
            pstmt1.close();
            conn.close();
        }
    }

    public static void updateProductstock(String sql,int num,String pid) throws SQLException, ClassNotFoundException {
        //String sql2="update product set Product_Stock=Product_Stock+? where Product_id=?";
        Connection conn= GetConn.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,num);
        pstmt.setString(2,pid);
        try {
            pstmt.executeUpdate();
        }
        catch (Exception e){
            throw new MyfunctionException();
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }
}
