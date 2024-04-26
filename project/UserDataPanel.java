import java.awt.*;
import java.sql.*;
public class UserDataPanel extends Panel {
    private Label seatNoLabel;
    private Label usernameLabel;
    private Label bookingTimeLabel;
    //private Label paymentLinkLabel;

    public UserDataPanel(int seatNo, String username, Timestamp bookingTime, String paymentLink) {
        setLayout(new GridLayout(1, 4, 10, 10)); // Layout for displaying data in one row

        seatNoLabel = new Label("SeatNo: " + seatNo);
        usernameLabel = new Label("Username: " + username);
        bookingTimeLabel = new Label("BookingTime: " + bookingTime.toString());
        //paymentLinkLabel = new Label("PaymentLink: " + paymentLink);

        add(seatNoLabel);
        add(usernameLabel);
        add(bookingTimeLabel);
        //add(paymentLinkLabel);
    }
}
