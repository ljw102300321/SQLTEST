package west_2;

import java.sql.SQLException;

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
        //
        Product p1=Select.selectProduct("0");
        System.out.println(p1);
    }
}
