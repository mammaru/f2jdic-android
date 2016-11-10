package com.example.sample.dict;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by yuma on 2016/10/24.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_FILE_NAME = "dict.sqlite3"; //assetsにあるDBファイル名
    private static String DB_NAME = "dict.db";
    private static int DB_VERSION = 1;
    private static String dbPath;

    private Context context;
    //private boolean databaseExist = true; //適切なDBファイルが存在するか

    public DatabaseHelper(Context _context) {
        super(_context, DB_NAME, null, DB_VERSION);
        dbPath = _context.getDatabasePath(DB_NAME).getAbsolutePath();
        //System.out.println(dbPath);
        //System.out.println(doesDatabaseExist());
        context = _context;
        if (!doesDatabaseExist()) {
            try {
                //database.close();
                copyDatabaseFromAssets();
                //databaseExist = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(DB_NAME + " already exists");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onOpen(db);
        //databaseExist = false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //String databasePath = this.dbPath.getAbsolutePath();
        File file = new File(dbPath);
        if (doesDatabaseExist()) {
            file.delete();
        }
        //databaseExist = false;
    }

    public void withTransaction() {

    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        //SQLiteDatabase database = super.getWritableDatabase();
        return super.getWritableDatabase();
    }

    private static boolean doesDatabaseExist() {
        File dbFile = new File(dbPath); //context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    /**
     * assetsにあるデータベースをdata/data/package/databasesにコピーする
     *
     * @throws IOException
     */
    private void copyDatabaseFromAssets() throws IOException { // TODO: Separate dict.sqlite3 if necessary.
        InputStream inputStream = this.context.getAssets().open(DB_FILE_NAME);
        OutputStream outputStream = new FileOutputStream(dbPath);

        System.out.println("Copy database file from assets");

        byte[] buffer = new byte[1024];
        int size;
        while ((size = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, size);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

}
