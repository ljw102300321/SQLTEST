package west_2;

import org.junit.Test;


public class updateTest {
   @Test
   public void test(){
       try {
           Update.updateStock("苹果",200,"set");
       }catch (Exception e){
           System.out.println("更新库存失败");
       }
        try {
            Update.updateStock("苹果",200,"increase");
        }catch (Exception e){
            System.out.println("更新库存失败");
        }
        try {
            Update.updateStock("苹果",200,"decrease");
        }catch (Exception e){
            System.out.println("更新库存失败");
        }
        try {
            Update.updateNeed(10,"苹果",2,"increase");
        }catch (Exception e){
            System.out.println("更新需求失败");
        }
    }
}
