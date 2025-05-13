package com.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Data {
    // lists (datastructures) to store all data (library items, transactions, users)
    public static final List<LibraryItem> items = new ArrayList<>();
    public static final List<Transaction> transactions = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();

    // ID counters to automatically generated IDs
    private static int nextItemId = 1;
    private static int nextTxId = 1;
    private static int nextUserId = 1;

    // initializer that automatically seeds data the class loads
    static {
        seedDefaults();
    }

    // method to store default users and items
    private static void seedDefaults() {
        // seed users
        users.add(new Librarian(nextUserId++, "admin"));
        users.add(new Member(nextUserId++, "member"));
        // seed items
        items.add(new PrintedBook(nextItemId++, "1984", "Orwell", "Dystopian", 328));
        items.add(new EBook(nextItemId++, "Clean Code", "Martin", "CS", 1.2));
        items.add(new Movie(nextItemId++, "The Matrix", "Wachowski", "Sci-Fi", 136));
    }

    // wipes all data and re-seeds default users and items
    public static void resetAll() {
        // clears all items, transactions, and users
        items.clear();
        transactions.clear();
        users.clear();

        // reset ID counters
        nextItemId = 1; 
        nextTxId = 1;
        nextUserId = 1;

        seedDefaults(); // re-ssed initial data
    }

    // returns next available item ID and increments counter
    public static int nextItemId() {
        return nextItemId++;
    }

    // returns next available transaction ID and increments counter
    public static int nextTxId() {
        return nextTxId++;
    }

    // returns next available user ID and increments counter
    public static int nextUserId() {
        return nextUserId++;
    }

    // method to authenticate user given a username and password
    // - admins use password "admin" and members use password "member"
    // returns optional user if authentication succeeds
    public static Optional<User> authenticate(String u, String p) {
        for (User x : users) {
            // checks if username matches
            if (x.getUsername().equals(u)) {
                // checks username and password combination
                if ((x.isAdmin() && "admin".equals(p)) ||
                        (!x.isAdmin() && "member".equals(p))) {
                    return Optional.of(x);  // valid, returns user
                }
            }
        }
        return Optional.empty();    // invalid, returns empty
    }
}
