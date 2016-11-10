package com.example.sample.dict.models;

import java.io.Serializable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.sample.dict.DatabaseHelper;

import java.lang.reflect.Array;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by yuma on 2016/10/28.
 */
public final class Item implements Serializable {
    /** @fields */
    public int id;
    public String label;
    public String translation;
    public String example;
    public Timestamp viewed_at = null;
    private static final long serialVersionUID = 1L;

    /** @constructor */
    public Item() {}


    /** @getter */
    public ArrayList<String> toArrayList() {
        ArrayList item = new ArrayList();
        item.add(label);
        item.add(translation);
        item.add(example);
        return item;
    }
}
