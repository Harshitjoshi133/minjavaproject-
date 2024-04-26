package Apology;
import java.awt.*;
import java.awt.event.*;
 
public class apology extends Frame {
    //private String s;
    public apology(String str){
        setSize(600,200);
        setLayout(new GridLayout(6, 1)); 
        Label lb=new Label(str);
        System.out.println(str);
        TextField t=new TextField(100);
        t.setText(str);
        t.setEnabled(false);
        //t.setBounds(10, 0, 600, 20);
        lb.setBounds(10, 0, 1000, 100);
        add(t);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Close the window
            }
        });
    }
}