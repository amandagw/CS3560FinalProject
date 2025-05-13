package com.library;

import java.time.LocalDate;

public class Transaction {
    private int id, userId, itemId; // stores transaction ID, user ID, and item ID
    private LocalDate borrowDate, dueDate;  // stores date item was borred and date item needs to be returned by

    // constructor initializes transaction with ID, user ID, and item ID
    public Transaction(int id, int userId, int itemId,
            LocalDate borrowDate, LocalDate dueDate) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    // getter method for transaction ID
    public int getId() {
        return id;
    }

    // getter method for user ID
    public int getUserId() {
        return userId;
    }   

    // getter method for item ID
    public int getItemId() {
        return itemId;
    }

    // getter method for date item is borrowed
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    // getter method for date item is due to be returned
    public LocalDate getDueDate() {
        return dueDate;
    }
}
