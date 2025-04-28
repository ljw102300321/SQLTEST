package west_2;

import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;

public class selectTest {
   @Test
   public void test() throws SQLException, ClassNotFoundException {
        //根据订单编号查询订单及其商品信息
        try {
            Order o1=Select.selectOrder("1");
            System.out.println(o1);
        }catch (Exception e){

        }

        //查询不存在的订单
        try {
            Order o2=Select.selectOrder("119");
            System.out.println(o2);
        }catch (Exception e){

        }

        //查询存在但无商品的订单
        try {
            Order o3=Select.selectOrder("11");
            System.out.println(o3);
        }catch (Exception e){

        }

        //根据商品名字查询商品信息->>>>>>>>>只能根据名字查询
        try {
            Product p0=Select.selectProduct("梨");
            System.out.println(p0);
        }catch (Exception e){

        }

        //查询不存在的商品
        try {
            Product p2=Select.selectProduct("香蕉");
            System.out.println(p2);
        }catch (Exception e){

        }
        //根据商品编号或订单编号查询商品细节
        try {
            String x="1";
            HashMap<Integer,Integer>hm= Select.selectProductorder("商品编号",x);
            for (int key : hm.keySet()) {
                int value = hm.get(key);
                System.out.println("编号为"+key+"的订单需要的"+x+"号商品数为"+value);
            }

            HashMap<Integer,Integer>hm2= Select.selectProductorder("订单编号",x);
            for (int key : hm2.keySet()) {
                int value = hm2.get(key);
                System.out.println("订单编号为"+x+"需要的"+key+"号商品数为"+value);
            }
        }catch (Exception e){

        }
    }
}
