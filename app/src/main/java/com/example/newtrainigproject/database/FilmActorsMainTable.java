package com.example.newtrainigproject.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.newtrainigproject.Model.ModelSpinner;

import java.util.ArrayList;
import java.util.List;

public class FilmActorsMainTable {
    private static final String COL_ID = "id";
    private static final String COL_ACTORS = "actors";
    private static final String COL_FILM = "film";
    private static final String TABLE_FILM_ACTORS = "filmActors";

    public static final String CREATE_TABLE_FILM_ACTORS = " create table if not exists " + TABLE_FILM_ACTORS + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_ACTORS + " text not null," + COL_FILM + " text )";
    DbRegistration dbConnection;
    Context context;

    public FilmActorsMainTable(Context context) {
        this.context = context;
        dbConnection = new DbRegistration(context);
    }

    public void insertIntoLogin(ModelSpinner modelSpinner) {
        SQLiteDatabase sq = dbConnection.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID, modelSpinner.getId());
        values.put(COL_ACTORS, modelSpinner.getActors());
        values.put(COL_FILM, modelSpinner.getFilm());
        sq.insert(TABLE_FILM_ACTORS, null, values);
    }

    public boolean checkActor(String actors, String film) {
        boolean valid = false;
        SQLiteDatabase sq = dbConnection.getReadableDatabase();
        Cursor cursor = sq.rawQuery("select * from " + TABLE_FILM_ACTORS + " where " + COL_ACTORS + " = \"" + actors + "\" and " + COL_FILM + " = \"" + film + "\"", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                valid = true;
            }
            cursor.close();
        }
        sq.close();
        return valid;
    }

    @SuppressLint("Range")
    public List<ModelSpinner> getAllUsers() {
        List<ModelSpinner> modelSpinnerList = new ArrayList<>();
        SQLiteDatabase sq = dbConnection.getReadableDatabase();
        Cursor cursor = sq.rawQuery("select * from " + TABLE_FILM_ACTORS, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ModelSpinner modelSpinner = new ModelSpinner();
                    modelSpinner.setActors(cursor.getString(cursor.getColumnIndex(COL_ACTORS)));
                    modelSpinner.setFilm(cursor.getString(cursor.getColumnIndex(COL_FILM)));
                    modelSpinnerList.add(modelSpinner);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        sq.close();
        return modelSpinnerList;
    }

}
