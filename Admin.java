import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Admin extends JFrame{


   
    private JLabel l1, l2;
    private JButton login;

    public Admin(){

        buildPanel();

    }

    public void buildPanel(){
        JFrame f = new JFrame("Login");
        l1 = new JLabel("Username");
        l1.setBounds(30, 15, 100, 30);

        l2 = new JLabel("Password");  //Create label Password
        l2.setBounds(30, 50, 100, 30);

        JTextField F_user = new JTextField(); //Create text field for username
        F_user.setBounds(110, 15, 200, 30);

        JPasswordField F_pass = new JPasswordField(); //Create text field for password
        F_pass.setBounds(110, 50, 200, 30);

        JButton login_but = new JButton("Login");//creating instance of JButton for Login Button
        login_but.setBounds(130, 90, 80, 25);//Dimensions for button


        f.add(l1);
        f.add(l2);
        f.add(F_user);
        f.add(F_pass);
        f.add(login_but);

        f.setSize(400,180);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    public static void main(String[] args){
        new Admin();
    }
}
