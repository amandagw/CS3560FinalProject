package com.library.ui;

import com.library.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class AdminMenu extends JFrame {
  // constructor initializes admin menu window
  public AdminMenu() {
    super("Admin Functions"); // sets window title

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(600, 200);
    setLayout(null);

    // -- Buttons -- 

    // reset button - button to reset all data
    JButton btnReset = new JButton("Reset");
    btnReset.setBounds(20, 20, 120, 25);  // sets position on window
    btnReset.addActionListener(e -> {
      Data.resetAll();  // resets all stored data
      JOptionPane.showMessageDialog(this, "Data reset!"); // displays success message
    });
    add(btnReset);  // adds button to window

    // view items button - button to view all items in library
    JButton btnViewItems = new JButton("View Item");
    btnViewItems.setBounds(150, 20, 120, 25); // sets position on window
    btnViewItems.addActionListener(e -> {
      // opens new window to show available items
      JFrame f = new JFrame("Items Available");
      f.setSize(500, 300);
      f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      // defines table columns for library items
      String[] cols = { "ID", "Type", "Title", "Creator", "Genre", "Details" };
      DefaultTableModel m = new DefaultTableModel(cols, 0);

      // fills table with all library items
      for (LibraryItem it : ItemService.all()) {
        m.addRow(new Object[] {
            it.getId(),
            it.getTypeName(),
            it.getTitle(),
            it.getCreator(),
            it.getGenre(),
            it.getDetails()
        });
      }

      // displays and makes table scrollable
      JTable tbl = new JTable(m);
      f.add(new JScrollPane(tbl));
      f.setLocationRelativeTo(this);
      f.setVisible(true);
    });
    add(btnViewItems);  // adds button to window

    // view users button - button to view all users
    JButton btnViewUsers = new JButton("View Users");
    btnViewUsers.setBounds(280, 20, 120, 25); // sets position on window
    btnViewUsers.addActionListener(e -> {
      // opens new window to show all users
      JFrame f = new JFrame("Users List");
      f.setSize(400, 200);
      f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      // defines table columns for list of users
      String[] cols = { "UID", "Username", "Admin" };
      DefaultTableModel m = new DefaultTableModel(cols, 0);

      // fills table with user info
      for (User u : Data.users) {
        m.addRow(new Object[] { u.getId(), u.getUsername(), u.isAdmin() });
      }

      // displays and makes table scrollable
      JTable tbl = new JTable(m);
      f.add(new JScrollPane(tbl));
      f.setLocationRelativeTo(this);
      f.setVisible(true);
    });
    add(btnViewUsers);  // adds button to window

    // view issued items button - button to view all currently issued items
    JButton btnViewIssued = new JButton("View Issued Item");
    btnViewIssued.setBounds(410, 20, 160, 25);  // sets position on window
    btnViewIssued.addActionListener(e -> {
      // opens new window to show issued transactions
      JFrame f = new JFrame("Issued Items");
      f.setSize(600, 300);
      f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      // defines table columns for transactions
      String[] cols = { "IID", "UID", "MID", "Borrowed", "Due", "Overdue Days" };
      DefaultTableModel m = new DefaultTableModel(cols, 0);
      
      // fills table with transaction history
      for (Transaction tx : ReportService.history()) {
        long overdue = java.time.temporal.ChronoUnit.DAYS
            .between(tx.getDueDate(), LocalDate.now());
        m.addRow(new Object[] {
            tx.getId(),
            tx.getUserId(),
            tx.getItemId(),
            tx.getBorrowDate(),
            tx.getDueDate(),
            overdue > 0 ? overdue : 0
        });
      }

      // displays and makes table scrollable
      JTable tbl = new JTable(m);
      f.add(new JScrollPane(tbl));
      f.setLocationRelativeTo(this);
      f.setVisible(true);
    });
    add(btnViewIssued); // adds button to window

    // add user button - button to add new user (admin or member)
    JButton btnAddUser = new JButton("Add User");
    btnAddUser.setBounds(20, 60, 120, 25);  // sets position on window
    btnAddUser.addActionListener(e -> {

      JTextField txtUser = new JTextField(10);  // creates input for username

      JRadioButton rbAdmin = new JRadioButton("Admin");
      JRadioButton rbMember = new JRadioButton("Member", true); // sets member as default selection
      ButtonGroup bg = new ButtonGroup();
      // only one option (admin or member) can be selected
      bg.add(rbAdmin);
      bg.add(rbMember);

      JPanel p = new JPanel(new GridLayout(0, 2, 5, 5));
      p.add(new JLabel("Username:"));
      p.add(txtUser);
      p.add(rbAdmin);
      p.add(rbMember);

      // creates new user and adds to system when confirmed
      int ok = JOptionPane.showConfirmDialog(
          this, p, "Enter User Details", JOptionPane.OK_CANCEL_OPTION);
      if (ok == JOptionPane.OK_OPTION) {
        int id = Data.nextUserId();
        User u = rbAdmin.isSelected()
            ? new Librarian(id, txtUser.getText())
            : new Member(id, txtUser.getText());
        Data.users.add(u);
        JOptionPane.showMessageDialog(this, "User added!"); // displays success message
      }
    });
    add(btnAddUser);  // adds button to window

    // add item button - button to add new item
    JButton btnAddItem = new JButton("Add Item");
    btnAddItem.setBounds(150, 60, 120, 25); // sets position on window
    btnAddItem.addActionListener(e -> {
      // creates dropdown with three options to select from: PrintedBook, EBook, or Movie
      String[] types = { "PrintedBook", "EBook", "Movie" };
      String type = (String) JOptionPane.showInputDialog(
          this, "Select type", "Add Item",
          JOptionPane.PLAIN_MESSAGE, null, types, types[0]);
      // closes window is dropdown is cancelled by user
      if (type == null)
        return;

      // creates input fields for item properties
      JTextField tTitle = new JTextField(), tCreator = new JTextField(), tGenre = new JTextField(), tExtra = new JTextField();

      JPanel p = new JPanel(new GridLayout(0, 2, 5, 5));
      p.add(new JLabel("Title:"));
      p.add(tTitle);
      p.add(new JLabel(type.equals("Movie") ? "Director" : "Author"));
      p.add(tCreator);
      p.add(new JLabel("Genre:"));
      p.add(tGenre);
      p.add(new JLabel(
          type.equals("PrintedBook") ? "Pages" : type.equals("EBook") ? "Size (MB)" : "Duration (min)"));
      p.add(tExtra);

      // displays pane with all fields and options to confirm (OK) or cancel
      int ok = JOptionPane.showConfirmDialog(
          this, p, "Enter Details", JOptionPane.OK_CANCEL_OPTION);
      // if user did not press OK, cancel process
      if (ok != JOptionPane.OK_OPTION)
        return;

      try {
        int id = Data.nextItemId(); // generates unique ID for new item
        LibraryItem it; // declares variable to store created item object

        // depending on selected type, choose which item subclass to instantiate
        switch (type) {
          case "EBook":
            it = new EBook(id,
                tTitle.getText(), tCreator.getText(),
                tGenre.getText(), Double.parseDouble(tExtra.getText()));
            break;
          case "Movie":
            it = new Movie(id,
                tTitle.getText(), tCreator.getText(),
                tGenre.getText(), Integer.parseInt(tExtra.getText()));
            break;
          default:
            it = new PrintedBook(id,
                tTitle.getText(), tCreator.getText(),
                tGenre.getText(), Integer.parseInt(tExtra.getText()));
        }

        // adds to item list
        ItemService.add(it);
        JOptionPane.showMessageDialog(this, "Item added!");
      } catch (Exception ex) {
        // handles invalid inputs
        JOptionPane.showMessageDialog(
            this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
    add(btnAddItem);  // adds button to window

    // issue item button - button to issue item to a user
    JButton btnIssueItem = new JButton("Issue Item");
    btnIssueItem.setBounds(280, 60, 120, 25); // sets position on window
    btnIssueItem.addActionListener(e -> {
      
      // creates input fields for issue properties
      JTextField tU = new JTextField(), tB = new JTextField(), tP = new JTextField();

      JPanel p = new JPanel(new GridLayout(0, 2, 5, 5));
      p.add(new JLabel("User ID:"));
      p.add(tU);
      p.add(new JLabel("Item ID:"));
      p.add(tB);
      p.add(new JLabel("Period (days):"));
      p.add(tP);

      // displays pane with all fields and options to confirm(OK) or cancel
      int ok = JOptionPane.showConfirmDialog(
          this, p, "Issue Item", JOptionPane.OK_CANCEL_OPTION);
      // if user doesn't press OK, cancels process
      if (ok != JOptionPane.OK_OPTION)
        return;

      try {
        // converts input values to integers for IDs and issues item
        TransactionService.borrow(
            Integer.parseInt(tU.getText()),
            Integer.parseInt(tB.getText()),
            Integer.parseInt(tP.getText()));
        JOptionPane.showMessageDialog(this, "Item issued!");  // displays success message
      } catch (Exception ex) {
        // handles invalid input
        JOptionPane.showMessageDialog(
            this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
    add(btnIssueItem);  // adds button to window

    // return button - button to return item
    JButton btnReturnItem = new JButton("Return Item");
    btnReturnItem.setBounds(410, 60, 120, 25);  // sets position on window
    btnReturnItem.addActionListener(e -> {
      // prompts user for transaction ID of item being returned
      String tx = JOptionPane.showInputDialog(
          this, "Transaction ID to return:");
      // exits if user cancels or inputs nothing
      if (tx == null)
        return;
      try {
        // returns item and calculates possible overdue fee
        double fee = TransactionService.returnItem(Integer.parseInt(tx));
        // displays message indicating calculated overdue fee
        JOptionPane.showMessageDialog(
            this, String.format("Late fee: $%.2f", fee));
        JOptionPane.showMessageDialog(this, "Item Returned!");  // displays sucess message
      } catch (Exception ex) {
        // handles any errors
        JOptionPane.showMessageDialog(
            this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }); 
    add(btnReturnItem); // adds button to window

    setLocationRelativeTo(null);  // centers window on screen
    setVisible(true); // makes window visible
  }
}
