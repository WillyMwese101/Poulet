package com.example.poulet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDBHelperPurchase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "purchasedb.db";

    // Database version
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_Purchases = "Purchases";

    //Table columns for purchases table
    public static final String Purchase_ID = "Purchase_ID";
    public static final String ItemPurchased = "ItemPurchased";
    public static final String Quantity = "Quantity";
    public static final String CostPerItem = "CostPerItem";
    public static final String TotalPurchase = "TotalPurchase";
    public static final String Transaction_Date = "Transaction_Date";

    private String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    private static final String CREATE_Purchases =
            "CREATE TABLE " + TABLE_Purchases + "(" +
                    "Purchase_ID INT NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "ItemPurchased Varchar(30), " +
                    "Quantity int, " +
                    "CostPerItem Double, " +
                    "TotalPurchase Double, " +
                    "Transaction_Date DATETIME DEFAULT CURRENT_TIMESTAMP)";
    public MyDBHelperPurchase(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db){
        //BioData Table
        //db.execSQL(CREATE_Bio);
        //Collections Table
        db.execSQL(CREATE_Purchases);
        //Purchases Table
        //db.execSQL(CREATE_Purchases);
        //Sales Table
        //db.execSQL(CREATE_Sales);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Purchases);


    }

    public void insertPurchase(Context context, String Item_Purchased, int Qtty, Double Cost_Per_Item, Double Total_Purchase){

        MyDBHelperPurchase dbHelper = new MyDBHelperPurchase(context);
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


}
