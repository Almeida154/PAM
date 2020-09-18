package com.example.contactbook;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Connection extends SQLiteOpenHelper {

    // Properties

    private static final String dbName = "dbSchedule.db";
    private static final int version = 1;

    // Constructor

    public Connection(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    // Methods

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tbContact(" +
                "idContact integer primary key autoincrement," +
                "nameContact varchar(25)," +
                "emailContact varchar(25)," +
                "telephoneContact varchar(15), " +
                "pointContact varchar(25), " +
                "messageContact varchar(25))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + dbName);
        onCreate(db);
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tbContact", null);
        return cursor;
    }

    public boolean verifyEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select emailContact from tbContact where emailContact like '" + email + "'", null);
        if(cursor.getCount() > 0) return true;
        else return false;
    }
}
