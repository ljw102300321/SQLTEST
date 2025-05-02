package west_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sort {
    public static ArrayList<Product> sortProduct(String Item, String function) throws SQLException, ClassNotFoundException {
        ArrayList<Product> list=new ArrayList<>();
        Connection conn= GetConn.getConnection();
        String sql="select Product_id,Product_Name,Product_Stock,Product_price from product order by Product_id asc";
        if(function.equals("desc")||function.equals("asc")){
            if(Item.equals("Product_id")||Item.equals("Product_price")){
                sql="select Product_id,Product_Name,Product_Stock,Product_price from product order by "+Item+" "+function;
                PreparedStatement pstmt=conn.prepareStatement(sql);
                try(ResultSet rs=pstmt.executeQuery()){
                    conn.setAutoCommit(false);
                    while (rs.next()) {
                        int id = rs.getInt("Product_id");
                        String name = rs.getString("Product_Name");
                        int stock = rs.getInt("Product_Stock");
                        double price = rs.getDouble("Product_price");
                        Product item = new Product();
                        item.setProduct_id(id + "");
                        item.setProduct_name(name);
                        item.setProduct_Stock(stock + "");
                        item.setProduct_price(price + "");
                        list.add(item);
                    }
                }
                catch (Exception e){
                    conn.rollback();
                    throw new MyfunctionException();
                }
                finally {
                    pstmt.close();
                    conn.close();
                }
            }
        }
        return list;
    }
    public static ArrayList<Order> sortOrder(String Item, String function) throws SQLException, ClassNotFoundException {
        ArrayList<Order> list=new ArrayList<>();
        Connection conn= GetConn.getConnection();
        String sql="select Order_id,Order_date,Order_price from order1 order by Order_id asc";
        if(function.equals("desc")||function.equals("asc")){
            if(Item.equals("Order_id")||Item.equals("Order_date")){
                sql="select Order_id,Order_date,Order_price from order1 order by "+Item+" "+function;
                PreparedStatement pstmt=conn.prepareStatement(sql);
                try(ResultSet rs=pstmt.executeQuery()){
                    conn.setAutoCommit(false);
                    while (rs.next()) {
                    int id=rs.getInt("Order_id");
                    String date=rs.getString("Order_date");
                    String price=rs.getString("Order_price");
                    Order item=new Order();
                    item.setOrder_id(id+"");
                    item.setOrder_date(date);
                    item.setOrder_price(price);
                    list.add(item);
                }
                } catch (Exception e){
                    conn.rollback();
                    throw new MyfunctionException();
                } finally {
                    pstmt.close();
                    conn.close();
                }
            }
        }
        return list;
    }
}
