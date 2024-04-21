package com.example.lab13;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public static String TABLE_NAME = "history";
    public static String FIELD_NAME = "text";

    public Database(@Nullable Context context) {
        super(context, "history.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" + FIELD_NAME + " varhcar(255));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        db.execSQL("create table " + TABLE_NAME + " (" + FIELD_NAME + " varhcar(255));");
    }

    @SuppressLint("Range")
    public List<String> getAll() {
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from " + TABLE_NAME, new String[]{});
        ArrayList<String> data = new ArrayList<>();

        while (cursor.moveToNext()) {
            data.add(cursor.getString(cursor.getColumnIndex(FIELD_NAME)));
        }

        cursor.close();
        return data;
    }

    public void add(String text) {
        this.getWritableDatabase().execSQL("insert into " + TABLE_NAME  + " (" + FIELD_NAME + ") VALUES(?)", new String[]{text});
    }
}