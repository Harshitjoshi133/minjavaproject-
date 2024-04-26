import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Apology.apology;

public class MovieSelection extends Frame implements ActionListener {
    private BufferedImage backgroundImage;
    private JButton[] buttons;
    private String url[];
    private String moviename[];
    private Button LogoutButton;
    public String username;
    public MovieSelection(String user) throws IOException {
        //Properties of the App
        username=user;
        setTitle("Movie Booking App  "+username);
        setSize(1295, 687);
        setLayout(null);
        setBackground(Color.GRAY);
        setLocationRelativeTo(null);

        // Header for the App
        LogoutButton = new Button("Logout");
        LogoutButton.setBounds(1150, 10, 120, 60);
        LogoutButton.setBackground(Color.gray);
        LogoutButton.addActionListener(this);
        Label titleLabel = new Label("Movie Selection");
        titleLabel.setBounds(550, 10, 200, 100);
        add(LogoutButton);
        add(titleLabel);

        // Panel for movie buttons
        Panel mainPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 140, 15));
        mainPanel.setBounds(50, 120, 1200, 500);
        add(mainPanel);
        url=new String[5];
        moviename=new String[5];
        //DataBase
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaminiproject", "root", "Harshit@99");
            PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM movies");
            ResultSet resultSet = checkStatement.executeQuery();
            // Setting the movie picture and label using the DataBases
            
            int i=0;
            
            while(resultSet.next() && i<5) {
                moviename[i]=resultSet.getString("name");
                
                // url[i]=resultSet.getString("links");
                //System.out.println(moviename[i]+" "+url[i]+" "+i);
                i++;    
            }  
        
                // Hide the login window
            connection.close();
        } catch (SQLException ex) {
            new apology("Error: " + ex.getMessage());
        }
        
        
        buttons = new JButton[5];
            
        // Load and resize movie poster images for buttons
        
        for (int i = 0; i < 5; i++) {
            //loading the image and then resizing it according
            // @SuppressWarnings("deprecation")
            // URL imageUrl = new URL(url[i]);
            // ImageIcon originalIcon = new ImageIcon(imageUrl);
            // Image originalImage = originalIcon.getImage();
            // Image resizedImage = originalImage.getScaledInstance(160, 190, Image.SCALE_SMOOTH);
            //ImageIcon resizedIcon = new ImageIcon(moviename[i]);


            buttons[i] = new JButton(moviename[i]);
            buttons[i].setName(moviename[i]);
            buttons[i].setPreferredSize(new Dimension(160, 190));
            buttons[i].addActionListener(this);
            
            Label label = new Label(moviename[i], Label.CENTER);

            Panel moviePanel = new Panel(new BorderLayout());
            moviePanel.add(buttons[i], BorderLayout.CENTER);
            moviePanel.add(label, BorderLayout.SOUTH);

            mainPanel.add(moviePanel);
        }

       
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==LogoutButton){
            
            new LoginPage();
            setVisible(false);
            return;
        }
        for(JButton b:buttons){
            if(e.getSource()==b){
            System.out.println("Done  "+b.getName());
            new TheatreSelection(b.getName(),username);
            return;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new MovieSelection("user");
    }

}
