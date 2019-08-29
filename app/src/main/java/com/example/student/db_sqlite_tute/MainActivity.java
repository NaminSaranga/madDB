package com.example.student.db_sqlite_tute;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.db_sqlite_tute.Database.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText editTextUsername, editTextPassword;
    DBHelper dbHelper;
    Button buttonAdd, buttonSelectAll, buttonSignIn, buttonDelete, buttonUpdate;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        context = getApplicationContext();

        editTextUsername = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSelectAll = findViewById(R.id.buttonSelect);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.addInfo(editTextUsername.getText().toString(), editTextPassword.getText().toString());
                Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT).show();

            }
        });

        buttonSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List data = dbHelper.readAllInfo();
                for(int i = 0; i< data.size();i++){

                    Log.v("Data"+i,data.get(i).toString());
                  // Log.v("users ",data.get(i).toString());
                }

                Toast.makeText(getApplicationContext(),"Retrieved Successfully",Toast.LENGTH_SHORT).show();

            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean status = dbHelper.readInfo(editTextUsername.getText().toString(),editTextPassword.getText().toString(),context);
               if (status){
                   Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
                   myIntent.putExtra("key", status);
                   MainActivity.this.startActivity(myIntent);
               }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteInfo(editTextUsername.getText().toString(), context);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateInfo(editTextUsername.getText().toString(), editTextPassword.getText().toString(),context);
            }
        });
    }



}
