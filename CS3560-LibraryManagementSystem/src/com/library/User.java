package com.library;

public abstract class User {
    private int id; // stores user ID
    private String username;    // stores username

    // constructor initializes user with ID and username
    public User(int id, String username) {
        this.id = id;   // sets ID
        this.username = username;   // sets username
    }

    // getter method for user ID
    public int getId() {
        return id;
    }

    // getter method for username
    public String getUsername() {
        return username;
    }

    // abstract method that determines if user is admin
    public abstract boolean isAdmin();
}
