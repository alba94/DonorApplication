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

    private static final String BloodType = "bloodType";

    private static final String BloodKEY_ID = "ID";
    private static final String BloodKEY_NAME = "NAME";

    private SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context, DbName, null, Db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BloodType + " (" + BloodKEY_ID + " INTEGER PRIMARY KEY " + BloodKEY_NAME + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + CITIES + " (" + KEY_ID + " INTEGER PRIMARY KEY " + KEY_NAME + " TEXT " + KEY_VALIDITY + " INTEGER" + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BloodType);
        db.execSQL("DROP TABLE IF EXISTS " + CITIES);
        onCreate(db);

    }

    public List<City> displayCities() {
        db = this.getWritableDatabase();
        List<City> listaQyteteve = DataService.getAllCities();
        ContentValues ct = new ContentValues();
        assert listaQyteteve != null;
        for (City city : listaQyteteve) {
            ct.put(KEY_ID, city.getIdcity());
            ct.put(KEY_NAME, city.getName());
            ct.put(KEY_VALIDITY, city.getValidity());
            db.insert(CITIES, null, ct);

        }

        return listaQyteteve;

    }

    public List<String> getCity() {
        List<String> list = new ArrayList<>();
        db = this.getReadableDatabase();
        String query = "SELECT name FROM " + CITIES;
        Cursor res = db.rawQuery(query, null);
        if (res.moveToFirst()) {
            do {
                list.add(res.getString(res.getColumnIndex("name")));
            } while (res.moveToNext());
        }
        db.close();
        res.close();
        return list;
    }


}
