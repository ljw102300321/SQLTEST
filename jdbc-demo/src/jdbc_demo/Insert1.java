package jdbc_demo;

import west_2.GetConn;
import west_2.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Insert1 {


    public static void insert_product(String s1) throws ClassNotFoundException, SQLException {
        Pattern pattern = Pattern.compile("商品名称：(.*) 商品编号：(.*) 商品价格：(.*) 商品库存：(.*)");
        Matcher matcher = pattern.matcher(s1);
        String name1="";
        String id1="";
        String price1="";
        String stock1="";
        if (matcher.find()) {
            name1=matcher.group(1);
            id1=id1=matcher.group(2);
            price1=matcher.group(3);
            stock1=matcher.group(4);
        }

        Connection conn= GetConn.getConnection();
        //String sql="insert into product values("+id1+", \'"+name1+"\' , "+price1+", "+stock1+")";
        String sql="insert into product values(?,?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,id1);
        pstmt.setString(2,name1);
        pstmt.setString(3,price1);
        pstmt.setString(4,stock1);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }




    public static void insert_productOrder(String s1) throws ClassNotFoundException, SQLException{
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
        Connection conn1= GetConn.getConnection();
        String sql1="select Product_Name,Product_Price,Product_Stock from product where Product_id=?";//查看是否有想插入的订单项目对应的商品
        PreparedStatement pstmt1=conn1.prepareStatement(sql1);
        pstmt1.setString(1,pid1);
        ResultSet rs=pstmt1.executeQuery();
        String pname="";
        double pprice=0;
        int pstock=0;
        if (rs.next()) {
            //System.out.println(rs.getString("Product_Name"));
            pname=rs.getString("Product_Name");
            pprice= rs.getDouble("Product_Price");
            pstock= rs.getInt("Product_Stock");
            rs.close();
            pstmt1.close();
            conn1.close();
            if(pstock<Integer.parseInt(quantity1)){
                System.out.println("订单需求的"+pname+"大于库存,无法插入此订单项目");
                return;
            }
        }else{
            System.out.println("不存在商品编号为"+pid1+"的商品");
            rs.close();
            pstmt1.close();
            conn1.close();
            return;
        }


        //System.out.println(pid1+" "+oid1+" "+quantity1);
        if(Select1.select_order(oid1).getOrder_id()==null){
            return;
        }
/*        if(Select.select_product(pname).getProduct_name()==null){
            //System.out.println("订单中有不存在的商品,无法插入");
            return;
        }
*/
        Connection conn= GetConn.getConnection();
        //System.out.println(pid1+"   "+oid1+"  "+quantity1);
        //String sql="insert into product_order values("+pid1+", \'"+oid1+"\' , "+quantity1+")";
        String sql="insert into product_order values(?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,pid1);
        pstmt.setString(2,oid1);
        pstmt.setString(3,quantity1);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

        //在插入订单条目后，需要改变订单的总价和商品的库存
        double sum=0;
        sum=Integer.parseInt(quantity1)*pprice;
        //更新商品库存
        Connection connx= GetConn.getConnection();
        String sqlx="update product set Product_Stock=Product_Stock-? where Product_id=?";
        PreparedStatement pstmtx=connx.prepareStatement(sqlx);
        pstmtx.setString(1,quantity1);
        pstmtx.setString(2,pid1);
        pstmtx.executeUpdate();
        pstmtx.close();
        connx.close();

        //更新订单总价
        Connection conny= GetConn.getConnection();
        String sqly="update order1 set Order_price=Order_price+? where Order_id=?";
        PreparedStatement pstmty=conny.prepareStatement(sqly);
        pstmty.setDouble(1,sum);
        pstmty.setString(2,oid1);
        pstmty.executeUpdate();
        pstmty.close();
        conny.close();
    }



    public static void insert_order(String s1) throws ClassNotFoundException, SQLException {
            /*
String s1="订单编号：8\n" +
        "商品名称：Apple\n" +
        "购买数量：2\n" +
        "商品名称：Banana\n" +
        "购买数量：5\n" +
        "商品名称：Orange\n" +
        "购买数量：3\n" +
        "订单时间：2024-4-10\n" +
        "订单总价：69元";

 */
        ArrayList<String> nameList=new ArrayList<String>();//用来存储订单中的商品细节
        //在插入订单前，得先判断订单中是否有不存在的商品
        Pattern p = Pattern.compile("商品名称：(.*)");
        Matcher m = p.matcher(s1);
        while (m.find()) {
            //System.out.println(m.group(1));

            if(Select1.select_product(m.group(1))==new Product()){
                System.out.println("订单中存在不存在的商品,无法插入");
                return;
            }
            nameList.add(m.group(1));
        }

        //获取每个商品购买的数量
        ArrayList<Integer> numberList=new ArrayList<>();
        Pattern p1 = Pattern.compile("购买数量：(.*)");
        Matcher m1 = p1.matcher(s1);
        while (m1.find()) {
            //System.out.println(m1.group(1));
            numberList.add(Integer.parseInt(m1.group(1)));
        }


        //插入订单时，要去改变商品库存和product_order表
        //----------------------------------------
        //先将商品名字转成商品id，并得到对应的库存

        HashMap hm=new HashMap();//用于存储商品id对应的库存
        for(String name:nameList){
            Connection conn1= GetConn.getConnection();
            String sql1="select Product_id,Product_Stock from product where Product_Name=?";
            PreparedStatement pstmt=conn1.prepareStatement(sql1);
            pstmt.setString(1,name);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                int id=rs.getInt("Product_id");
                int stock=rs.getInt("Product_Stock");
                hm.put(id,stock);
            }
            rs.close();
            pstmt.close();
            conn1.close();
        }
        //在插入订单前，先判断是否有超过库存数量的订单商品
        int k=0;
        Set<Integer> idSet1=hm.keySet();
        for(Integer id:idSet1){
            int number=numberList.get(k);
            if(number>(int)hm.get(id)){
                System.out.println("订单中存在超过库存数量的商品,无法插入");
                return;
            }
        }

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


//用map集合接受idList和numberList，方便后面插入product_order表

        Connection conn= GetConn.getConnection();
        //String sql1="begin";
        String sql2="insert into order1 values("+id1+", \'"+date1+"\' , "+price1+")";
        //PreparedStatement pstmt1=conn.prepareStatement(sql1);
        PreparedStatement pstmt2=conn.prepareStatement(sql2);
        try {
            pstmt2.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("插入订单失败，订单编号已经存在");
        }finally {
            pstmt2.close();
        }
        Set<Integer> idSet=hm.keySet();
        int j=0;
        try {
            for(int id:idSet){
                int number=numberList.get(j);
                String sql3="update product set Product_Stock=Product_Stock-? where Product_id=?";
                //String sql4="insert into product_order values("+id+", \'"+id1+"\' ,?)";
                String sql4="insert into product_order values(?,?,?)";
                PreparedStatement pstmt3=conn.prepareStatement(sql3);
                PreparedStatement pstmt4=conn.prepareStatement(sql4);
                pstmt3.setInt(1,number);
                pstmt3.setInt(2,id);
                pstmt4.setInt(1,id);
                pstmt4.setString(2,id1);
                pstmt4.setInt(3,number);
                pstmt3.executeUpdate();
                pstmt4.executeUpdate();
                pstmt3.close();
                pstmt4.close();
            }
        }
        catch (Exception e) {
            System.out.println("插入订单失败");
        }
        finally {
            conn.close();
        }

    }
}
