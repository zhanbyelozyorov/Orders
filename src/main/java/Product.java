/**
 * Created by 2 on 28.06.2018.
 */
public class Product {
    int productId;
    String name;
    double price;
    double quantity = 0;
    int orderid;

    public Product(int productId, String name, double price, double quantity, int orderid) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.orderid = orderid;
    }

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public Product(int productId, String name, double price, double quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
