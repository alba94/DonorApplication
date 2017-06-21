package com.enterprise.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.enterprise.responses.City;
import com.enterprise.services.DataService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albal on 27/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static int Db_version = 1;
    private static final String DbName = "city.db";

    private static final String CITIES = "cities";

    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";
    private static final String KEY_VALIDITY = "validity";



    public DBHelper(Context context) {
        super(context, DbName, null, Db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + CITIES);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + CITIES + " (" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_VALIDITY + " INTEGER " + ")");
        initCities(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    private void initCities(SQLiteDatabase db) {
        List<City> listaQyteteve = DataService.getAllCities();
        ContentValues ct = new ContentValues();
        for (City city : listaQyteteve) {
            ct.put(KEY_ID, city.getIdcity());
            ct.put(KEY_NAME, city.getName());
            ct.put(KEY_VALIDITY, city.getValidity());
            db.insert(CITIES, null, ct);
        }
    }

    public List<String> getCity() {
        List<String> list = new ArrayList<>();
        String query = "SELECT name FROM " + CITIES;
        Cursor res = this.getReadableDatabase().rawQuery(query, null);
        if (res.moveToFirst()) {
            do {
                list.add(res.getString(res.getColumnIndex("name")));
            } while (res.moveToNext());
        }
        this.close();
        res.close();

        return list;
    }

    public int getCityId(String name) {
        String query = "SELECT id FROM " + CITIES + " where name=?";
        Cursor res = this.getReadableDatabase().rawQuery(query, new String[]{name});
        int id = 0;
        if (res.moveToFirst()) {
            id = res.getInt(res.getColumnIndex("id"));
        }
        this.close();
        res.close();
        return id;
    }






}
