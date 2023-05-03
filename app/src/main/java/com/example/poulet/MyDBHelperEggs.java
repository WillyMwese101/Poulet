package com.example.poulet;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelperEggs extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static int total1 = 0, total2 = 0, total3 = 0;

    public static final String DATABASE_NAME = "databaseeggs.db";
    public static final String TABLE_Collections = "Poul_Collections";

    //Table columns for the collections table
    public static final String Coll_ID = "Coll_ID";
    public static final String House_1 = "House1";
    public static final String House_2 = "House2";
    public static final String House_3 = "House3";
    public static final String Coll_Date = "Coll_Date";


    private static final String CREATE_Collections =
            "CREATE TABLE " + TABLE_Collections + "(" +
                    //"Coll_ID int PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "House1 int, " +
                    "House2 int, " +
                    "House3 int) ";
    //+ /*"Coll_Date DATETIME DEFAULT CURRENT_TIMESTAMP)*/")";

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_Collections);
    }

    public MyDBHelperEggs(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Collections);
    }

    public static void insertEggCollections(Context context, int House1, int House2, int House3){

        MyDBHelper dbHelper = new MyDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //putting values into the database
        ContentValues values = new ContentValues();
        values.put(House_1, House1);
        values.put(House_2, House2);
        values.put(House_3, House3);
        //values.put(Coll_Date, currentDate);

        db.insert(TABLE_Collections, null, values);

        db.close();




    }

    @SuppressLint("Range")
    public void viewCollections(Context context){
        // Open the database
        SQLiteDatabase db = this.getReadableDatabase();

        // Execute the SQL query
        Cursor cursor1 = db.rawQuery("SELECT SUM(House1) AS total FROM Poul_Collections", null);
        Cursor cursor2 = db.rawQuery("SELECT SUM(House2) AS total FROM Poul_Collections", null);
        Cursor cursor3 = db.rawQuery("SELECT SUM(House3) AS total FROM Poul_Collections", null);

        // Retrieve the total value

        if (cursor1.moveToFirst()) {
            total1 = cursor1.getInt(cursor1.getColumnIndex(House_1));
        }
        if (cursor2.moveToFirst()) {
            total2 = cursor2.getInt(cursor2.getColumnIndex(House_2));
        }
        if (cursor3.moveToFirst()) {
            total3 = cursor3.getInt(cursor3.getColumnIndex(House_3));
        }



        // Close the cursor and the database
        cursor1.close();
        cursor2.close();
        cursor3.close();
        db.close();

    }
}
