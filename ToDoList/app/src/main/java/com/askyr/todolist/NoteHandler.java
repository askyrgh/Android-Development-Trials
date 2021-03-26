package com.askyr.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class NoteHandler extends DatabaseHelper {
    // This class is responsible for handling the transactions between app and SQL database
    // All the transaction and database handling logic is implemented in this class

    public NoteHandler(Context context) {
        super(context);
    }

    // Methods used for Create, Read, Update & Delete (CRUD) operations in the database

    public boolean create(Note note) {

        ContentValues values = new ContentValues();

        values.put("title", note.getTitle());
        values.put("description", note.getDescription());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isSuccessful = db.insert("Note", null, values) > 0;    // null values for attributes are allowed to be inserted as tuples

        db.close();

        return isSuccessful;
    }
}
