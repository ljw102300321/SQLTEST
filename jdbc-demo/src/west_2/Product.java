package west_2;

public class Product {
    private String product_id;
    private String product_name;
    private String product_price;
    private String Product_Stock;

    public Product() {
    }

    public Product(String product_id, String product_name, String product_price, String Product_Stock) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.Product_Stock = Product_Stock;
    }


    public String getProduct_id() {return product_id;}
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_Stock() {
        return Product_Stock;
    }

    public void setProduct_Stock(String Product_Stock) {
        this.Product_Stock = Product_Stock;
    }

    public String toString() {
        return "Product{product_id = " + product_id + ", product_name = " + product_name + ", product_price = " + product_price + ", Product_Stock = " + Product_Stock + "}";
    }
}
