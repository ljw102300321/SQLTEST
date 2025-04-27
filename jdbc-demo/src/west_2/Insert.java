package west_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.*;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Insert {
    public static void insertProductOrder(String s1) throws ClassNotFoundException, SQLException{
        Pattern pattern = Pattern.compile("商品编号：(.*) 订单编号：(.*) 订单数量：(.*)");
        Matcher matcher = pattern.matcher(s1);
        String pid1="";
        String oid1="";
        String quantity1="";
        if (matcher.find()) {
            pid1=matcher.group(1);
            oid1=matcher.group(2);
            quantity1=matcher.group(3);
        }
        Connection conn= GetConn.getConnection();
        String sql0="select Product_Price from product where Product_id=?";
        String sql1="insert into product_order values(?,?,?)";
        String sql2="update product set Product_Stock=Product_Stock-? where Product_id=?";
        String sql3="update order1 set Order_price=Order_price+? where Order_id=?";
        PreparedStatement pstmt0=conn.prepareStatement(sql0);

        try{
            conn.setAutoCommit(false);
            pstmt0.setString(1,pid1);
            ResultSet rs=pstmt0.executeQuery();
            double pprice=0;
            if (rs.next()) {
                pprice= rs.getDouble("Product_Price");
            }
            insertProductorder(sql1,Integer.parseInt(pid1),oid1,quantity1);//执行sql1
            updateProduct(sql2,quantity1, Integer.parseInt(pid1));//执行sql2
            updateOrderprice(sql3,quantity1,pprice,oid1);//执行sql3
            conn.commit();
            System.out.println("插入订单成功");
        }
        catch (Exception e){
            System.out.println(" 插入订单出错");
            conn.rollback();
            throw new MyfunctionException();
        }
        finally {
            conn.close();
        }
    }

    public static void insertOrder(String s1) throws ClassNotFoundException, SQLException {
        Pattern pattern = Pattern.compile("订单编号：(.*)|订单时间：(.*)|订单总价：(.*)元");
        Matcher matcher = pattern.matcher(s1);
        String id1="";
        String date1="";
        String price1="";
        while (matcher.find()) {
            if("".equals(id1)&&matcher.group(1)!=null)
                id1=matcher.group(1);
            if("".equals(date1)&&matcher.group(2)!=null)
                date1=matcher.group(2);
            if("".equals(price1)&&matcher.group(3)!=null)
                price1=matcher.group(3);
        }//存储订单数据
        ;
        HashMap<String,String> orderItem=new HashMap<>();//用来存储订单中的商品细节
        Pattern p = Pattern.compile("商品名称：(.*)\\n购买数量：(.*)");
        Matcher m = p.matcher(s1);
        while (m.find()) orderItem.put(m.group(1),m.group(2));
        Connection conn= GetConn.getConnection();

        String sql1="insert into order1 values(?,?,?)";
        String sql2="select Product_id from product where Product_Name=?";
        String sql3="insert into product_order values(?,?,?)";
        String sql4="update product set Product_Stock=Product_Stock-? where Product_id=?";
       try{
           conn.setAutoCommit(false);
           insertOrder1(sql1,id1,date1,price1);//执行sql1
           for(String name:orderItem.keySet()){
               int pid1=getPid(sql2,name);//执行sql2
               String quantity1=orderItem.get(name);
               insertProductorder(sql3,pid1,id1,quantity1);//执行sql3
               updateProduct(sql4,quantity1,pid1);//执行sql4
           }
           conn.commit();
           System.out.println("插入订单成功");
       }
       catch (Exception e){
           System.out.println(" 插入订单出错");
           conn.rollback();
           throw new MyfunctionException();
       }
       finally {
           conn.close();
       }
    }

    public static void insertProduct(String s1) throws ClassNotFoundException, SQLException {
        Pattern pattern = Pattern.compile("商品名称：(.*) 商品编号：(.*) 商品价格：(.*) 商品库存：(.*)");
        Matcher matcher = pattern.matcher(s1);
        String name1="";
        int id1=-1;
        double price1=-1;
        int stock1=-1;
        if (matcher.find()) {
            name1=matcher.group(1);
            id1=Integer.parseInt(matcher.group(2));
            price1=Double.parseDouble(matcher.group(3));
            stock1=Integer.parseInt(matcher.group(4));
        }
        Connection conn= GetConn.getConnection();
        String sql="insert into product values(?,?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,id1);
        pstmt.setString(2,name1);
        pstmt.setDouble(3,price1);
        pstmt.setInt(4,stock1);
        try{
            conn.setAutoCommit(false);
            pstmt.executeUpdate();
            conn.commit();
            System.out.println("插入新商品成功");
        }
        catch (Exception e){
            System.out.println("插入新商品失败");
            conn.rollback();
            throw new MyfunctionException();
        }
        finally {
            pstmt.close();
            conn.close();
        }
    }

    public static void updateOrderprice(String sql,String quantity1,double pprice,String oid1) throws SQLException, ClassNotFoundException {
        //String sql3="update order1 set Order_price=Order_price+? where Order_id=?";
        Connection conn = GetConn.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,(Double.parseDouble(quantity1)*pprice)+"");
        pstmt.setString(2,oid1);
        try{
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
    public static void updateProduct(String sql,String quantity1,int pid1) throws SQLException, ClassNotFoundException {
        //String sql4="update product set Product_Stock=Product_Stock-? where Product_id=?";
        Connection conn = GetConn.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,quantity1);
        pstmt.setInt(2,pid1);
        try{
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
    public static void insertProductorder(String sql,int pid1,String id1,String quantity1) throws SQLException, ClassNotFoundException {
        //String sql3="insert into product_order values(?,?,?)";
        Connection conn = GetConn.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,pid1);
        pstmt.setString(2,id1);
        pstmt.setInt(3,Integer.parseInt(quantity1));
        try{
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

    public static void insertOrder1(String sql,String id1,String date1,String price1) throws SQLException, ClassNotFoundException {
        //String sql1="insert into order1 values(?,?,?)";
        Connection conn = GetConn.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,id1);
        pstmt.setString(2,date1);
        pstmt.setString(3,price1);
        try{

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

    public static int getPid(String sql,String pname) throws SQLException, ClassNotFoundException {
        //String sql = "select Product_id from product where Product_Name=?";
        int pid=-1;
        Connection conn = GetConn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,pname);
        try {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                pid=rs.getInt("Product_id");
            }
        } catch (Exception e) {
            throw new MyfunctionException();
        } finally {
            pstmt.close();
            conn.close();
        }
        return pid;
    }
}


