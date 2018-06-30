import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2 on 28.06.2018.
 */
public class Order {
    int orderId;
    String description;
    List<Product> productsList;

    public Order(int orderId, String description) {
        this.orderId = orderId;
        this.description = description;
    }
}
