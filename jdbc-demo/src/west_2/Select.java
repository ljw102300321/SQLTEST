package west_2;

import java.sql.*;
import java.util.HashMap;

public class Select {

    public static HashMap<Integer,Integer> selectProductorder(String findItem, String x) throws SQLException, ClassNotFoundException{
        if(findItem.equals("订单编号")){
            if(!x.matches("\\d+")){
                System.out.println("订单编号必须为数字");
                return new HashMap<>();
            }
        }
        String name="";
        HashMap<Integer,Integer> hm=new HashMap<>();
        //如果x不是数字,由上面步骤规范x必定是商品id
        if(!x.matches("\\d+")) {
            name=x;
            //先执行查询语句得到该商品的id
            Connection conn= GetConn.getConnection();
            String sql="select Product_id from product where Product_name=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,x);
            try {
                ResultSet rs=pstmt.executeQuery();
                if(!rs.next()){
                    rs.close();
                    pstmt.close();
                    conn.close();
                    return new HashMap<>();
                }else {
                    x = rs.getInt("Product_id")+"";
                    rs.close();
                }
            } catch (Exception e){
                System.out.println("查询订单细节失败");
            } finally {
                pstmt.close();
                conn.close();
            }
        }
        //根据订单编号查询该订单的内容
        if(findItem.equals("订单编号")){
            Connection conn= GetConn.getConnection();
            String sql="select * from product_order where Order_id=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,x);
            try {
                ResultSet rs=pstmt.executeQuery();
                while (rs.next()) {
                    int pid = rs.getInt("Product_id");
                    int oid= rs.getInt("Order_id");
                    int num= rs.getInt("quantity");
                    //System.out.println(pid + " " + oid + " " + num);
                    hm.put(pid,num);
                }
                rs.close();
            }
            catch (Exception e){
                System.out.println("查询订单细节失败");
            }
            finally {
                pstmt.close();
                conn.close();
            }
        }   else if (findItem.equals("商品编号")) {
            Connection conn= GetConn.getConnection();//根据商品编号或商品名称查询与该商品有关的内容
            String sql="select * from product_order where Product_id=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,x);
            try {
                ResultSet rs=pstmt.executeQuery();
                //上面步骤已经保证商品存在
                while (rs.next()) {
                    int pid = rs.getInt("Product_id");
                    int oid = rs.getInt("Order_id");
                    int num = rs.getInt("quantity");
                    //System.out.println(pid + " " + oid + " " + num);
                    hm.put(oid,num);
                }
                rs.close();
            }
            catch (Exception e){
                System.out.println("查询订单细节失败");
            }
            finally {
                pstmt.close();
                conn.close();
            }
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
        try {
            ResultSet rs=pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("Order_id");
                Double price= rs.getDouble("Order_price");
                Date date= rs.getDate("Order_date");
                //System.out.println(id + " " + date + " " + price);
                item.setOrder_id(id+"");
                item.setOrder_date(date+"");
                item.setOrder_price(price+"");
                HashMap<Integer,Integer>hm=selectProductorder("订单编号",OrderId);
                item.setHm(hm);
                rs.close();
            }else{
                System.out.println("没有订单编号为"+OrderId+"的订单");
                rs.close();
                pstmt.close();
                conn1.close();
                return new Order();
            }
        } catch (Exception e){
            System.out.println("查询订单失败");
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
        if(pname.matches("\\d+")){
            sql="select * from product where Product_id=?";
        }
        PreparedStatement pstmt=conn1.prepareStatement(sql);
        pstmt.setString(1,pname);
        try {
            ResultSet rs=pstmt.executeQuery();
            if (rs.next()) {
                int id=rs.getInt("Product_id");
                String name=rs.getString("Product_Name");
                int stock=rs.getInt("Product_Stock");
                double price=rs.getDouble("Product_price");
                item.setProduct_id(id+"");
                item.setProduct_name(name);
                item.setProduct_Stock(stock+"");
                item.setProduct_price(price+"");
                rs.close();
            }else{
                System.out.println("没有商品"+pname);
                rs.close();
                pstmt.close();
                conn1.close();
                return new Product();
            }
        } catch (Exception e){
            System.out.println("查询商品失败");
        } finally {
            pstmt.close();
            conn1.close();
        }
        return item;
    }
}
