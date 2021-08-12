package com.example.newtrainigproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbRegistration extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="dbTest";
    private static final int DB_VERSION=2;
            Context context;
    public DbRegistration(@Nullable Context context){
        super(context,DATABASE_NAME,null,DB_VERSION);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LoginRegistrationTable.CREATE_TABLE_REGISTRATION);
        sqLiteDatabase.execSQL(FilmActorsTable.CREATE_TABLE_FILM_ACTORS);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase ,  int i, int i1) {

        sqLiteDatabase.execSQL(LoginRegistrationTable.DROP_TABLE_REGISTRATION);
        sqLiteDatabase.execSQL(LoginRegistrationTable.CREATE_TABLE_REGISTRATION);
        sqLiteDatabase.execSQL(FilmActorsTable.CREATE_TABLE_FILM_ACTORS);




    }
}
