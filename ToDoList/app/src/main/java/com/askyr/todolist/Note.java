package com.askyr.todolist;

public class Note {
    // This class will be used to handle the data for each Note object so it can be stored in database
    // and the value can be fetched to display on UI components through the NoteHolder class instance

    private int id; // unique id will be used to store the Note object in SQLite database
    private String title;
    private String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
