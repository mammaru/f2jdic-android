package com.example.sample.dict;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by yuma on 2016/10/24.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_FILE_NAME = "dict.sqlite3"; //database file name in assets
    private static int DB_VERSION = 3;
    private static final String DB_NAME = "dict.db";
    private static File dbFile;
    private static boolean databaseExist = false;
    private Context context;

    public DatabaseHelper(Context _context) {
        super(_context, DB_NAME, null, DB_VERSION);
        context = _context;
        dbFile = _context.getDatabasePath(DB_NAME);
        databaseExist = dbFile.exists();
        //Log.d("Multi", " dbPath: " + dbFile.getAbsolutePath());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onOpen(db);
        databaseExist = false;
        //Log.d("Multi", "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String databasePath = dbFile.getAbsolutePath();
        File file = new File(databasePath);
        if(file.exists()){
            file.delete();
        }
        databaseExist = false;
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase database = super.getWritableDatabase();
        if(!databaseExist){
            try {
                database.close();
                database = copyDatabaseFromAssets();
                databaseExist = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return database;
    }

    /**
     * Copy database file from assets to database.
     *
     * @throws IOException
     */
    private SQLiteDatabase copyDatabaseFromAssets() throws IOException { // TODO: Separate dict.sqlite3 if necessary.

        //Log.d("Multi", "Source database file: " + context.getAssets() + "/" + DB_FILE_NAME);
        InputStream inputStream = context.getAssets().open(DB_FILE_NAME);
        //Log.d("Multi", "Target database file: " + dbFile.getAbsolutePath());
        OutputStream outputStream = new FileOutputStream(dbFile);

        Log.d("Multi", "Copy database file from assets");

        byte[] buffer = new byte[1024];
        int size;
        while ((size = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, size);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        return super.getWritableDatabase();
    }

}
