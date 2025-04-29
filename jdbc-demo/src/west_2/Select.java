package west_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.*;
public class Select {

    public static HashMap<Integer,Integer> selectProductorder(String findItem, String x) throws SQLException, ClassNotFoundException{
        if(findItem.equals("订单编号")){
            if(!x.matches("\\d+")){
                System.out.println("订单编号必须为数字");
                return new HashMap<>();
            }
        }
        HashMap<Integer,Integer> hm=new HashMap<>();
        //如果x不是数字,由上面步骤规范x必定是商品名
        if(!x.matches("\\d+")) {
            x=changeNametoid(x);
        }
        if(x.equals("-1")){
            return new HashMap<>();
        }

        Connection conn= GetConn.getConnection();
        String sql="select * from product_order where Order_id=?";//默认查询订单编号的细节
        if (findItem.equals("商品编号")) sql="select * from product_order where Product_id=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,x);
        try (ResultSet rs=pstmt.executeQuery()){

            while (rs.next()) {
                int pid = rs.getInt("Product_id");
                int oid= rs.getInt("Order_id");
                int num= rs.getInt("quantity");
                if (findItem.equals("商品编号")) hm.put(oid,num);
                else hm.put(pid,num);
            }
        }
        catch (Exception e){
            throw new MyfunctionException();
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return hm;
    }

    public static Order selectOrder(String OrderId) throws SQLException, ClassNotFoundException{
        //订单不存在->Order全部为null
        //订单存在但是没有内容->Order的hm为null
        Order item=new Order();
        Connection conn1= GetConn.getConnection();
        String sql="select * from order1 where Order_id=?";
        PreparedStatement pstmt=conn1.prepareStatement(sql);
        pstmt.setString(1,OrderId);
        try (ResultSet rs=pstmt.executeQuery()){
            if (rs.next()) {
                int id = rs.getInt("Order_id");
                Double price= rs.getDouble("Order_price");
                Date date= rs.getDate("Order_date");
                item.setOrder_id(id+"");
                item.setOrder_date(date+"");
                item.setOrder_price(price+"");
                HashMap<Integer,Integer>hm=selectProductorder("订单编号",OrderId);
                item.setHm(hm);
            }else{
                System.out.println("没有订单编号为"+OrderId+"的订单");
                rs.close();
                pstmt.close();
                conn1.close();
                return new Order();
            }
        } catch (Exception e){
            throw new MyfunctionException();
        } finally {
            pstmt.close();
            conn1.close();
        }
        return item;
    }


    public static Product selectProduct(String pname) throws SQLException, ClassNotFoundException {
        Product item=new Product();
        Connection conn1= GetConn.getConnection();
        String sql="select * from product where Product_Name=?";
        if(pname.matches("\\d+")) sql="select * from product where Product_id=?";
        PreparedStatement pstmt=conn1.prepareStatement(sql);
        pstmt.setString(1,pname);
        try(ResultSet rs=pstmt.executeQuery()){
            if (rs.next()) {
                int id=rs.getInt("Product_id");
                String name=rs.getString("Product_Name");
                int stock=rs.getInt("Product_Stock");
                double price=rs.getDouble("Product_price");
                item.setProduct_id(id+"");
                item.setProduct_name(name);
                item.setProduct_Stock(stock+"");
                item.setProduct_price(price+"");
            }else{
                System.out.println("没有商品"+pname);
            }
        } catch (Exception e){
            throw new MyfunctionException();
        } finally {
            pstmt.close();
            conn1.close();
        }
        return item;
    }

    public static String changeNametoid(String name) throws SQLException, ClassNotFoundException {
        int id=-1;
        Connection conn= GetConn.getConnection();
        String sql="select Product_id from product where Product_Name=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,name);
        try(ResultSet rs=pstmt.executeQuery()) {
            if(rs.next()){
                id = rs.getInt("Product_id");
            }
        } catch (Exception e){
            throw new MyfunctionException();
        } finally {
            pstmt.close();
            conn.close();
        }
        return id+"";
    }
}
