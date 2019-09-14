package com.torredben.datasave;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Person {
    public int _id;
    public String name;
    public String info;

    public static void create(SQLiteDatabase database) {
        String createTable = "create table if not exists persons("
                + "_id integer primary key autoincrement,"
                + "name text not null,"
                + "info text not null);";
        database.execSQL(createTable);
    }

    public static void drop(SQLiteDatabase database) {
        String dropTable = "drop table if exists persons";
        database.execSQL(dropTable);
    }

    public static List<Person> getAll(SQLiteDatabase database) {
        List<Person> list = new ArrayList<Person>();
        android.database.Cursor cursor = database.rawQuery("select * from persons",null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Person person = cursorToPerson(cursor);
            list.add(person);
            cursor.moveToNext();
        }
        cursor.close();
        return  list;
    }

    private static Person cursorToPerson(android.database.Cursor cursor) {
        Person person = new Person();
        person._id = cursor.getInt(cursor.getColumnIndex("_id"));
        person.name = cursor.getString(cursor.getColumnIndex("name"));
        person.info = cursor.getString(cursor.getColumnIndex("info"));

        return person;
    }
}
