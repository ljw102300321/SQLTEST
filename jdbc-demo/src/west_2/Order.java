package west_2;

import java.util.HashMap;

public class Order {
    private String order_id;
    private String order_date;
    private String order_price;
    private HashMap<Integer,Integer> hm;

    public Order() {
    }

    public Order(String order_id, String order_date, String order_price, HashMap<Integer, Integer> hm) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_price = order_price;
        this.hm = hm;
    }

    /**
     * 获取
     * @return order_id
     */
    public String getOrder_id() {
        return order_id;
    }

    /**
     * 设置
     * @param order_id
     */
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    /**
     * 获取
     * @return order_date
     */
    public String getOrder_date() {
        return order_date;
    }

    /**
     * 设置
     * @param order_date
     */
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    /**
     * 获取
     * @return order_price
     */
    public String getOrder_price() {
        return order_price;
    }

    /**
     * 设置
     * @param order_price
     */
    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    /**
     * 获取
     * @return hm
     */
    public HashMap<Integer, Integer> getHm() {
        return hm;
    }

    /**
     * 设置
     * @param hm
     */
    public void setHm(HashMap<Integer, Integer> hm) {
        this.hm = hm;
    }

    public String toString() {
        String Product_id="";
        String quantity="";
        StringBuilder sb=new StringBuilder();
   if(this.hm==null){
       sb.append("没有商品 ");
   }else{
       //遍历HM获取键和值
       for (Integer x : hm.keySet()) {
           Product_id=x+"";
           quantity=hm.get(x)+"";
           sb.append("Product_id = " +Product_id  +", Product_quantity = "+quantity+ " ");
       }
   }



        return "Order{order_id = " + order_id + ", order_date = " + order_date + ", order_price = " + order_price + " "+sb.toString()+ "}";
    }




}
