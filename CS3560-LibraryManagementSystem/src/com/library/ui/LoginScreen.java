package com.library.ui;

import com.library.AuthService;
import com.library.User;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    private final JTextField txtUser = new JTextField(15);  // creates input field for username
    private final JPasswordField txtPass = new JPasswordField(15);  // creates input field for password

    // constructor initializes login screen
    public LoginScreen() {
        super("Library Login"); // sets window title
        setLayout(new GridBagLayout()); // sets gridbaglayout
        GridBagConstraints gbc = new GridBagConstraints();  // defines layout contstraints
        gbc.insets = new Insets(5, 5, 5, 5);    // sets padding around components

        // -- Username --

        // sets position for label
        gbc.gridx = 0;
        gbc.gridy = 0;
        // adds label for username
        add(new JLabel("Username:"), gbc);
        // moves to next column for username input
        gbc.gridx = 1;
        // adds username field
        add(txtUser, gbc);

        // -- Password --

        // sets position for label
        gbc.gridy = 1;
        gbc.gridx = 0;
        // adds label for password
        add(new JLabel("Password:"), gbc);
        // moves to next column for password input
        gbc.gridx = 1;
        // adds password field
        add(txtPass, gbc);

        // -- Login button --

        JButton btn = new JButton("Login"); // creates login button
        // moves to next row for button
        gbc.gridy = 2;
        gbc.gridx = 0;
        // sets button width to two columns
        gbc.gridwidth = 2;
        // adds login button
        add(btn, gbc);

        // defines action to be performed when login button is clicked
        btn.addActionListener(e -> {
            try {
                // calls authentication service with entered credentials
                User u = AuthService.login(
                        txtUser.getText(),
                        new String(txtPass.getPassword())); 
                dispose(); // closes login window once successfully logged in

                // checks if user is admin
                if (u.isAdmin()) {
                    // opens admin menu window
                    new AdminMenu();
                } else {
                    // opens user menu window and passes user ID as string
                    new UserMenu(String.valueOf(u.getId()));
                }

            } catch (Exception ex) {
                // handles any errors
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        pack(); // automatically sizes window based on components
        setLocationRelativeTo(null);    // centers window on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // exits program when window is closed
    }
}
