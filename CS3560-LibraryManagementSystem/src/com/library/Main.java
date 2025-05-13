package com.library;

import javax.swing.SwingUtilities;
import com.library.ui.LoginScreen;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen().setVisible(true));   // creates new login screen and makes it visible
    }
}
