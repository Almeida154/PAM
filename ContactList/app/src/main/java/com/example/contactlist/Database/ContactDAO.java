package com.example.contactlist.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.contactlist.Model.Contact;
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

    public long insertBD(Contact contact){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameContact", contact.getNameContact());
        contentValues.put("numberContact", contact.getNumberContact());
        if(contact.getEmailContact().length() != 0) contentValues.put("emailContact", contact.getEmailContact());
        if(contact.getLandlineContact().length() != 0) contentValues.put("landlineContact", contact.getLandlineContact());
        if(contact.getNicknameContact().length() != 0) contentValues.put("nicknameContact", contact.getNicknameContact());
        return database.insert("tbContact", null, contentValues);
    }

    public List<Contact> selectDB(){
        List<Contact> contacts = new ArrayList<>();
        Cursor c = database.query("tbContact",
                new String[]{"nameContact", "numberContact", "emailContact", "landlineContact", "nicknameContact"},
                null, null, null, null, null);
        while(c.moveToNext()){
            Contact contact = new Contact(
                    c.getString(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4)
            );
            contacts.add(contact);
        }
        return contacts;
    }

    public List<Contact> selectDB(String number){
        List<Contact> contacts = new ArrayList<>();
        Cursor c = database.query("tbContact",
                new String[]{"nameContact", "numberContact", "emailContact", "landlineContact", "nicknameContact"},
                null, null, null, null, null);
        while(c.moveToNext()){
            if(c.getString(1).equals(number)){
                Contact contact = new Contact(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)
                );
                contacts.add(contact);
            }
        }
        return contacts;
    }

    public void deleteDB(String contactNumber) {
        database.delete("tbContact", "numberContact like ?", new String[]{contactNumber});
    }

    public void updateDB(Contact contact, String contactNumber){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameContact", contact.getNameContact());
        contentValues.put("numberContact", contact.getNumberContact());
        if(contact.getEmailContact().length() != 0) contentValues.put("emailContact", contact.getEmailContact());
        if(contact.getLandlineContact().length() != 0) contentValues.put("landlineContact", contact.getLandlineContact());
        if(contact.getNicknameContact().length() != 0) contentValues.put("nicknameContact", contact.getNicknameContact());
        database.update("tbContact", contentValues, "numberContact like ?", new String[]{contactNumber});
    }

    public int countTB(){
        Cursor c = database.rawQuery("select * from tbContact", null);
        return c.getCount();
    }

    public int countTB(String number){
        Cursor c = database.rawQuery("select * from tbContact where numberContact like '" + number + "'", null);
        return c.getCount();
    }
}