package com.library.ui;

import javax.swing.*;
import java.awt.event.*;

public class AdminMenu extends JFrame {
    public AdminMenu() {
        super("Admin Functions");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 200);
        setLayout(null);

        // row 1
        JButton resetBtn     = new JButton("Create/Reset");
        JButton viewBooksBtn = new JButton("View Books");
        JButton viewUsersBtn = new JButton("View Users");
        JButton viewIssBtn   = new JButton("View Issued Books");

        resetBtn    .setBounds( 20, 20, 120, 25);
        viewBooksBtn.setBounds(150, 20, 120, 25);
        viewUsersBtn.setBounds(280, 20, 120, 25);
        viewIssBtn  .setBounds(410, 20, 160, 25);

        add(resetBtn);
        add(viewBooksBtn);
        add(viewUsersBtn);
        add(viewIssBtn);

        // row 2
        JButton addUserBtn  = new JButton("Add User");
        JButton addBookBtn  = new JButton("Add Book");
        JButton issueBtn    = new JButton("Issue Book");
        JButton returnBtn   = new JButton("Return Book");

        addUserBtn.setBounds( 20, 60, 120, 25);
        addBookBtn.setBounds(150, 60, 120, 25);
        issueBtn  .setBounds(280, 60, 120, 25);
        returnBtn .setBounds(410, 60, 120, 25);

        add(addUserBtn);
        add(addBookBtn);
        add(issueBtn);
        add(returnBtn);

        // listeners (placeholders for now)
        resetBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Database Created/Reset!")
        );
        viewBooksBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Showing all books...")
        );
        viewUsersBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Showing all users...")
        );
        viewIssBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Showing all issued books...")
        );
        addUserBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Enter user details (stub)")
        );
        addBookBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Enter book details (stub)")
        );
        issueBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Issue book form (stub)")
        );
        returnBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Return book form (stub)")
        );

        setLocationRelativeTo(null);
        setVisible(true);
    }
}