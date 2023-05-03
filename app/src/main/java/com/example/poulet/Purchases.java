package com.example.poulet;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Purchases extends AppCompatActivity {

    private Button AddPurchases, Dashboard_purchases;
    private EditText ItemPurchased, Quantity, CostPerItem, TotalPurchase;
    Context context = this;
    MyDBHelper dbHelper = new MyDBHelper(context);
    MyDBHelperPurchase Purchasehelper = new MyDBHelperPurchase(context);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        AddPurchases = (Button) findViewById(R.id.Add_purchase_main);
        Dashboard_purchases = (Button) findViewById(R.id.back_purchases);
        ItemPurchased = (EditText) findViewById(R.id.item_purchase);
        Quantity = (EditText) findViewById(R.id.Qtty_purchase);
        CostPerItem = (EditText) findViewById(R.id.costperitem);
        TotalPurchase = (EditText) findViewById(R.id.totalpurchaseexpense);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        AddPurchases.setOnClickListener(v -> {
            dbHelper.insertPurchase(context,
                    ItemPurchased.getText().toString(),
                    Integer.parseInt(Quantity.getText().toString()),
                            Double.parseDouble(CostPerItem.getText().toString()),
                            Double.parseDouble(TotalPurchase.getText().toString()));

            Toast.makeText(context, "Purchase Successfully Added!", Toast.LENGTH_SHORT).show();

            ItemPurchased.setText("");
            Quantity.setText("");
            CostPerItem.setText("");
            TotalPurchase.setText("");
        });


        Dashboard_purchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Purchases.this, Dashboard.class));
            }
        });

    }


}