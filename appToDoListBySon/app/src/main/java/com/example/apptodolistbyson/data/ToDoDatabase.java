package com.example.apptodolistbyson.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoDatabase extends SQLiteOpenHelper {
    private static ToDoDatabase intance;



    public ToDoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    public static ToDoDatabase getInstance(Context context, String name, int version){
        if (intance == null){
            intance = new ToDoDatabase(context, name,null, version);
            return intance;
        }
        return intance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void QueryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(sql, null);
    }
}
