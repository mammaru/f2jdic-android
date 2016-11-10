package com.example.sample.dict.models;

import java.util.Collections;
import android.text.TextUtils;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sample.dict.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;

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
        searchByLabel(key);
        searchByTrans(key);
    }

    public void searchByLabel(String key) {
        Item word;
        ArrayList<Item> list = new ArrayList<Item>();
        //DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT _id, label, trans, exp FROM " + TABLE_NAME + " WHERE label LIKE ? || '%' LIMIT 20";
        System.out.println("Execute sql: " + sql);
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{key});
            //if (cursor.moveToFirst()) {
                while (cursor.moveToNext()) {
                    word = new Item();//context);
                    System.out.println(cursor.getString(0));
                    word.id = cursor.getInt(0);
                    word.label = cursor.getString(1);
                    word.translation = cursor.getString(2);
                    word.example = cursor.getString(3);
                    list.add(word);
                } //while (cursor.moveToNext());
                this.addAll(list);
            //}
            cursor.close();
        } finally {
            db.close();
        }
    }

    public void searchByTrans(String key) {
        Item word;
        ArrayList<Item> list = new ArrayList<Item>();
        //DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<String> words = new ArrayList<>();
        String sql = "SELECT _id, label, trans, exp FROM " + TABLE_NAME + " WHERE trans LIKE '%' || ? || '%' LIMIT 20";
        System.out.println("Execute sql: " + sql);
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{key});
            if (cursor.moveToFirst()) {
                do {
                    word = new Item();//context);
                    word.id = cursor.getInt(0);
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

    public void findById(ArrayList<Integer> IDs) {
        Item word;
        ArrayList<Item> list = new ArrayList<Item>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] arr = new String[IDs.size()];
        for (int i=0;i<IDs.size();i++) {
            arr[i] = IDs.get(i).toString();
        }

        String sql = "SELECT _id, label, trans, exp FROM " + TABLE_NAME + " WHERE _id IN(" + TextUtils.join(",", Collections.nCopies(IDs.size(), "?")) + ")";
        //String sql = "SELECT _id, label, trans, exp FROM " + TABLE_NAME + " WHERE _id IN( " + IDs.toString() + " )";
        System.out.println("Execute sql: " + sql);
        try {
            Cursor cursor = db.rawQuery(sql, arr);
            //Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    word = new Item();
                    word.id = cursor.getInt(0);
                    word.label = cursor.getString(1);
                    word.translation = cursor.getString(2);
                    word.example = cursor.getString(3);
                    list.add(word);
                } while (cursor.moveToNext());
                this.addAll(list);
            }
            cursor.close();
        } finally {
            db.close();
        }
    }

    public int totalCount() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{"label"}, null, null, null, null, null);
            count = cursor.getCount();
            cursor.close();
        } finally {
            db.close();
        }
        return count;
    }


}