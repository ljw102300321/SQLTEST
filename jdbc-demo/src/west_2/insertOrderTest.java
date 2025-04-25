package west_2;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class insertOrderTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //插入第一个订单
        String s1="订单编号：1\n" +
                "商品名称：苹果\n" +
                "购买数量：5\n" +
                "商品名称：梨\n" +
                "购买数量：5\n" +
                "商品名称：橘子\n" +
                "购买数量：5\n" +
                "订单时间：2025-4-21\n" +
                "订单总价：75元";
        Insert.insertOrder(s1);

        //重复插入订单，无法插入
/*        String s2="订单编号：1\n" +
                "商品名称：苹果\n" +
                "购买数量：5\n" +
                "商品名称：梨\n" +
                "购买数量：5\n" +
                "商品名称：橘子\n" +
                "购买数量：5\n" +
                "订单时间：2025-4-21\n" +
                "订单总价：75元";
        Insert.insertOrder(s2);

 */
        //插入的订单中有不存在的商品，无法插入
/*        String s3="订单编号：2\n" +
                "商品名称：桃子\n" +
                "购买数量：5\n" +
                "商品名称：梨\n" +
                "购买数量：5\n" +
                "商品名称：橘子\n" +
                "购买数量：5\n" +
                "订单时间：2025-4-21\n" +
                "订单总价：75元";
        Insert.insertOrder(s3);
 */
        //将不存在的商品删除后
/*        String s4="订单编号：2\n" +
                "商品名称：梨\n" +
                "购买数量：5\n" +
                "商品名称：橘子\n" +
                "购买数量：5\n" +
                "订单时间：2025-4-21\n" +
                "订单总价：25元";
        Insert.insertOrder(s4);
*/
        //购买数量小于0，无法插入
/*        String s5="订单编号：3\n" +
                "商品名称：梨\n" +
                "购买数量：1\n" +
                "商品名称：橘子\n" +
                "购买数量：-5\n" +
                "订单时间：2025-4-21\n" +
                "订单总价：25元";
        Insert.insertOrder(s5);
*/
/*        String s6="订单编号：3\n" +
                "商品名称：梨\n" +
                "购买数量：1\n" +
                "商品名称：橘子\n" +
                "购买数量：5\n" +
                "订单时间：2025-4-21\n" +
                "订单总价：25元";
        Insert.insertOrder(s6);
*/
//订单总价不能小于0
/*        String s7="订单编号：4\n" +
                "商品名称：梨\n" +
                "购买数量：1\n" +
                "商品名称：橘子\n" +
                "购买数量：5\n" +
                "订单时间：2025-4-21\n" +
                "订单总价：-1元";
        Insert.insertOrder(s7);

 */
        /*
        String s8="订单编号：4\n" +
                "商品名称：梨\n" +
                "购买数量：1\n" +
                "商品名称：橘子\n" +
                "购买数量：5\n" +
                "订单时间：2025-4-21\n" +
                "订单总价：10元";
        Insert.insertOrder(s8);
*/
    }

}
