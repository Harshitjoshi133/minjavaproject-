import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class DatabaseConnection {
    static Connection connection=null;
    public static void main(String[] args) {
        try{
        
        
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Open a connection
            String url = "jdbc:mysql://localhost:3306/javaminiproject";
            String username = "root";
            String password = "Harshit@99";
            connection = DriverManager.getConnection(url, username, password);
            
            // Connection successful
            System.out.println("Connected to the database!");
        }
        catch(Exception e){
            e.printStackTrace();
        }   
            // Perform database operations here...
            
    }
}
