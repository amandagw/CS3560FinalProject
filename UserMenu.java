package com.library.ui;

import javax.swing.*;
import java.awt.event.*;

public class UserMenu extends JFrame {
    // constructor, accepts user ID and sets up UI
    public UserMenu(String UID) {
        super("User Functions"); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setSize(350, 200);
        setLayout(null); 
        
        // view books button
        JButton viewBtn = new JButton("View Books");
        viewBtn.setBounds(20, 20, 120, 25);
        viewBtn.addActionListener(e -> {
            JFrame j = new JFrame("Books Available");
            j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            j.setSize(350, 200);

            String[] cols = {"ID","Title","Author","Genre"};
            String[][] data = {
                {"1","1984","Orwell","Dystopian"},
                {"2","Clean Code","Martin","Programming"},
                {"3","The Matrix","Wachowski","Sci-Fi"}
            };
            JTable table = new JTable(data, cols);
            j.add(new JScrollPane(table));

            j.setLocationRelativeTo(this);
            j.setVisible(true);
        });
        add(viewBtn);

        // my books button
        JButton myBooksBtn = new JButton("My Books");
        myBooksBtn.setBounds(150, 20, 120, 25);
        myBooksBtn.addActionListener(e -> {
            JFrame j = new JFrame("My Books");
            j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            j.setSize(350, 200);

            String[] cols = {"TxID","BookID","Borrowed","Due"};
            String[][] data = {
                {"1","2","2025-05-01","2025-05-08"}
            };
            JTable table = new JTable(data, cols);
            j.add(new JScrollPane(table));

            j.setLocationRelativeTo(this);
            j.setVisible(true);
        });
        add(myBooksBtn);

        // issue book button
        JButton issueBtn = new JButton("Issue Book");
        issueBtn.setBounds(20, 60, 120, 25);
        issueBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Issue book form (stub)")
        );
        add(issueBtn);

        // return book button 
        JButton returnBtn = new JButton("Return Book");
        returnBtn.setBounds(150, 60, 120, 25);
        returnBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Return book form (stub)")
        );
        add(returnBtn);

        setLocationRelativeTo(null); // center window on screen
        setVisible(true); // make window visible
    }
}