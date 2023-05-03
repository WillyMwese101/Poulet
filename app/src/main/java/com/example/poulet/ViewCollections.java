package com.example.poulet;

import static com.example.poulet.MyDBHelper.House_1;
import static com.example.poulet.MyDBHelper.House_2;
import static com.example.poulet.MyDBHelper.House_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewCollections extends AppCompatActivity {

    private Button Addcollect;
    private TextView hs1, hs2, hs3;

    private int total1 = 0, total2 = 0, total3 = 0;
    Context context = this;
    private MyDBHelper myDBHelper = new MyDBHelper(context);

    SQLiteDatabase db;

    MyDBHelper dbHelper = new MyDBHelper(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_collections);

        /*SQLiteDatabase mDatabase = openOrCreateDatabase("database.db", MODE_PRIVATE, null);

        Object total = 0;

        Cursor cursor = mDatabase.rawQuery("SELECT SUM(House1) AS total FROM TABLE_Collections", null);*/


        Addcollect = (Button) findViewById(R.id.add_Coll_Viewcoll);
        hs1 = (TextView) findViewById(R.id.house1_viewcoll);
        hs2 = (TextView) findViewById(R.id.house2_viewcoll);
        hs3 = (TextView) findViewById(R.id.house3_viewcoll);

        Addcollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewCollections.this, Eggs.class));
            }
        });





        viewCollections();
        /*hs1.setText(MyDBHelper.total1);
        hs2.setText(MyDBHelper.total2);
        hs3.setText(MyDBHelper.total3);*/



    }

    @SuppressLint("Range")
    public void viewCollections(){
        // Open the database
        db = dbHelper.getReadableDatabase();

        // Execute the SQL query
        Cursor cursor1 = db.rawQuery("SELECT SUM(House1) AS total FROM Poul_Collections", null);
        Cursor cursor2 = db.rawQuery("SELECT SUM(House2) AS total FROM Poul_Collections", null);
        Cursor cursor3 = db.rawQuery("SELECT SUM(House3) AS total FROM Poul_Collections", null);

        // Retrieve the total value

        if (cursor1.moveToFirst()) {
            total1 = cursor1.getInt(cursor1.getColumnIndex(House_1));
            hs1.setText(MyDBHelper.total1);
        }
        if (cursor2.moveToFirst()) {
            total2 = cursor2.getInt(cursor2.getColumnIndex(House_2));
            hs2.setText(MyDBHelper.total2);
        }
        if (cursor3.moveToFirst()) {
            total3 = cursor3.getInt(cursor3.getColumnIndex(House_3));
            hs3.setText(MyDBHelper.total3);
        }



        // Close the cursor and the database
        cursor1.close();
        cursor2.close();
        cursor3.close();
        db.close();

    }
}