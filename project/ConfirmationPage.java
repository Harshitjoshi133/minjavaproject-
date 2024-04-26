import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ConfirmationPage extends Frame implements ActionListener{
    private Button moreBookBtn;
    private Button LogoutButton;
    private Button  Detailbutton;
    private Button MovieButton;
    private  String user;
    private String movie;
    private String time;
    private String date;
    private String tableName;
    
    public ConfirmationPage(String user,String movie,String tableName,String time,String date,int seat){
        this.user=user;
        this.movie=movie;
        this.time=time;
        this.date=date;
        this.tableName=tableName;
        setSize(1295,687);
        setLayout(null); 
        Label label=new Label("Seat number"+"  "+seat+" "+"is Confirmed");
        label.setBounds(550, 300, 200, 40);
        add(label);
        moreBookBtn=new Button("Book More Seats!");
        moreBookBtn.addActionListener(this);
        moreBookBtn.setBounds(500,400,130,20);
        add(moreBookBtn);
        LogoutButton=new Button("Logout");
        LogoutButton.addActionListener(this);
        LogoutButton.setBounds(650, 400, 130, 20);
        add(LogoutButton);

        Detailbutton=new Button("See More details");
        Detailbutton.addActionListener(this);
        Detailbutton.setBounds(500, 500, 130, 20);
        add(Detailbutton);

        MovieButton=new Button("Book Another Movie");
        MovieButton.setBounds(650, 500, 130, 20);
        MovieButton.addActionListener(this);
        add(MovieButton);
        
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Close the window
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //For booking more seats
        if(e.getSource()==moreBookBtn){
            setVisible(false);
            new SeatSelectionFrame(user, movie, time, date);
            return;
        }
        // to logout from the web site
        if(e.getSource()==LogoutButton){
            setVisible(false);
            new LoginPage();
            return;
        }
        if(e.getSource()==Detailbutton){
            new Detailpage(user,tableName);
        }
        if(e.getSource()==MovieButton){
            try {
                setVisible(false);
                new MovieSelection(user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
