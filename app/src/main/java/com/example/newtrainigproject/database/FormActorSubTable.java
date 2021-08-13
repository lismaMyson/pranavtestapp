package com.example.newtrainigproject.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.example.newtrainigproject.Model.FormActorModel;

import java.util.ArrayList;
import java.util.List;

public class FormActorSubTable {
    private static final String COL_ID = "id";
    private static final String COL_ACTORS = "actors";
    private static final String TABLE_FORM_ACTORS = "formActors";

    public static final String CREATE_TABLE_FORM_ACTORS = " create table if not exists " + TABLE_FORM_ACTORS + "( " + COL_ID + " Integer primary key autoincrement ," +
            COL_ACTORS + " text unique )";
    public static final String DROP_TABLE_FORM_ACTORS = " drop table if exists " + TABLE_FORM_ACTORS;
    DbRegistration dbConnection;
    Context context;

    public FormActorSubTable(Context context) {
        this.context = context;
        dbConnection = new DbRegistration(context);
    }

    public long insertIntoFormActor(FormActorModel mFormActorModel) {
        SQLiteDatabase sq = dbConnection.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ACTORS, mFormActorModel.getActors());
        return sq.insert(TABLE_FORM_ACTORS, null, values);

    }
    @SuppressLint("Range")
    public List<FormActorModel> getAllActors(){
        List<FormActorModel> modelList =new ArrayList<>();
        SQLiteDatabase sq=dbConnection.getReadableDatabase();
        Cursor cursor=sq.rawQuery(" select * from " + TABLE_FORM_ACTORS,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                do {
                    FormActorModel formActorModel=new FormActorModel();
                    formActorModel.setActors(cursor.getString(cursor.getColumnIndex(COL_ACTORS)));
                    modelList.add(formActorModel);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        sq.close();
        return modelList;
    }
}



