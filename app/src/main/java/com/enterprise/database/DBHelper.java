package com.enterprise.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.enterprise.responses.City;
import com.enterprise.services.DataService;

import java.util.List;

/**
 * Created by albal on 27/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static int Db_version = 1;
    private static final String DbName = "city.db";
    private static final String CITIES = "cities";
    private static final String BloodType = "bloodType";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";
    private static final String KEY_VALIDITY = "validity";


    public SQLiteDatabase mydb;

    public DBHelper(Context context) {
        super(context, DbName, null, Db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BloodType + "(" + KEY_ID + "INTEGER PRIMARY KEY" + KEY_NAME + "TEXT" + ")");
        db.execSQL("CREATE TABLE " + CITIES + "(" + KEY_ID + "INTEGER PRIMARY KEY" + KEY_NAME + "TEXT" + KEY_VALIDITY + "INTEGER" + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BloodType);
        db.execSQL("DROP TABLE IF EXISTS " + CITIES);
        onCreate(db);

    }

    public List<City> displayCities() {

        List<City> listaQyteteve= DataService.getAllCities();
        City city = (City) listaQyteteve;
        ContentValues ct = new ContentValues();
        if(listaQyteteve!=null) {
            ct.put(KEY_NAME, city.getName());

            mydb.insert(CITIES, null, ct);
        }
        return listaQyteteve;

    }

    public void getBloodType(String name) {


    }


}
