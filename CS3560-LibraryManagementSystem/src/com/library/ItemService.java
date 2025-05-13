package com.library;

import java.util.*;

public class ItemService {
    // returns new list containing all items in library
    public static List<LibraryItem> all() {
        return new ArrayList<>(Data.items); // creates copy of item list
    }

    // adds new library item to system
    public static void add(LibraryItem it) {
        Data.items.add(it);
    }
}
