package com.enterprise.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dlika on 6/2/2017.
 */


public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context,
                    String dbName,
                    Integer version) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cities(id integer primary key, name text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
