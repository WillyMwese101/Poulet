package com.example.poulet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sales extends AppCompatActivity {

    private Button AddSales, Dashboard_sales;
    private EditText NumberofEggs, Traysofeggs, UnitPrice, TotalSales;
    Context context = this;
    MyDBHelper dbHelper = new MyDBHelper(context);
    private Context this_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        AddSales = (Button) findViewById(R.id.addsales);
        Dashboard_sales = (Button) findViewById(R.id.back_sales);
        NumberofEggs = (EditText) findViewById(R.id.numberofeggs);
        Traysofeggs = (EditText) findViewById(R.id.traysofeggs);
        UnitPrice = (EditText) findViewById(R.id.unitprices);
        TotalSales = (EditText) findViewById(R.id.totalsales);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        AddSales.setOnClickListener(v -> {
            dbHelper.insertSale(context,
                    Integer.parseInt(NumberofEggs.getText().toString()),
                    Integer.parseInt(Traysofeggs.getText().toString()),
                    Double.parseDouble(UnitPrice.getText().toString()),
                    Double.parseDouble(TotalSales.getText().toString()));

            Toast.makeText(this_context, "Sale Successfully Added!", Toast.LENGTH_SHORT).show();

        });

        Dashboard_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sales.this, Dashboard.class));
            }
        });


    }
}