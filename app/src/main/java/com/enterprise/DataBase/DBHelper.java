package com.enterprise.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.enterprise.dagger.ApplicationContext;
import com.enterprise.dagger.DatabaseInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by albal on 27/05/2017.
 */

@Singleton
public class DBHelper extends SQLiteOpenHelper {

    @Inject
    public DBHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo Integer version) {
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
