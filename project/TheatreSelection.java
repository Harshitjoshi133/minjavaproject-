import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class TheatreSelection extends Frame implements ActionListener {

    private String user;
    private String movie;
    private LocalTime[] bookingTimes;
    private Button[] timebutton;
    private Button logoutButton;
    public TheatreSelection(String movieName, String userName) {
        user = userName;
        movie=movieName;
        setTitle(movieName + "  " + userName);
        setSize(1295, 687);
        setBackground(Color.GRAY);
        setLocationRelativeTo(null);
        setLayout(null);

        // Header for the App
        logoutButton = new Button("Logout");
        logoutButton.setBounds(1150, 10, 120, 60);
        logoutButton.setBackground(Color.GRAY);
        logoutButton.addActionListener(this);
        // Add ActionListener for LogoutButton here

        Label titleLabel = new Label("Theatre Selection");
        titleLabel.setBounds(550, 10, 200, 100);

        Panel panel = new Panel();
        panel.setBounds(50, 120, 1200, 500);
        panel.setBackground(Color.white);
        LocalTime currentTime = LocalTime.now();
        bookingTimes = new LocalTime[3]; 
        bookingTimes[0]=LocalTime.of(11, 30);
        bookingTimes[1]=LocalTime.of(16, 30);
        bookingTimes[2]=LocalTime.of(19, 30);
        timebutton=new Button[3];
        int x=100;
        int y=100;
        for (int i=0;i<3;i++) {
            if (currentTime.isBefore(bookingTimes[i])) {
                timebutton[i] = new Button(bookingTimes[i].toString());
                timebutton[i].addActionListener(this);
                timebutton[i].setBounds(x, y, 100, 120);
                x=x+100;
                y=y+100;
                panel.add(timebutton[i]);
            }
        }

        add(logoutButton);
        add(titleLabel);
        add(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logoutButton){
            setVisible(false);
            new LoginPage();
        }
        //LocalDateTime book;
        for(Button btn:timebutton){
            if(e.getSource()==btn){
                String time=btn.getLabel();
                LocalDate currentdate=LocalDate.now();
                new SeatSelectionFrame(user,movie,time,currentdate.toString());
            }
        }
       
    }
    public static void main(String[] args) {
        new TheatreSelection("noob","chup");
    }
}


