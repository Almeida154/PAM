package com.example.contactlist.Database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Connection extends SQLiteOpenHelper {

    // Properties

    private static final String dbName = "dbContactList.db";
    private static final int version = 1;

    // Constructor

    public Connection(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table tbContact(" +
                        "idContact integer primary key autoincrement," +
                        "nameContact varchar(25) not null," +
                        "numberContact varchar(16) not null," +
                        "emailContact varchar(25)," +
                        "landlineContact varchar(15)," +
                        "nicknameContact varchar(25)"
             + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tbContact");
        onCreate(db);
    }
}