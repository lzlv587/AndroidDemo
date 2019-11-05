package com.example.test01.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MydatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_USER="create table User(" +
            "id integer primary key autoincrement, " +
            "name text, " +
            "password text)";
    public static final String CREATE_FOOD="create table Food("+
            "id integer primary key autoincrement,"
            +"name text,"
            +"kind text,"
            +"price double,"
            +"note text)";

    private Context  mContext;

    public MydatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext =context;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        //sqLiteDatabase.execSQL(CREATE_FOOD);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
