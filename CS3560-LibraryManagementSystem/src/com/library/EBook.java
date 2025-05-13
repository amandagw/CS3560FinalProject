package com.library;

public class EBook extends LibraryItem {
    private double sizeMB;  // stores file size of EBook (megabytes)

    // constructor initializes all EBook field
    public EBook(int id, String title, String author, String genre, double sizeMB) {
        super(id, title, author, genre);    // calls parent constructor
        this.sizeMB = sizeMB;   // sets specific size of EBook
    }

    // getter method to return size of EBook
    public double getSizeMB() {
        return sizeMB;
    }

    // setter method to update size of Ebook
    public void setSizeMB(double s) {
        sizeMB = s;
    }

    // overrides abstract method from MediaType to return type name
    @Override
    public String getTypeName() {
        return "EBook";
    }

    // overrides abstract method from LibraryItem.java to return formatted details
    @Override
    public String getDetails() {
        return String.format("%s, %.2f MB", super.getTitle(), sizeMB);  // returns title and size formatted as string
    }
}
