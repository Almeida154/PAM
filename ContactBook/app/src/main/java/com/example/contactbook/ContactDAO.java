package com.example.contactbook;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    // Properties

    private Connection connection;
    private SQLiteDatabase database;

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

    public List<Contact> list(){
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = database.query("tbContact", new String[]
                {"nameContact", "emailContact", "telephoneContact", "pointContact", "messageContact"},
                    null, null, null, null, null);
        while(cursor.moveToNext()){
            Contact contact = new Contact(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            contacts.add(contact);
        }
        return contacts;
    }

    public int delete(String email){
        return database.delete("tbContact", "emailContact like '" + email + "'", null);
    }
}