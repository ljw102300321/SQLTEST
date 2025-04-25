package jdbc_demo;

import west_2.GetConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update1 {

    //更改商品需求量
    public static void updateNeed(int oid,String pname,int num,String func) throws SQLException, ClassNotFoundException {

        //如果func不符合else if语句内容的任何一项，默认是增加
        String pid="x";
        String sqlx="begin";
        String sql1="select product_id from product where product_Name=?";
        String sql2="update product_order set quantity=quantity+? where Order_id=? and Product_id=-?";//默认是增加
        String sqly="commit";
        String sqlz="rollback";
        if(func.equals("decrease")){
            sql2="update product_order set quantity=quantity-? where Order_id=? and Product_id=?";
        }else if(func.equals("set")){
            sql2="update product_order set quantity=? where Order_id=? and Product_id=?";
        }
        Connection conn1= GetConn.getConnection();
        PreparedStatement pstmtx=conn1.prepareStatement(sqlx);
        PreparedStatement pstmt1=conn1.prepareStatement(sql1);
        pstmt1.setString(1,pname);
        PreparedStatement pstmt2=conn1.prepareStatement(sql2);
        pstmt2.setInt(1,num);
        pstmt2.setInt(2,oid);
        //pstmt2.setString(3,pname);
        PreparedStatement pstmty=conn1.prepareStatement(sqly);
        PreparedStatement pstmtz=conn1.prepareStatement(sqlz);
        try {
            pstmtx.executeUpdate();
            ResultSet rs=pstmt1.executeQuery();
            if(rs.next()) {
                pid= rs.getString("product_id");
            }
            rs.close();
            pstmt2.setString(3,pid);
            pstmt2.executeUpdate();
            pstmty.executeUpdate();

        }
        catch (Exception e){
            pstmtz.executeUpdate();
            System.out.println("更新商品需求失败");
        }
        finally {
            pstmtz.close();
            pstmty.close();
            pstmt2.close();
            pstmt1.close();
            pstmtx.close();
            conn1.close();
        }
    }





    public static void updateStock(String pname,int num,String func) throws SQLException, ClassNotFoundException {//pname指要改变的商品名，num表示要添加或减少的库存数
        //默认增加
        String pid="x";
        Connection conn1= GetConn.getConnection();
        String sqlx="begin";
        String sql1="select Product_id from product where Product_Name=?";
        String sql2="update product set Product_Stock=Product_Stock+? where Product_id=?";
        if(func.equals("decrease"))
            sql2="update product set Product_Stock=Product_Stock-? where Product_id=?";
        else if(func.equals("set"))
            sql2="update product set Product_Stock=? where Product_id=?";
        String sqly="commit";
        String sqlz="rollback";

        PreparedStatement pstmtx=conn1.prepareStatement(sqlx);
        PreparedStatement pstmt1=conn1.prepareStatement(sql1);
        pstmt1.setString(1,pname);
        PreparedStatement pstmt2=conn1.prepareStatement(sql2);
        PreparedStatement pstmty=conn1.prepareStatement(sqly);
        PreparedStatement pstmtz=conn1.prepareStatement(sqlz);
        try {
            pstmtx.executeUpdate();
            ResultSet rs=pstmt1.executeQuery();
            if(rs.next()) {
                pid= rs.getString("product_id");
            }
            rs.close();
            pstmt2.setInt(1,num);
            pstmt2.setString(2,pid);
            pstmt2.executeUpdate();
            pstmty.executeUpdate();
            System.out.println("更新商品库存成功");
        }
        catch (Exception e){
            pstmtz.executeUpdate();
            System.out.println("更新商品库存失败");
        }
        finally {
            pstmtz.close();
            pstmty.close();
            pstmt2.close();
            pstmt1.close();
            pstmtx.close();
            conn1.close();
        }
        /*        String sql1="select Product_id from product where Product_Name=?";
        Connection conn1= GetConn.getConnection();
        PreparedStatement pstmt1=conn1.prepareStatement(sql1);
        pstmt1.setString(1,pname);
        String pid="";
        try {
            ResultSet rs1=pstmt1.executeQuery();
            while(rs1.next()){
                pid=rs1.getString("Product_id");
            }
            rs1.close();
            if(pid.equals("")){
                System.out.println("不存在商品"+pname+",无法更新其库存");

                pstmt1.close();
                conn1.close();
                return;
            }
        }
        catch (Exception e){

        }
        finally {
            pstmt1.close();
            conn1.close();
        }
         if(b){//b取false表示减少，b取true表示增加
             Connection connx= GetConn.getConnection();
             String sqlx="update product set Product_Stock=Product_Stock+? where Product_id=?";
             PreparedStatement pstmtx=connx.prepareStatement(sqlx);
             pstmtx.setInt(1,num);
             pstmtx.setString(2,pid);
             pstmtx.executeUpdate();
             pstmtx.close();
             connx.close();
             System.out.println("更新"+pname+"库存成功，"+"库存增加"+num+"件");



         }else{
             if(Integer.parseInt(Select.selectProduct(pname).getProduct_Stock())<num){
                 Connection connx= GetConn.getConnection();
                 String sqlx="update product set Product_Stock=0 where Product_id=?";
                 PreparedStatement pstmtx=connx.prepareStatement(sqlx);
                 pstmtx.setString(1,pid);
                 pstmtx.executeUpdate();
                 pstmtx.close();
                 connx.close();
                 System.out.println("更新"+pname+"库存成功，"+"由于库存不足"+num+"，库存已清零");
             }else{
                 Connection connx= GetConn.getConnection();
                 String sqlx="update product set Product_Stock=Product_Stock-? where Product_id=?";
                 PreparedStatement pstmtx=connx.prepareStatement(sqlx);
                 pstmtx.setInt(1,num);
                 pstmtx.setString(2,pid);
                 pstmtx.executeUpdate();
                 pstmtx.close();
                 connx.close();
                 System.out.println("更新"+pname+"库存成功，"+"库存减少"+num+"件");
             }
         }

 */
    }
}
