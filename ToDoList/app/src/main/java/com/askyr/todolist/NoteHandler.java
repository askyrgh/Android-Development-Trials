package com.askyr.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NoteHandler extends DatabaseHelper {
    // This class is responsible for handling the transactions between app and SQL database
    // All the transaction and database handling logic is implemented in this class

    public NoteHandler(Context context) {
        super(context);
    }

    // Methods used for Create, Read, Update & Delete (CRUD) operations in the database

    public boolean create(Note note) {
        // Method to create ana insert a new tuple(note) in the database

        ContentValues values = new ContentValues();

        values.put("title", note.getTitle());
        values.put("description", note.getDescription());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isSuccessful = db.insert("Note", null, values) > 0;    // null values for attributes are allowed to be inserted as tuples

        db.close();

        return isSuccessful;
    }

    public ArrayList<Note> readNotes() {
        // Method to retrieve all the tuples(notes) from the database

        ArrayList<Note> notes = new ArrayList<>();

        String sqlQuery = "SELECT * FROM Note ORDER BY id ASC";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery, null);  // cursor for navigating through the database

        if(cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                Note note = new Note(title, description);
                note.setId(id);
                notes.add(note);

            } while (cursor.moveToNext());  // if moving to next tuple is possible (like a curr pointer in linked list)

            cursor.close();
            db.close();
        }
        return notes;
    }

    public Note readSingleNote(int idArg) {
        // Method to retrieve a single tuple from the database with the corresponding id passed as argument

        Note note = null;

        String sqlQuery = "SELECT * FROM Note WHERE id=" + idArg;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst())
        {
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String description = cursor.getString(cursor.getColumnIndex("description"));

            note = new Note(title, description);
            note.setId(id);
        }
        cursor.close();
        db.close();
        return note;
    }

    public boolean updateNote(Note note) {
        // Method to update values of a tuple in the database with their corresponding id

        ContentValues values = new ContentValues();

        values.put("title", note.getTitle());
        values.put("description", note.getDescription());
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isSuccessful = db.update("Note", values, "id='" + note.getId() + "'", null) > 0;

        db.close();

        return isSuccessful;
    }

    public boolean deleteNote(int idArg) {
        // Method to delete a particular tuple in the database with their corresponding id

        boolean isDeleted;

        SQLiteDatabase db = this.getWritableDatabase();

        isDeleted = db.delete("Note", "id='" + idArg + "'", null) > 0;

        db.close();

        return isDeleted;
    }
}
