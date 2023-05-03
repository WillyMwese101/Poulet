package com.example.poulet;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;
import static java.time.LocalDate.now;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.ContactsContract;

import androidx.annotation.RequiresApi;

import java.net.PasswordAuthentication;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;



public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database.db";

    // Database version
    public static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_BIO = "Poul_BioData";
    public static final String TABLE_Collections = "Poul_Collections";
    private static final String TABLE_Purchases = "Purchases";
    private static final String TABLE_Sales = "Poul_Sales";

    // Table columns for BioData table
    private static final String Person_ID = "Person_ID";
    public static final String Full_Name = "Full_Name";
    public static final String DateOfBirth = "DateOfBirth";
    public static final String Emails = "Emails";
    public static final String Usersname = "Username";
    public static final String Password1 = "Password1";
    public static final String Password2 = "Password2";

    //Table columns for the collections table
    public static final String Coll_ID = "Coll_ID";
    public static final String House_1 = "House1";
    public static final String House_2 = "House2";
    public static final String House_3 = "House3";
    public static final String Coll_Date = "Coll_Date";

    //Table columns for purchases table
    public static final String Purchase_ID = "Purchase_ID";
    public static final String ItemPurchased = "ItemPurchased";
    public static final String Quantity = "Quantity";
    public static final String CostPerItem = "CostPerItem";
    public static final String TotalPurchase = "TotalPurchase";
    public static final String Transaction_Date = "Transaction_Date";

    //Table columns for sales table
    public static final String Sale_ID = "Sale_ID";
    public static final String Eggs = "Eggs";
    public static final String Trays = "Trays";
    public static final String UnitPrice = "UnitPrice";
    public static final String TotalSale = "TotalSale";
    public static final String Sale_Date = "Sale_Date";

    static int total1 = 0, total2 = 0, total3 = 0;
    private String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


    //LocalDateTime createdAt = LocalDateTime.parse(cursor.getString(cursor.getColumnIndex("created_at")));
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    //String formattedCreatedAt = createdAt.format(formatter);


    // Create table SQL query
    private static final String CREATE_Bio =
            "CREATE TABLE " + TABLE_BIO + "(" +
                    "Full_Name Varchar NOT NULL, " +
                    "DateOfBirth Date NOT NULL, " +
                    "Emails Email NOT NULL, " +
                    "Username Varchar PRIMARY KEY NOT NULL, " +
                    "Password1 Varchar NOT NULL, " +
                    "Password2 Varchar NOT NULL)";

    private static final String CREATE_Collections =
            "CREATE TABLE " + TABLE_Collections + "(" +
                    //"Coll_ID int PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "House1 int, " +
                    "House2 int, " +
                    "House3 int) ";
                    //+ /*"Coll_Date DATETIME DEFAULT CURRENT_TIMESTAMP)*/")";

    private static final String CREATE_Purchases =
            "CREATE TABLE " + TABLE_Purchases + "(" +
                    "Purchase_ID INT NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "ItemPurchased Varchar(30), " +
                    "Quantity int, " +
                    "CostPerItem Double, " +
                    "TotalPurchase Double, " +
                    "Transaction_Date DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private static final String CREATE_Sales =
            "CREATE TABLE " + TABLE_Sales + "(" +
                    "Sale_ID int NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "Eggs int, " +
                    "Trays int, " +
                    "UnitPrice Double, " +
                    "TotalSale Double, " +
                    "Sale_Date DATETIME DEFAULT CURRENT_TIMESTAMP)";

    public MyDBHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db){
        //BioData Table
        //db.execSQL(CREATE_Bio);
        //Collections Table
        db.execSQL(CREATE_Collections);
        //Purchases Table
        //db.execSQL(CREATE_Purchases);
        //Sales Table
        //db.execSQL(CREATE_Sales);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Collections);


    }

    public void insertBioData(Context context, String Fullname, String DOB, String email,String Uname, String pwd, String pwd2){

        MyDBHelper dbHelper = new MyDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //putting values into the database
        ContentValues values = new ContentValues();
        values.put(Full_Name, Fullname);
        values.put(DateOfBirth, DOB);
        values.put(Emails, email);
        values.put(Usersname, Uname);
        values.put(Password1, pwd);
        values.put(Password2, pwd2);

        db.insert(TABLE_BIO, null, values);

        db.close();


    }

    public void insertEggCollections(Context context, int House1, int House2, int House3){

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

    @SuppressLint("SimpleDateFormat")
    public void insertPurchase(Context context, String Item_Purchased, int Qtty, Double Cost_Per_Item, Double Total_Purchase){

        MyDBHelper dbHelper = new MyDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //putting values into the database
        ContentValues values = new ContentValues();
        values.put(ItemPurchased, Item_Purchased);
        values.put(Quantity, Qtty);
        values.put(CostPerItem, Cost_Per_Item);
        values.put(TotalPurchase, Total_Purchase);
        values.put(Transaction_Date,  currentDate);

        db.insert(TABLE_Purchases, null, values);

        db.close();


    }

    public void insertSale(Context context, int Eggs_sold, int Trays_sold, Double Unit_Price, Double Total_Sale){

        MyDBHelper dbHelper = new MyDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //putting values into the database
        ContentValues values = new ContentValues();
        values.put(Eggs, Eggs_sold);
        values.put(Trays, Trays_sold);
        values.put(UnitPrice, Unit_Price);
        values.put(TotalSale, Total_Sale);
        values.put(Sale_Date, currentDate);

        db.insert(TABLE_Sales, null, values);

        db.close();


    }

    public boolean checkLogin(String username, String password) {
        boolean result;
        try (SQLiteDatabase db = this.getReadableDatabase()) {

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BIO + "  WHERE username=? AND password=?", new String[]{username, password});
            result = cursor.getCount() > 0;

            cursor.close();
            //db.close();
        }

        return result;
    }

    public boolean isValidUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "Username"
        };

        String selection = "Username = ? AND Password = ?";
        String[] selectionArgs = { username, password };

        Cursor cursor = db.query(
                TABLE_BIO,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }

    public List<String> getAllNames() {
        List<String> names = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select1 = "SELECT " + House_1 + " FROM " + TABLE_Collections;
        String select2 = "SELECT " + House_2 + " FROM " + TABLE_Collections;
        String select3 = "SELECT " + House_3 + " FROM " + TABLE_Collections;
        Cursor cursor1 = db.rawQuery(select1, null);
        if (cursor1.moveToFirst()) {
            do {
                names.add(cursor1.getString(cursor1.getInt(Integer.parseInt(House_1))));
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        db.close();
        return names;
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








