package west_2;

import java.sql.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class west2_Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String s3="商品编号：3 订单编号：4 订单数量：20";
        String s1="商品名称：鸡蛋 商品编号：5 商品价格：1.0 商品库存：100000";
        String s2="订单编号：5 订单时间：2025-4-10 订单价格：1000";
        //Insert i1 = new Insert();
        //Insert.insert_product(s1);
        //Insert.insert_order(s2);
        //Insert.insert_productOrder(s3);

        //Update.updateStock("苹果",200,"decrease");
        Update.updateNeed(10,"苹果",2,"increase");
String s="订单编号：11\n" +
        "商品名称：苹果\n" +
        "购买数量：10\n" +
        "商品名称：梨\n" +
        "购买数量：10\n" +
        "商品名称：橘子\n" +
        "购买数量：10\n" +
        "订单时间：2024-4-11\n" +
        "订单总价：100元";
        //Insert.insertOrder(s);
        //Insert.insertProductOrder(s3);
        //HashMap<Integer,Integer> hm=Select.selectProductorder("订单编号","111");
        //System.out.println(hm);
        //int x=Delete.deleteProduct("面包");
        //System.out.println(x);
        //Insert.insertProduct("商品名称：面包 商品编号：18 商品价格：10 商品库存：1");
//int x=Delete.deleteOrder(7);
  //      System.out.println(x);
 //Insert.insertOrder(s);
        //Select.select_product();
        //Select.select_order();
        //Select.select_productOrder("商品编号",1);
        //System.out.println("-------------------------------------------");
        //Select.select_productOrder("订单编号",1);
        //int r=Delete.deleteOrder(1);
        //System.out.println(r);
/*        Pattern pattern = Pattern.compile("订单编号：(.*)|订单时间：(.*)|订单总价：(.*)");
        Matcher matcher = pattern.matcher(s);
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
        }
        System.out.println(id1);
        System.out.println(date1);
        System.out.println(price1);

 */

        //Insert.insert_order(s);
        //Select.select_product("Apple",true);
        //Select.select_order(1+"");
        //HashMap<Integer,Integer> hm=Select.select_productOrder("订单编号",1+"");
        //Order order1=Select.select_order("11");
        //System.out.println(order1.getOrder_id());
        //Order order2=Select1.select_order("11");
        //HashMap<Integer,Integer>hm=Select.select_productOrder("订单编号",1+"");
        //System.out.println(hm);
        //Order o=Select.select_order(7+"");
        //System.out.println(o);
        //HashMap<Integer,Integer>hm=Select.select_productOrder("订单编号",1+"");
        //System.out.println(hm);
        //Order o=Select.select_order(2+"");
        //System.out.println(o);
        //Insert.insert_productOrder("商品编号：1 订单编号：7 订单数量：10");
//Update.updateStock("Apple",40,false);
        //Update.updateStock("Banana",956,true);
//Insert.insertProductOrder("商品编号：1 订单编号：3 订单数量：1000");







    }
}
