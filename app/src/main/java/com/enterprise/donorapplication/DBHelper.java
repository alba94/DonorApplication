package com.enterprise.donorapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albal on 27/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static int Db_version = 1;
    private static final String DbName = "city.db";
    private static final String TableName = "cities";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";

    public DBHelper(Context context) {
        super(context, DbName, null, Db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TableName + "(" + KEY_ID + "INTEGER PRIMARY KEY" + KEY_NAME + "TEXT" + ")");

        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(1,'BERAT')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(2,'BULQIZË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ") VALUES(3,'DELVINË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(4,'DEVOLL')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(5,'DIBËR')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(6,'DURRËS')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(7,'ELBASAN')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(8,'FIER')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(9,'GJIROKASTËR')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(10,'GRAMSH')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(11,'HAS')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(12,'KAVAJË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ") VALUES(13,'KOLONJË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(14,'KORÇË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(15,'KRUJË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(16,'KUKËS')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(17,'KURBIN')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(18,'KUÇOVË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(19,'LEZHË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(20,'LIBRAZHD')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(21,'LUSHNJE')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(22,'MALLAKASTËR')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(23,'MAT')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(24,'MIRDITË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(25,'PEQIN')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(26,'POGRADEC')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(27,'PUKË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(28,'PËRMET')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(29,'SARANDË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(30,'SHKODËR')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(31,'SKRAPAR')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(32,'TEPELENË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(33,'TIRANË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(34,'TROPOJË')");
        db.execSQL("INSERT INTO " + TableName + "(" + KEY_ID + ", " + KEY_NAME + ")  VALUES(35,'VLORË')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);

    }

    public List<String> getAllCities() {

        List<String> userlist = new ArrayList<>();
        String query = "SELECT * FROM " + TableName + " group by " + KEY_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                userlist.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();

        db.close();
        return userlist;
    }

}
