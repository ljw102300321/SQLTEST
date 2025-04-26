package west_2;

import java.sql.SQLException;
import java.util.HashMap;

public class selectTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //根据订单编号查询订单及其商品信息
        Order o1=Select.selectOrder("1");
        System.out.println(o1);
        //查询不存在的订单
        Order o2=Select.selectOrder("119");
        System.out.println(o2);
        //查询存在但无商品的订单
        Order o3=Select.selectOrder("11");
        System.out.println(o3);

        //根据商品名字查询商品信息->>>>>>>>>只能根据名字查询
        Product p0=Select.selectProduct("梨");
        System.out.println(p0);
        //查询不存在的商品
        Product p2=Select.selectProduct("香蕉");
        System.out.println(p2);


        //根据商品编号或订单编号查询商品细节
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
    }
}
