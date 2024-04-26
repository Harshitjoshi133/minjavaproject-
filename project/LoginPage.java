import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import javax.imageio.ImageIO;

import Apology.apology;


public class LoginPage extends Frame implements ActionListener {
    private TextField usernameField;
    private TextField passwordField;
    private Button loginButton;
    private Button registerButton;
    private BufferedImage backgroundImage;
    public LoginPage() {
        setTitle("Login Page");
        setSize(1295, 687); // Fixed size for the frame
        setLayout(null); // Use null layout to set absolute positions
        

  
        // Create components
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        
        usernameField = new TextField();
        passwordField = new TextField();
        passwordField.setEchoChar('*'); // Mask password characters

        loginButton = new Button("Login");
        loginButton.addActionListener(this);

        registerButton = new Button("Register");
        registerButton.addActionListener(this);
        
        Label loginLabel=new Label("Login");
        loginLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        

       
        // //Setting the Backgrounds and Foregrounds of the Fields
        // usernameLabel.setBackground(new Color(153,143,193));
        // passwordLabel.setBackground(new Color(153,143,193));
        // loginLabel.setBackground(new Color(153,143,193));
        // loginButton.setForeground(new Color(153,143,193));
        // registerButton.setForeground(new Color(153,143,193));
        // // Set absolute positions and sizes for each component
        
        loginLabel.setBounds(800,150,100,40);
        usernameLabel.setBounds(700, 250, 80, 20); // x, y, width, height
        usernameField.setBounds(790, 250, 120, 20);
        passwordLabel.setBounds(700, 280, 80, 20);
        passwordField.setBounds(790, 280, 120, 20);
        loginButton.setBounds(750, 320, 80, 30);
        registerButton.setBounds(840, 320, 80, 30);

        // Add components to the frame
        add(loginLabel);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);
        
        setVisible(true);

        // Handle window close event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if(username.isEmpty() || password.isEmpty()){
                new apology("Cannot Leave these positions empty");
                return;
            }
            
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaminiproject", "root", "Harshit@99");
                // Checking Username and password
                PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM users WHERE name=?");
                checkStatement.setString(1, username);
                ResultSet resultSet = checkStatement.executeQuery();
                String p="";
                if (resultSet.next()) {
                    System.out.println(resultSet.getString("password"));
                    //System.out.println(resultSet.getString("name"));
                    p =resultSet.getString("password");
                    
                    }
                if(p.equals(password)){
                    System.out.print("hello im here");
                    new MovieSelection(username);
                    setVisible(false);
                }   
                    
                
                 else{
                    new apology("Incorrect Username or Password");
                }

                    // Hide the login window


                //insertStatement.close();
                connection.close();
            } catch (SQLException | IOException ex) {
                new apology("Error: " + ex.getMessage());
            }
        }


  
            // Example: Open a new window (MovieSelection)
                    else if (e.getSource() == registerButton) {
            // Handle registration button click
            setVisible(false); // Hide the login window

            // Example: Open a new registration window
            new RegistrationPage();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the background image
        if (backgroundImage != null) {
            // Scale the image to fill the frame
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
