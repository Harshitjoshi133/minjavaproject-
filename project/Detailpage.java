import java.awt.*;
import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
import java.sql.*;
//import java.time.LocalDateTime;
//import javax.imageio.ImageIO;
import java.util.*;
//import org.junit.jupiter.api.parallel.ResourceLock;

import Apology.apology;

public class Detailpage extends Frame implements ActionListener{
    private ArrayList<UserDataPanel> userDataPanels;
    Detailpage(String user,String movies) {
        setSize(1295,687);
        setTitle(user);
        setLayout(new FlowLayout()); 
        Label label=new Label("");
        label.setBounds(650, 100, 200, 40);
        userDataPanels=new ArrayList<>();
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(label);
        
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            String sql = "SELECT * FROM " + movies + " WHERE username = ?";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaminiproject", "root", "Harshit@99");
            PreparedStatement checkStatement = connection.prepareStatement(sql);
            checkStatement.setString(1, user);
            ResultSet resultSet = checkStatement.executeQuery();
            while (resultSet.next()) {
                int seatNo = resultSet.getInt("SeatNo");
                String username = resultSet.getString("Username");
                Timestamp bookingTime = resultSet.getTimestamp("BookingTime");
                String paymentLink = resultSet.getString("PaymentLink");

                UserDataPanel userDataPanel = new UserDataPanel(seatNo, username, bookingTime, paymentLink);
                userDataPanels.add(userDataPanel); // Add panel to the list
                add(userDataPanel); // Add panel to the frame
            }
            // Setting the movie picture and label using the DataBases
            
        
        
                // Hide the login window
            connection.close();
        } catch (SQLException ex) {
            new apology("Error: " + ex.getMessage());
        }
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Close the window
            }
        });
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }    

}