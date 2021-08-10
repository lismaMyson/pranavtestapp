package com.example.newtrainigproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbRegistration extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="dbTest";
    private static final int DB_VERSION=1;
            Context context;
    public DbRegistration(@Nullable Context context){
        super(context,DATABASE_NAME,null,DB_VERSION);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LoginRegistrationdb.CREATE_TABLE_REGISTRATION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase ,  int i, int i1) {
        sqLiteDatabase.execSQL(LoginRegistrationdb.CREATE_TABLE_REGISTRATION);
        sqLiteDatabase.execSQL(LoginRegistrationdb.DROP_TABLE_REGISTRATION);




    }
}
