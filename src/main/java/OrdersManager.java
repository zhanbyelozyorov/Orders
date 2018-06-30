import java.util.*;

/**
 * Created by 2 on 28.06.2018.
 */
public class OrdersManager {
    DBConnection dbConnection;

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choosing;
        do {
            System.out.println("\nChoose: \n      1 - get all products by orders;" +
                    "\n      2 - get unsold products;" +
                    "\n      3 - get all products by quantity;" +
                    "\n      0 - exit;");
            choosing = scanner.nextInt();
            dbConnection = new DBConnection();

            switch (choosing) {
                case 1:
                    selectOrdersProducts();
                    break;
                case 2:
                    selectUnsoldProducts();
                    break;
                case 3:
                    selectAllProducts();
                    break;
            }
        } while (choosing != 0);
    }

    private void selectOrdersProducts() {
        List<Product> ordersProducts = dbConnection.getOrdersProducts();
        for (Product order : ordersProducts) {
            System.out.println("Order" + order.orderid + ":");
            System.out.println("      " + order);
        }
    }

    private void selectUnsoldProducts() {
        List<Product> productsList = dbConnection.getUnsoldProducts();
        for (Product product : productsList) {
            System.out.println(product);
        }
    }

    private void selectAllProducts() {
        List<Product> productsList = dbConnection.getAllProducts();
        for (Product product : productsList) {
            System.out.println(product);
        }
    }
}
