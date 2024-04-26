import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import javax.imageio.ImageIO;
import Apology.apology;

public class paymentframe extends Frame implements ActionListener {
    private String username;
    private String moviename;
    private LocalDateTime bookingTime;
    private int seat;
    private String time;
    private String date;
    private String tableName; // Specify your table name here
    private String paymentLink;
    private BufferedImage backgroundImage;
    private Button bookbtn;
    public paymentframe(String user, LocalDateTime curr, String movie, int Seat,String tablename,String time,String date) {
        this.username = user;
        this.tableName=tablename;
        this.bookingTime = curr;
        this.moviename = movie;
        this.seat=Seat;
        this.date=date;
        this.time=time;
        setTitle(user);
        setSize(400, 400);
        setLayout(null);
        setResizable(false);
        bookbtn = new Button("BOOK");
        bookbtn.addActionListener(this); // Register ActionListener for the button
        bookbtn.setBounds(140, 300,120,20);
        add(bookbtn);
        Label lb=new Label("Pay for confirmation Seat "+String.valueOf(Seat));
        lb.setBounds(70, 20, 200, 100);
        add(lb);
        
        try {
            backgroundImage = ImageIO.read(new File("project\\images\\QR.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        paymentLink = "user\\" + user + "attime\\" + curr.toString() + "for\\" + movie + "onseat\\" + Seat;

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 150, 150, 100, 100, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bookbtn){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaminiproject", "root", "Harshit@99");


            // Insert records into the table
            String insertSQL = "INSERT INTO " + tableName + " (SeatNo, Username, Moviename,BookingTime, PaymentLink) VALUES (?, ?,?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
            insertStatement.setInt(1, seat); // Example SeatNo
            insertStatement.setString(2, username);
            insertStatement.setString(3, insertSQL);
            insertStatement.setTimestamp(4, Timestamp.valueOf(bookingTime));
            insertStatement.setString(5, paymentLink);
            insertStatement.executeUpdate();

            connection.close();
            new ConfirmationPage(username,moviename,tableName,date,time,seat);
        } catch (SQLException ex) {
            new apology("Error: " + ex.getMessage());
        }

        setVisible(false);
        
     } // Show confirmation page after successful insertion
    }
}
