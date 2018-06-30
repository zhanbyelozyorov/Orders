import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2 on 28.06.2018.
 */
public class DBConnection {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/orders";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "g2222n";

    List<Product> productList = new ArrayList<>();
    ;

    private Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    public List<Product> getOrdersProducts() {
        try (Connection connection = getDBConnection(); Statement statement = connection.createStatement()) {

            String query = "SELECT product.productid, product.name, product.price, orderproducts.productquantity," +
                    "orderproducts.orderid  FROM product, orderproducts " +
                    "WHERE product.productid=orderproducts.productid  " +
                    "ORDER BY orderproducts.orderid";

            ResultSet rs = statement.executeQuery(query);
            int count = 1;
            while (rs.next()) {
                int orderNum = rs.getInt("orderid");
                int productid = rs.getInt("productid");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                double quantity = rs.getDouble("productquantity");

                productList.add(new Product(productid, name, price, quantity, orderNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getUnsoldProducts() {
        try (Connection connection = getDBConnection(); Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM product LEFT JOIN orderproducts " +
                    "ON product.productid=orderproducts.productid WHERE orderproducts.productid IS NULL";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int productid = rs.getInt("productid");
                String name = rs.getString("name");
                double price = rs.getDouble("price");

                productList.add(new Product(productid, name, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getAllProducts() {
        try (Connection connection = getDBConnection(); Statement statement = connection.createStatement()) {

            String query = "SELECT product.productid, product.name, product.price, orderproducts.productquantity " +
                    "FROM product, orderproducts WHERE product.productid=orderproducts.productid " +
                    "ORDER BY orderproducts.productquantity DESC";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int productid = rs.getInt("productid");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                double quantity = rs.getDouble("productquantity");
                boolean flag = true;

                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).name.equals(name)) {
                        productList.set(i, new Product(productid, name, price, quantity + productList.get(i).quantity));
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    productList.add(new Product(productid, name, price, quantity));
                }
            }
            getUnsoldProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}

