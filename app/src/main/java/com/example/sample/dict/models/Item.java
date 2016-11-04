package com.example.sample.dict.models;

import java.io.Serializable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.sample.dict.DatabaseHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by yuma on 2016/10/28.
 */
public final class Item implements Serializable {
    /** @fields */
    //private final int id;
    public String label;
    public String translation;
    public String example;
    private static final long serialVersionUID = 1L;

    //private final static String TABLE_NAME = "WORD";
    //private boolean didEditted = false;
    //private final Context context;

    /** @constructor */
    public Item() {}

/*    public Word(Context _context) {
        context = _context;
    }

    public Word(Context _context, String key) {
        context = _context;
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT word, trans, exp FROM " + TABLE_NAME + " WHERE word= ?";
        try {
            Cursor c = db.rawQuery(sql, new String[]{key});
            c.moveToFirst();
            label = c.getString(0);
            translation = c.getString(1);
            example = c.getString(2);
            c.close();
        } finally {
            db.close();
        }
    }*/

    /** @getter */
    public ArrayList<String> toArrayList() {
        ArrayList item = new ArrayList();
        item.add(label);
        item.add(translation);
        item.add(example);
        return item;
    }

 /*   public void save() {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("word", label);
        values.put("translation", translation);
        values.put("example", example);
        try {
            if (db.update(TABLE_NAME, values, "word='" + label + "'", null) == 0) {
                db.insert(TABLE_NAME, null, values);
            }
        } finally {
            db.close();
        }
    }

    public void update() {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("word", label);
        values.put("translation", translation);
        values.put("example", example);
        try {
            if (db.update(TABLE_NAME, values, "word='" + label + "'", null) == 0) {
                db.insert(TABLE_NAME, null, values);
            }
        } finally {
            db.close();
        }
    }

    public void delete(String key) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(TABLE_NAME, "word='" + key + "'", null);
        } finally {
            db.close();
        }
    }

    public void deleteAll() {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(TABLE_NAME, null, null);
        } finally {
            db.close();
        }
    }*/
}

