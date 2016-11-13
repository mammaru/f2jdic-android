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
    public String fr;
    public String jp;
    public String en;
    public String exp;
    public int count;
    public boolean modified;
    private static final long serialVersionUID = 1L;

    /** @constructor */
    public Item() {}


    /** @getter */
    public ArrayList<String> toArrayList() {
        ArrayList item = new ArrayList();
        item.add(id);
        item.add(fr);
        item.add(jp);
        item.add(en);
        item.add(exp);
        item.add(count);
        item.add(modified);
        return item;
    }
}
