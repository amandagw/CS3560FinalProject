package com.library;

public class AuthService {
    // method to login given a username and password
    public static User login(String u, String p) throws Exception {
        // calls authenticate method from Data.java
        // checks if matches a user -> returns user if it does, throws exception if it doesn't
        return Data.authenticate(u, p)
                .orElseThrow(() -> new Exception("Invalid credentials"));
    }
}
