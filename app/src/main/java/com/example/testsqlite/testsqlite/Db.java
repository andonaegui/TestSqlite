package com.example.testsqlite.testsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Wolf on 8/29/2016.
 */
public class Db {
    private DbHelper dbHelper;

    private SQLiteDatabase database;

    public final static String EMP_TABLE="Notes"; // name of table

    public final static String EMP_ID="_id"; // id value for note
    public final static String EMP_NAME="note";  // name of note
    /**
     *
     * @param context
     */
    public Db(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public long createRecord(String name){
        ContentValues values = new ContentValues();
        values.put(EMP_NAME, name);
        return database.insertWithOnConflict(EMP_TABLE,null ,values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public long deleteRecords() {
        return database.delete(EMP_TABLE,null,null);
    }

    public Cursor selectRecords() {
        String[] cols = new String[] {EMP_ID, EMP_NAME};
        Cursor mCursor = database.query(true, EMP_TABLE,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }
}