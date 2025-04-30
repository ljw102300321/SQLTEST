package west_2;

import org.junit.Test;


public class InsertProductTest {
        @Test
        public void test(){
            //插入第一个商品
           try {
               String s1="商品名称：苹果 商品编号：2 商品价格：10 商品库存：100";
               Insert.insertProduct(s1);
           }catch (Exception e){
               System.out.println("插入商品失败");
           }

            //再次插入此商品，显示插入失败，因为不能插入重复商品
            try {
                String s2="商品名称：苹果 商品编号：2 商品价格：10 商品库存：100";
                Insert.insertProduct(s2);
            }catch (Exception e){
                System.out.println("插入商品失败");
            }
            //插入商品价格小于0的商品，显示失败，因为商品价格不能小于0
            try {
                String s3="商品名称：橘子 商品编号：3 商品价格：-1 商品库存：100";
                Insert.insertProduct(s3);
            }catch (Exception e){
                System.out.println("插入商品失败");
            }

            //将商品价格改成正数，插入成功
            try{
                String s4="商品名称：橘子 商品编号：3 商品价格：1 商品库存：100";
                Insert.insertProduct(s4);
            }catch (Exception e){
                System.out.println("插入商品失败");
            }
            //不能插入商品编号小于0的商品
            try{
                String s5="商品名称：梨 商品编号：-1 商品价格：4 商品库存：100";
                Insert.insertProduct(s5);
            }catch (Exception e){
                System.out.println("插入商品失败");
            }

            //将编号改成大于0，插入成功
            try{
                String s="商品名称：梨 商品编号：1 商品价格：4 商品库存：100";
                Insert.insertProduct(s);
            }catch (Exception e){
                System.out.println("插入商品失败");
            }

            //商品库存不能小于0
            try{
                String s6="商品名称：鸡蛋 商品编号：4 商品价格：1.5 商品库存：-1";
                Insert.insertProduct(s6);
            }catch (Exception e){
                System.out.println("插入商品失败");
            }
            //
            try{
                String s7="商品名称：鸡蛋 商品编号：4 商品价格：1.5 商品库存：0";
                Insert.insertProduct(s7);
            }catch (Exception e){
                System.out.println("插入商品失败");
            }
        }





}
