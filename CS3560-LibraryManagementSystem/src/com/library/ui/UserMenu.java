package com.library.ui;

import com.library.ItemService;
import com.library.LibraryItem;
import com.library.Transaction;
import com.library.TransactionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserMenu extends JFrame {
    private final int userId;

    // constructor initializes user menu window
    public UserMenu(String UID) {
        super("User Functions"); // sets window title
        this.userId = Integer.parseInt(UID); // converts string to integer for user ID

        // sets window properties
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLayout(null);
        setLocationRelativeTo(null);

        // -- Buttons --

        // view Items button - button to view all available items
        JButton btnView = new JButton("View Items");
        btnView.setBounds(20, 20, 120, 25); // sets position on window
        btnView.addActionListener(e -> {
            // opens new window to show available items
            JFrame f = new JFrame("Items Available");
            f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            f.setSize(500, 300);

            // defines table columns for library items
            String[] cols = { "ID", "Type", "Title", "Creator", "Genre", "Details" };
            DefaultTableModel m = new DefaultTableModel(cols, 0);

            // fills tables with all library items
            for (LibraryItem it : ItemService.all()) {
                m.addRow(new Object[] {
                        it.getId(), it.getTypeName(),
                        it.getTitle(), it.getCreator(),
                        it.getGenre(), it.getDetails()
                });
            }

            // displays and makes table scrollable
            JTable tbl = new JTable(m);
            f.add(new JScrollPane(tbl), BorderLayout.CENTER);
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        });
        add(btnView);   // adds button to window

        // my items button - button to view user's borrowed items
        JButton btnMy = new JButton("My Items");
        btnMy.setBounds(150, 20, 120, 25);  // sets position on window
        btnMy.addActionListener(e -> {
            // opens new window to show this user's borrowed items
            JFrame f = new JFrame("My Items");
            f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            f.setSize(500, 300);

            // defines table columns for transactions
            String[] cols = { "TxID", "Item", "Borrowed", "Due" };
            DefaultTableModel m = new DefaultTableModel(cols, 0);

            // gets all transactions for this user
            List<Transaction> txs = TransactionService.forUser(userId);
            for (Transaction tx : txs) {
                // finds item that matches the transaction's item ID
                LibraryItem it = ItemService.all().stream()
                        .filter(i -> i.getId() == tx.getItemId())
                        .findFirst().orElse(null);
                // adds transaction row to table
                m.addRow(new Object[] {
                        tx.getId(),
                        it != null ? it.getTitle() : "n/a",
                        tx.getBorrowDate(),
                        tx.getDueDate()
                });
            }

            // displays and makes table scrollable
            JTable tbl = new JTable(m);
            f.add(new JScrollPane(tbl), BorderLayout.CENTER);
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        });
        add(btnMy); // adds button to window

        // borrow item button - button to borrow an item
        JButton btnBorrow = new JButton("Borrow Item");
        btnBorrow.setBounds(20, 60, 120, 25);   // sets position on window
        btnBorrow.addActionListener(e -> {
            // prompts user to input ID of item they want to borrow and number of days they
            // want to borrow it for
            String bid = JOptionPane.showInputDialog(this, "Item ID to Borrow:");
            String days = JOptionPane.showInputDialog(this, "Days to borrow:");
            try {
                // calls transaction service to borrow the item
                TransactionService.borrow(
                        userId,
                        Integer.parseInt(bid),
                        Integer.parseInt(days));
                JOptionPane.showMessageDialog(this, "Item Borrowed!"); // displays success message
            } catch (Exception ex) {
                // handles invalid input
                JOptionPane.showMessageDialog(
                        this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(btnBorrow); // adds button to window

        // return item button - button to return a borrowed item
        JButton btnReturn = new JButton("Return Item");
        btnReturn.setBounds(150, 60, 120, 25);  // sets position on window
        btnReturn.addActionListener(e -> {
            // prompts user to transaction ID of item they want to return
            String tx = JOptionPane.showInputDialog(
                    this, "Transaction ID to return:");
            try {
                // calls transaction service to return item
                double fee = TransactionService.returnItem(
                        Integer.parseInt(tx));
                JOptionPane.showMessageDialog(
                        this, String.format("Late fee: $%.2f", fee)); // displays overdue fee
                JOptionPane.showMessageDialog(this, "Item Returned!"); // displays success message
            } catch (Exception ex) {
                // handles any errors with return
                JOptionPane.showMessageDialog(
                        this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(btnReturn); // adds button to window

        setVisible(true); // makes window visible
    }
}
