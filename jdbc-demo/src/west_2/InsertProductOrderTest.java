package west_2;

import org.junit.Test;


public class InsertProductOrderTest {
   @Test
   public void test() {
       try {
           Insert.insertProductOrder("商品编号：2 订单编号：1 订单数量：2");
       }catch (Exception e){
           System.out.println("插入订单项目失败");
       }
   }


}
