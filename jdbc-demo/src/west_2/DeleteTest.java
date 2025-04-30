package west_2;

import org.junit.Test;


public class DeleteTest {
  @Test
  public void test(){
      //在订单中存在，无法删除，返回-1
      try {
          int x=Delete.deleteProduct("苹果");
          System.out.println(x);
      }catch (Exception e){
          System.out.println("删除商品失败");
      }

      //不存在的商品，返回0
      try {
          int y=Delete.deleteProduct("花生");
          System.out.println(y);
      } catch (Exception e){
          System.out.println("删除商品失败");
      }

      //存在且不在订单中，返回1
      try {
          int z=Delete.deleteProduct("橘子");
          System.out.println(z);
      }catch (Exception e){
          System.out.println("删除商品失败");
      }

      //
      try {
          int a=Delete.deleteOrder(1);
          System.out.println(a);
      }catch (Exception e){
          System.out.println("删除订单失败");
      }
      try {
          int b=Delete.deleteOrder(2);
          System.out.println(b);
      }catch (Exception e){
          System.out.println("删除订单失败");
      }
  }



}
