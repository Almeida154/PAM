package com.example.contactbook;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ContactDAO {

    // Properties

    private Connection connection;
    SQLiteDatabase database;

    // Methods

    public ContactDAO(Context context) {
        connection = new Connection(context);
        database = connection.getWritableDatabase();
    }

    public long insert(Contact contact){
        ContentValues values = new ContentValues();
        values.put("nameContact", contact.getName());
        values.put("emailContact", contact.getEmail());
        values.put("telephoneContact", contact.getTelephone());
        values.put("pointContact", contact.getPoint());
        values.put("messageContact", contact.getMessage());
        return database.insert("tbContact", null, values);
    }
}