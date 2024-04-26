import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//import ConfirmationPage.*;
//import ConfirmationPage.ConfirmationPage;
import java.time.LocalDateTime;

import Apology.apology;

public class SeatSelectionFrame extends Frame implements ActionListener {
    private Button[][] seatButtons;
    private Button selectedButton;
    private Button bookButton;
    private String name;
    private String movie;
    private String time;
    private String date;
    private String tableName;
    private LocalDateTime bookingTime;
    public SeatSelectionFrame(String user,String movie,String time,String date) {
        this.name=user;
        this.movie=movie;
        this.time=time;
        this.date=date;

        String temptableName=movie+"_"+date+"_"+time;
        this.tableName=formatTableName(temptableName);
        bookingTime=LocalDateTime.now();
        setTitle(name+" "+movie+" "+" "+time+" "+date);
        setSize(1295, 687);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(5, 5, 5, 5));
        setLocationRelativeTo(null);
        seatButtons = new Button[5][4];
        selectedButton = null;
        setBackground(Color.DARK_GRAY);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                int seatNumber=i * 5 + j + 1;
                seatButtons[i][j] = new Button(Integer.toString(i * 5 + j + 1));
                seatButtons[i][j].setFont(new Font("Arial", Font.BOLD, 14));
                seatButtons[i][j].addActionListener(this);
                if(isSeatBooked(seatNumber)){
                seatButtons[i][j].setEnabled(false);
                seatButtons[i][j].setBackground(Color.WHITE);
                }
                add(seatButtons[i][j]);
            }
        }
        Panel bookpanel=new Panel(new FlowLayout());
        
        bookpanel.setBounds(100,100,1295, 100);
        bookpanel.setBackground(Color.PINK);
        bookButton = new Button("Book");
        bookButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookButton.addActionListener(this);
        bookButton.setBounds(683, 660, 100, 100);
        bookpanel.add(bookButton);
        
        add(bookpanel);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
    public boolean isSeatBooked(int seatNumber) {
         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaminiproject", "root", "Harshit@99");

            // Create the table if it does not exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                                    + "SeatNo INT, "
                                    + "Username VARCHAR(255), "
                                    + "Moviename VARCHAR(255),"
                                    + "BookingTime TIMESTAMP, "
                                    + "PaymentLink TEXT)";
            PreparedStatement createStatement = connection.prepareStatement(createTableSQL);
            createStatement.executeUpdate();
            
            String sql = "SELECT seatno FROM " + tableName + " WHERE seatno = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, seatNumber); // Set the seat number as parameter
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.next());
                // Seat is already booked
                System.out.println("Seat " + seatNumber + " is booked.");
                return true;
            } else {
                // Seat is available
                System.out.println("Seat " + seatNumber + " is available.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("hello");
            new apology("Error: " + ex.getMessage());
            return false;
        }
    }
    public static String formatTableName(String tableName) {
        // Remove leading and trailing spaces
        tableName = tableName.trim();
        
        // Replace invalid characters with underscores
        tableName = tableName.replaceAll("[^a-zA-Z0-9_]", "_");
        
        // Ensure the table name starts with a letter
        if (!Character.isLetter(tableName.charAt(0))) {
            // If the first character is not a letter, prepend an underscore
            tableName = "_" + tableName;
        }
        
        // Limit length of table name (optional)
        tableName = tableName.substring(0, Math.min(tableName.length(), 64)); // Limit to 64 characters
        
        return tableName;
    }
    int seat=0;
    public void actionPerformed(ActionEvent e) {
        String seatNumber="";
        Button clickedButton = (Button) e.getSource();
        if(e.getSource()==bookButton){
            //String paymentlink="kar";
            System.out.println(seat);
            if(seat!=0){
            
           // int seat=Integer.parseInt(seatNumber);   
            setVisible(false);
            new paymentframe(name,bookingTime,movie,seat,tableName,time,date);
        }
            else{
                new apology("Please select seat");
            }
            
        }
        if (selectedButton != null && selectedButton != clickedButton) {
            // Reset background color of previously selected button
            selectedButton.setBackground(null);
        }

        // Toggle selection (change background color)
        if (clickedButton == selectedButton) {
            // Deselect the currently selected button
            clickedButton.setBackground(Color.WHITE);
            selectedButton = null;
            System.out.println("Seat deselected.");
        } else {
            // Select the clicked button
            clickedButton.setBackground(Color.GREEN); // Highlight selected seat
            selectedButton = clickedButton;
            seatNumber= clickedButton.getLabel();
            seat=Integer.parseInt(seatNumber);
            System.out.println("Selected Seat: " + seatNumber+seat);
        }

    }

    //to get the payment through the app;
    public static void main(String[] args) {
        new SeatSelectionFrame("user", "movie","time", "time");
    }
}
