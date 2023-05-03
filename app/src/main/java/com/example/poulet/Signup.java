package com.example.poulet;

import static com.example.poulet.MyDBHelper.DateOfBirth;
import static com.example.poulet.MyDBHelper.Emails;
import static com.example.poulet.MyDBHelper.Full_Name;
import static com.example.poulet.MyDBHelper.Password1;
import static com.example.poulet.MyDBHelper.Password2;
import static com.example.poulet.MyDBHelper.TABLE_BIO;
import static com.example.poulet.MyDBHelper.Usersname;

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

import java.net.PasswordAuthentication;
import java.util.Date;

public class Signup extends AppCompatActivity {

    private Button Signup;
    private EditText Fullname, DOB, Email, Username, Pwd, Pwd2;
    MyDBHelper dbHelper = new MyDBHelper(this);

    Context context = this;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Signup = (Button) findViewById(R.id.signup);
        Fullname = (EditText) findViewById(R.id.Fullname_signup);
        DOB = (EditText) findViewById(R.id.D_O_B);
        Email = (EditText) findViewById(R.id.Email_signup);
        Username = (EditText) findViewById(R.id.Username_signup);
        Pwd = (EditText) findViewById(R.id.pwd_signup1);
        Pwd2 = (EditText) findViewById(R.id.pwd_signup2);

        SQLiteDatabase db;

        MyDBHelper dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                dbHelper.insertBioData(context,
                        Fullname.getText().toString(),
                        DOB.getText().toString(),
                        Email.getText().toString(),
                        Username.getText().toString(),
                        Pwd.getText().toString(),
                        Pwd2.getText().toString());

                /*ContentValues values = new ContentValues();
                values.put(Full_Name, Fullname.getText().toString());
                values.put(DateOfBirth, DOB.getText().toString());
                values.put(Emails, Email.getText().toString());
                values.put(Usersname, Username.getText().toString());
                values.put(Password1, Pwd.getText().toString());
                values.put(Password2, Pwd2.getText().toString());

                db.insert(TABLE_BIO, null, values);

                db.close();*/



                // get reference to the context
                Context this_context = getApplicationContext();

                // create and display the toast
                Toast.makeText(this_context, "Congratulations, Successfully Registered!", Toast.LENGTH_SHORT).show();


                startActivity(new Intent(Signup.this, MainActivity.class));
            }
        });
    }



}