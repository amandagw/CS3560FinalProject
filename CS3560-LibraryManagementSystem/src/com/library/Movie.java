package com.library;

public class Movie extends LibraryItem {
    private int duration;   // stores duration of movie in minutes

    // constructor initializes movie with ID, title, director, genre, and duration)
    public Movie(int id, String title, String director, String genre, int duration) {
        super(id, title, director, genre);  // calls parent constructor
        this.duration = duration;   // sets duration of movie
    }

    // getter method for movie duration
    public int getDuration() {
        return duration;
    }

    // setter method to update movie's duration
    public void setDuration(int d) {
        duration = d;
    }

    // overrides method from MediaType to return type name
    @Override
    public String getTypeName() {
        return "Movie";
    }

    // overrides to return detailed description of movie
    @Override
    public String getDetails() {
        return String.format("%s, %d min", super.getTitle(), duration);
    }
}
