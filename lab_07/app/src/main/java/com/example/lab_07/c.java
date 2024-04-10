package com.example.lab_07;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class c extends SQLiteOpenHelper
{
    public c(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase c) {
        c.execSQL("CREATE TABLE IF NOT EXISTS my_test (my_key TEXT, my_value TEXT)");
    }

    public void do_incert(String key, String value)
    {
        String sql;
        sql = "INSERT INTO my_test VALUES(" + key + "," + value + ");";
        SQLiteDatabase c = getWritableDatabase();
        c.execSQL(sql);
    }

    public String do_select(String key)
    {
        String sql = "SELECT my_value FROM my_test WHERE my_key= "  + key + ";";
        SQLiteDatabase c = getReadableDatabase();
        Cursor cur = c.rawQuery(sql,  null);

        if (cur.moveToFirst()==true)
            return cur.getString(0);
        return "(!) not found";
    }
    public void do_delete(String key, String value)
    {
        String sql;
        sql = "DELETE FROM my_test WHERE my_key= "  + key + ";";
        SQLiteDatabase c = getWritableDatabase();
        c.execSQL(sql);
    }
    public void update(ContentValues cv, String nid)
    {
        getWritableDatabase().update("my_test", cv, "my_key = ?", new String[]{String.valueOf(nid)});    }
    @Override
    public void onUpgrade(SQLiteDatabase c, int oldVersion, int newVersion) {

    }
}