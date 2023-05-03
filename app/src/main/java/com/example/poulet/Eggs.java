package com.example.poulet;

import static com.example.poulet.MyDBHelper.House_1;
import static com.example.poulet.MyDBHelper.House_2;
import static com.example.poulet.MyDBHelper.House_3;
import static com.example.poulet.MyDBHelper.TABLE_Collections;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Eggs extends AppCompatActivity {

    private Button AddCollection, Dashboard_eggs, ViewCollect;
    private EditText house1, house2, house3;
    Context context = this;
    //MyDBHelper dbHelper = new MyDBHelper(context);
    MyDBHelperEggs Helper = new MyDBHelperEggs(context);
    private Context thiscontext = this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eggs);

        AddCollection = (Button) findViewById(R.id.Add_Coll);
        ViewCollect = (Button) findViewById(R.id.view_coll);
        Dashboard_eggs = (Button) findViewById(R.id.back_eggs);
        house1 = (EditText) findViewById(R.id.hse1);
        house2 = (EditText) findViewById(R.id.hse2);
        house3 = (EditText) findViewById(R.id.hse3);

        SQLiteDatabase db = Helper.getWritableDatabase();

        // get reference to the context
        Context this_context = getApplicationContext();

        //if(db!=null){
            AddCollection.setOnClickListener(v -> {
                Helper.insertEggCollections(thiscontext,
                        Integer.parseInt(house1.getText().toString()),
                        Integer.parseInt(house2.getText().toString()),
                        Integer.parseInt(house3.getText().toString()));

                /*//putting values into the database
                ContentValues values = new ContentValues();
                values.put(House_1, house1.getText().toString());
                values.put(House_2, house2.getText().toString());
                values.put(House_3, house3.getText().toString());
                //values.put(Coll_Date, currentDate);

                db.insert(TABLE_Collections, null, values);

                //db.close();*/


                Toast.makeText(this_context, "Collection Successfully Added!", Toast.LENGTH_SHORT).show();

                house1.setText("");
                house2.setText("");
                house3.setText("");
            });
        //}else{
            Toast.makeText(this_context, "Error connecting to the database!", Toast.LENGTH_SHORT).show();
       //}

        ViewCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Eggs.this, ViewCollections.class));
            }
        });

        Dashboard_eggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Eggs.this, Dashboard.class));
            }
        });


    }


}