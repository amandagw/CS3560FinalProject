package com.library;

public class PrintedBook extends LibraryItem {
    private int pages;  // stores number of pages in book

    // constructor intiailizes printed book with ID, title, author, genre, and number of pages)
    public PrintedBook(int id, String title, String author, String genre, int pages) {
        super(id, title, author, genre);    // calls parent constructor
        this.pages = pages; // sets number of pages for the book
    }

    // getter method for number of pages
    public int getPages() {
        return pages;
    }

    // setter method to update number of pages
    public void setPages(int p) {
        pages = p;
    }

    // overrides method from MediaType to return type name
    @Override
    public String getTypeName() {
        return "Printed Book";
    }

    // overrides method to return formatted string containing title and number of pages
    @Override
    public String getDetails() {
        return String.format("%s, %d pages", super.getTitle(), pages);
    }
}
