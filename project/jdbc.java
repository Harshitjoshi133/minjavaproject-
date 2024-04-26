import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbc {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Open a connection
            String url = "jdbc:mysql://localhost:3306/mysql";
            String username = "root";
            String password = "Harshit@99";
            connection = DriverManager.getConnection(url, username, password);
            
            // Connection successful
            System.out.println("Connected to the database!");
            
            // Perform database operations here...
            
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
