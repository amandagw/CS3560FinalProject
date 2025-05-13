package com.library;

public class Member extends User {
    // constructor initializes member with ID and username
    public Member(int id, String u) {
        super(id, u);   // calls parent constructor
    }

    // overrides the isAdmin method to indicate that members are not admins
    @Override
    public boolean isAdmin() {
        return false;
    }
}
