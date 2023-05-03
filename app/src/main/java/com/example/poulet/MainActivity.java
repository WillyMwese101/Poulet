package com.example.poulet;

import static com.example.poulet.MyDBHelper.TABLE_BIO;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText user, pwd;
    private TextView signup;
    MyDBHelper dbHelper = new MyDBHelper(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login_main);
        signup = (TextView) findViewById(R.id.Signup_main);
        user = (EditText) findViewById(R.id.username_main);
        pwd = (EditText) findViewById(R.id.pwd_main);


        // get reference to the context
        Context this_context = getApplicationContext();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Dashboard.class));
                /*
                boolean isValidLogin = checkLogin(user.getText().toString(), pwd.getText().toString());
                boolean isuservalid = dbHelper.isValidUser(user.getText().toString(), pwd.getText().toString());
                try {
                    if (isuservalid){

                        // create and display the toast
                        Toast.makeText(this_context, "Login Success!", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(MainActivity.this, Dashboard.class));
                    }else{
                        Toast.makeText(this_context, "Login failed! Please login with the correct username and password", Toast.LENGTH_SHORT).show();
                        user.setText("");
                        pwd.setText("");
                    }
                }catch (Exception e){
                    Toast.makeText(this_context, "Error connecting to the database!", Toast.LENGTH_SHORT).show();
                }*/

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Signup.class));
            }
        });


    }

    public boolean checkLogin(String username, String password) {
        boolean result=false;
        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BIO + "  WHERE Username=? AND Password1=?", new String[]{username, password});

            result = cursor.getCount() > 0;

            cursor.close();
            //db.close();
        }

        return result;
    }
}