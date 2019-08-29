package com.example.student.db_sqlite_tute.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;
import android.widget.Toast;

import java.sql.RowId;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final  String DATABASENAME = "USerInfo.db";

    public  DBHelper(Context context) {super(context, DATABASENAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + UserMaster.Users.TABLE_NAME + "("+
                UserMaster.Users._ID+ " INTEGER PRIMARY KEY,"+
                UserMaster.Users.COLUMN_NAME_USERNAME + " TEXT,"+
                UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  void addInfo(String username, String password){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_USERNAME,username);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        long newRowId = db.insert(UserMaster.Users.TABLE_NAME, null,values);
        Log.v("Newly Added RowID", Long.toString(newRowId));

    }

    public List readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_USERNAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD
        };

        String sortOrder = UserMaster.Users.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor = db.query(UserMaster.Users.TABLE_NAME,projection,null,null,null,null,sortOrder);


        List userNames = new ArrayList<>();
        List passWords = new ArrayList<>();
        List data = new ArrayList<>();

        while (cursor.moveToNext()){
            String username  = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));

            userNames.add(username);
            passWords.add(password);
            data.add("username: "+username+" Password: "+password);
        }

        cursor.close();
        return (data);

    }

    public boolean readInfo(String un, String pwd, Context context){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_USERNAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD
        };

        String sortOrder = UserMaster.Users.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor = db.query(UserMaster.Users.TABLE_NAME,projection,null,null,null,null,sortOrder);


        List userNames = new ArrayList<>();
        List passWords = new ArrayList<>();

        while (cursor.moveToNext()){
            String username  = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));

            userNames.add(username);
            passWords.add(password);
        }
        cursor.close();

        if (userNames.contains(un)){
            if (passWords.contains(pwd)){
                Toast.makeText(context,"Login Sucessful",Toast.LENGTH_LONG).show();
                return true;
            }
            else
            {
                Toast.makeText(context,"Login UnSucessful",Toast.LENGTH_LONG).show();
                return false;
            }
        }else{
            Toast.makeText(context,"Unable to find User",Toast.LENGTH_LONG).show();
            return false;
        }

    }

    public void deleteInfo(String username, Context context){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UserMaster.Users.COLUMN_NAME_USERNAME + " Like ?";

        String[] selectionArgs = {username};

        db.delete(UserMaster.Users.TABLE_NAME,selection,selectionArgs);

        Toast.makeText(context,"Login Delete successful",Toast.LENGTH_LONG).show();

    }

    public  void updateInfo(String username, String password,Context context){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues  values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD,password);

        String selection = UserMaster.Users.COLUMN_NAME_USERNAME +" Like ?";

        String[] selectionArgs = {username};

        int count = db.update(UserMaster.Users.TABLE_NAME,values,selection,selectionArgs);
        Toast.makeText(context,"User update successful",Toast.LENGTH_LONG).show();

    }
}
