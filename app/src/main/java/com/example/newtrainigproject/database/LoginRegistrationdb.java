package com.example.newtrainigproject.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.newtrainigproject.database.RegModel.LoginRegModel;

import java.util.ArrayList;
import java.util.List;

public class LoginRegistrationdb {
    private static final String COL_ID = "_id";
    private static final String COL_NAME = "name";
    private static final String COL_UNAME = "uname";
    private static final String COL_PASS = "password";
    private static final String COL_EMAIL = "email";
    private static final String COL_PH_NUMBER = "ph_number";
    private static final String COL_AGE = "age";
    private static final String COL_DOB = "date_of_birth";
    private static final String COL_GENDER = "gender";
    private static final String COL_HOBBIES = "hobbies";
    private static final String TABLE_REGISTRATION = "registration";

    public static final String CREATE_TABLE_REGISTRATION = "create table if not exists " + TABLE_REGISTRATION + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " text not null, " + COL_UNAME +
            " text not null," + COL_PASS + " text not null ," + COL_EMAIL + " text not null," + COL_PH_NUMBER + " text," + COL_AGE + " INTEGER," + COL_DOB + " text," + COL_GENDER +
            " text," + COL_HOBBIES + " text )";
    public static final String DROP_TABLE_REGISTRATION = "drop table if exists" + TABLE_REGISTRATION;
    DbRegistration dbConnection;
    Context context;

    public LoginRegistrationdb(Context context) {
        this.context = context;
        dbConnection = new DbRegistration(context);
    }

    public void insertIntoLogin(LoginRegModel loginRegModel1) {
        SQLiteDatabase sq = dbConnection.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, loginRegModel1.getName());
        values.put(COL_UNAME, loginRegModel1.getUname());
        values.put(COL_PASS, loginRegModel1.getPassword());
        values.put(COL_AGE, loginRegModel1.getAge());
        values.put(COL_GENDER, loginRegModel1.getGender());
        values.put(COL_HOBBIES, loginRegModel1.getHobbies());
        values.put(COL_DOB, loginRegModel1.getDate_of_birth());
        values.put(COL_PH_NUMBER, loginRegModel1.getPh_number());
        values.put(COL_EMAIL, loginRegModel1.getEmail());
        sq.insert(TABLE_REGISTRATION, null, values);
    }

    @SuppressLint("Range")
    public LoginRegModel checkLogin(String uname, String password) {
        LoginRegModel loginRegModel = new LoginRegModel();
        SQLiteDatabase sq = dbConnection.getReadableDatabase();
        Cursor cursor = sq.rawQuery("select * from " + TABLE_REGISTRATION + " where " + COL_UNAME + " = \"" + uname + "\" and " + COL_PASS + " = \"" + password + "\"", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                loginRegModel.setUname(cursor.getString(cursor.getColumnIndex(COL_UNAME)));
                loginRegModel.setPassword(cursor.getString(cursor.getColumnIndex(COL_PASS)));
                loginRegModel.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                loginRegModel.setEmail(cursor.getString(cursor.getColumnIndex(COL_EMAIL)));
                loginRegModel.setAge(cursor.getString(cursor.getColumnIndex(COL_AGE)));
                loginRegModel.setPh_number(cursor.getString(cursor.getColumnIndex(COL_PH_NUMBER)));
                loginRegModel.setHobbies(cursor.getString(cursor.getColumnIndex(COL_HOBBIES)));
                loginRegModel.setGender(cursor.getString(cursor.getColumnIndex(COL_GENDER)));
                loginRegModel.setDate_of_birth(cursor.getString(cursor.getColumnIndex(COL_DOB)));
            }
            cursor.close();
        }
        sq.close();
        return loginRegModel;
    }

    @SuppressLint("Range")
    public List<LoginRegModel> getAllUsers() {
        List<LoginRegModel> modelList = new ArrayList<>();
        SQLiteDatabase sq = dbConnection.getReadableDatabase();
        Cursor cursor = sq.rawQuery("select * from " + TABLE_REGISTRATION, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    LoginRegModel loginRegModel = new LoginRegModel();
                    loginRegModel.setUname(cursor.getString(cursor.getColumnIndex(COL_UNAME)));
                    loginRegModel.setPassword(cursor.getString(cursor.getColumnIndex(COL_PASS)));
                    loginRegModel.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                    loginRegModel.setEmail(cursor.getString(cursor.getColumnIndex(COL_EMAIL)));
                    loginRegModel.setAge(cursor.getString(cursor.getColumnIndex(COL_AGE)));
                    loginRegModel.setPh_number(cursor.getString(cursor.getColumnIndex(COL_PH_NUMBER)));
                    loginRegModel.setHobbies(cursor.getString(cursor.getColumnIndex(COL_HOBBIES)));
                    loginRegModel.setGender(cursor.getString(cursor.getColumnIndex(COL_GENDER)));
                    loginRegModel.setDate_of_birth(cursor.getString(cursor.getColumnIndex(COL_DOB)));
                    modelList.add(loginRegModel);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }

sq.close();
        return modelList;
    }


}
