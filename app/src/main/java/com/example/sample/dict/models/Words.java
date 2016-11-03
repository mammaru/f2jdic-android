package com.example.sample.dict.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sample.dict.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by yuma on 2016/11/03.
 */

public final class Words extends ArrayList<Item> {
    private final String TABLE_NAME = "WORD";
    private final Context context;
    private DatabaseHelper dbHelper;

    public Words(Context _context) {
        context = _context;
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public boolean add(Item word) {
        return super.add(word);
    }

    public void find(String key) {
        System.out.println(key);
        searchWords(key);
        searchTranslations(key);
    }

    public void searchWords(String key) {
        Item word;
        ArrayList<Item> list = new ArrayList<Item>();
        //DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT word, trans, exp FROM " + TABLE_NAME + " WHERE word LIKE ? || '%' LIMIT 20";
        System.out.println("Execute sql: " + sql);
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{key});
            //if (cursor.moveToFirst()) {
                while (cursor.moveToNext()) {
                    word = new Item();//context);
                    System.out.println(cursor.getString(0));
                    word.label = cursor.getString(0);
                    word.translation = cursor.getString(1);
                    word.example = cursor.getString(2);
                    list.add(word);
                } //while (cursor.moveToNext());
                this.addAll(list);
            //}
            cursor.close();
        } finally {
            db.close();
        }
    }

    public void searchTranslations(String key) {
        Item word;
        ArrayList<Item> list = new ArrayList<Item>();
        //DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<String> words = new ArrayList<>();
        String sql = "SELECT word, trans, exp FROM " + TABLE_NAME + " WHERE trans LIKE '%' || ? || '%' LIMIT 20";
        System.out.println("Execute sql: " + sql);
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{key});
            if (cursor.moveToFirst()) {
                do {
                    word = new Item();//context);
                    word.label = cursor.getString(0);
                    word.translation = cursor.getString(1);
                    word.example = cursor.getString(2);
                    list.add(word);
                } while (cursor.moveToNext());
                this.addAll(list);
            }
            cursor.close();
        } finally {
            db.close();
        }
    }

}