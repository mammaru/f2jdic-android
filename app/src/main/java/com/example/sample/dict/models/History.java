package com.example.sample.dict.models;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sample.dict.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by yuma on 2016/11/10.
 */

public class History {
    private final String TABLE_NAME = "HISTORY";
    private final int MAX_ROW_COUNT = 100;
    private final Context context;
    private DatabaseHelper dbHelper;

    public History(Context _context) {
        context = _context;
        dbHelper = new DatabaseHelper(context);
    }

    public Words get() {
        ArrayList<Integer> histIDs = getIDs();
        Words words = new Words(context);
        words.findById(histIDs);
        return words;
    }

    public void add(Item item) {
        if(isExists(item)) {
            delete(item);
        }
        insert(item);
    }

    private ArrayList<Integer> getIDs() {
        ArrayList<Integer> histIDs = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT _id, word_id, word_label, viewed_at FROM " + TABLE_NAME + " ORDER BY viewed_at ASC";
        System.out.println("Execute sql: " + sql);
        try {
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                histIDs.add(cursor.getInt(1));
            }
            cursor.close();
        } finally {
            db.close();
        }
        System.out.println(histIDs.toString());
        return histIDs;
    }

    private void insert(Item item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("word_id", item.id);
        values.put("word_label", item.label);
        try {
            if (db.update(TABLE_NAME, values, "word_id='" + item.id + "'", null) == 0) {
                db.insert(TABLE_NAME, null, values);
            }
        } finally {
            db.close();
        }
    }

    private void delete(Item item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(TABLE_NAME, "word_id='" + Integer.toString(item.id) + "'", null);
        } finally {
            db.close();
        }
    }

    private boolean isExists(Item item) {
        boolean flag = false;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT _id FROM " + TABLE_NAME + " WHERE word_id= ?";
        System.out.println("Execute sql: " + sql);
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(item.id)});
            if (cursor.getCount()>0) {
                flag = true;
            }
            cursor.close();
        } finally {
            db.close();
        }
        return flag;
    }

}
