package west_2;

import org.junit.Test;

import java.sql.SQLException;

public class insertproductorderTest {
   @Test
   public void test() throws SQLException, ClassNotFoundException {
       try {
           Insert.insertProductOrder("商品编号：4 订单编号：1 订单数量：2");
       }catch (Exception e){

       }
   }


}
