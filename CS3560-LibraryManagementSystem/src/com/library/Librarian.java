package com.library;

public class Librarian extends User {
    // cnostructor initializes librarian with ID and username
    public Librarian(int id, String u) {
        super(id, u);   // calls parent constructor
    }

    // overrides isAdmin method from User.java to indicate librarian is an admin
    @Override  
    public boolean isAdmin() {
        return true;
    }
}
