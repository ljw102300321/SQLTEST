package west_2;

import java.sql.SQLException;

public class deleteTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //在订单中存在，无法删除，返回-1
        int x=Delete.deleteProduct("苹果");
        System.out.println(x);
        //不存在的商品，返回0
        int y=Delete.deleteProduct("花生");
        System.out.println(y);
        //存在且不在订单中，返回1
        int z=Delete.deleteProduct("橘子");
        System.out.println(z);

    }
}
