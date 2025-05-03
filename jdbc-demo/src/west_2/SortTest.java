package west_2;

import org.junit.Test;

import java.util.ArrayList;

public class SortTest {
    @Test
    public void test(){
        //根据订单编号
        try {
            System.out.println("----------------------------------------------------");
            ArrayList<Order> list=Sort.sortOrder("Order_id","asc");
            for (Order o:list) {
                System.out.println(o);
            }
        }
        catch (Exception e){
            System.out.println("排序失败");
        }
        //根据订单日期
        try {
            System.out.println("----------------------------------------------------");
            ArrayList<Order> list=Sort.sortOrder("Order_date","asc");
            for (Order o:list) {
                System.out.println(o);
            }
        } catch (Exception e){
            System.out.println("排序失败");
        }
        //根据商品价格
        try {
            System.out.println("----------------------------------------------------");
            ArrayList<Product> list=Sort.sortProduct("Product_price","asc");
            for (Product p:list) {
                System.out.println(p);
            }
        }catch (Exception e){
            System.out.println("排序失败");
        }
        //根据商品编号
        try {
            System.out.println("----------------------------------------------------");
            ArrayList<Product> list=Sort.sortProduct("Product_id","asc");
            for (Product p:list) {
                System.out.println(p);
            }
        } catch (Exception e){
            System.out.println("排序失败");
        }

    }
}
