package west_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.*;
public class Delete {

    public static int deleteOrder(int Oid) throws ClassNotFoundException, SQLException {
        int rs=0;
        Connection conn= GetConn.getConnection();
        String sql="delete from order1 where Order_id=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        try{
            conn.setAutoCommit(false);
            pstmt.setString(1,Oid+"");
            rs = pstmt.executeUpdate();//rs=0说明没有找到要删除的订单
            conn.commit();
            if(rs!=0) System.out.println("删除订单成功");
            else System.out.println("不存在此订单");
        }
        catch (Exception e){
            conn.rollback();
            System.out.println("删除订单失败");
            throw new MyfunctionException();
        }
        finally {
            pstmt.close();
            conn.close();
        }
        return rs;
    }



    public static int deleteProduct(String nameOrid) throws ClassNotFoundException, SQLException {
        //返回0表示要删除的商品不存在，返回-1表示商品在订单中，不能删，返回其他表示删除成功
        int num=0;
        //如果nameOrid给的是商品名，将它转换成id
        if(!nameOrid.matches("\\d+")) {
            nameOrid = changeNametoid(nameOrid);
        }
        //再判断该商品是否存在在订单中，在订单中的商品无法删除
        if(isInorder(nameOrid)) return -1;//说明这个商品在订单中，无法删除
        Connection conn= GetConn.getConnection();
        String sql="delete from product where Product_id=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        try{
            conn.setAutoCommit(false);
            pstmt.setString(1,nameOrid);
            num = pstmt.executeUpdate();
            conn.commit();
        }
        catch (Exception e){
            conn.rollback();
            System.out.println("删除商品失败");
            throw new MyfunctionException();
        }
        finally {
            pstmt.close();
            conn.close();
        }
        if(num!=0&&num!=-1)
            System.out.println("删除商品成功");
        return num;
    }
    public static String changeNametoid(String name) throws ClassNotFoundException, SQLException {
        int id=-1;
        Connection conn = GetConn.getConnection();
        String sql = "select Product_id from product where Product_Name=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        try(ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                id = rs.getInt("Product_id");
            }
        } catch (Exception e) {
            System.out.println("查询商品失败");
            throw new MyfunctionException();
        } finally {
            pstmt.close();
            conn.close();
        }
        return id+"";
    }

    public static boolean isInorder(String id) throws ClassNotFoundException, SQLException {
        Connection conn= GetConn.getConnection();
        String sql1="select * from product_order where Product_id=?";
        PreparedStatement pstmt=conn.prepareStatement(sql1);
        pstmt.setString(1,id);
        try (ResultSet rs1=pstmt.executeQuery()) {
            if (rs1.next()) {
                rs1.close();
                pstmt.close();
                conn.close();
                return true;//说明这个商品在订单中，无法删除
            }
        }catch (Exception e){

        }finally {
            pstmt.close();
            conn.close();
        }
        return false;
    }
}
