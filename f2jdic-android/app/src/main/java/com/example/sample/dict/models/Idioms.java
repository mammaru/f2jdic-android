package com.example.sample.dict.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sample.dict.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by yuma on 2016/11/03.
 */

public final class Idioms extends ArrayList<Item> {
    private final String TABLE_NAME = "IDIOM";
    private final Context context;
    private DatabaseHelper dbHelper;

    public Idioms(Context _context) {
        context = _context;
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public boolean add(Item idiom) {
        return super.add(idiom);
    }

    public void find(String key) {
        Item idiom;
        ArrayList<Item> list = new ArrayList<Item>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT _id, fr, jp, en, exp FROM " + TABLE_NAME + " WHERE fr LIKE ? || ' %' OR fr LIKE '% ' || ? || ' %' OR fr LIKE '% ' || ? LIMIT 20";
        System.out.println("Execute sql: " + sql);
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{key, key, key});
            while (cursor.moveToNext()) {
                idiom = new Item();
                //System.out.println(cursor.getString(0));
                idiom.id = cursor.getInt(0);
                idiom.fr = cursor.getString(1);
                idiom.jp = cursor.getString(2);
                idiom.en = cursor.getString(3);
                idiom.exp = cursor.getString(4);
                list.add(idiom);
            }
            this.addAll(list);
            cursor.close();
        } finally {
            db.close();
        }
    }

    public int totalCount() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{"fr"}, null, null, null, null, null);
            count = cursor.getCount();
            cursor.close();
        } finally {
            db.close();
        }
        return count;
    }

}