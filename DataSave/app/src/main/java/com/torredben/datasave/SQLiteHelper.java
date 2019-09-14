package com.torredben.datasave;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATEBASE_NAME="persiondata.db";
    private static final int DATABASE_VERSION=1;

    public SQLiteHelper(Context context) {
        super(context, DATEBASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Person.create(db);
    }

    @Override
    public void onConfigure(android.database.sqlite.SQLiteDatabase db) {
        db.execSQL("pragma foreign_key=NO;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Person.drop(db);
        onCreate(db);
    }

    public SQLiteDatabase open() {
        return getWritableDatabase();
    }

    public void create() {
        open();
    }
}
