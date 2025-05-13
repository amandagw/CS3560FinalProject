package com.library;

public abstract class LibraryItem implements MediaType {
    private int id; // ID for item
    private String title, creator, genre;   // fields for all media items

    // constructor initializes item with ID, title, creator, and genre
    public LibraryItem(int id, String title, String creator, String genre) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.genre = genre;
    }

    // getter method for ID
    public int getId() {
        return id;
    }

    // getter method for title
    public String getTitle() {
        return title;
    }

    // getter method for creator (author/director)
    public String getCreator() {
        return creator;
    }

    // getter method for genre
    public String getGenre() {
        return genre;
    }

    // setter method for title
    public void setTitle(String t) {
        title = t;
    }

    // setter method for creator (author/director)
    public void setCreator(String c) {
        creator = c;
    }

    // setter method for genre
    public void setGenre(String g) {
        genre = g;
    }

    // abstract method from MediaType interface; to return type name
    @Override
    public abstract String getTypeName();

    // abstract method to return item details
    public abstract String getDetails();
}
