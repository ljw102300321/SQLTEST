package west_2;

import java.sql.SQLException;

public class insertproductorderTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            Insert.insertProductOrder("商品编号：4 订单编号：1 订单数量：2");
        }catch (Exception e){

        }
    }
}
