package com.askyr.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NotesDatabase";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);// This will call the constructor from parent class SQLiteOpenHelper
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // This method is called when database is created

        String sqlQuery = "CREATE TABLE Note ( id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT ) ";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This method is called when the database version is changed

        String sqlQuery = "DROP TABLE IF EXISTS Note";
        db.execSQL(sqlQuery);
        onCreate(db);
    }
}
