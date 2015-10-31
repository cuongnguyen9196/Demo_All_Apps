package com.example.simple_sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DemoSQLite";
    private static final String TABLE_NAME = "Infomation";
    private static final String COL_1 = "id";
    private static final String COL_2 = "name";
    private static final String COL_3 = "address";
 
    public DB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "("
                + COL_1 + " INTEGER PRIMARY KEY," + COL_2 + " TEXT,"
                + COL_3 + " TEXT" + ")";
        db.execSQL(sql);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    public void addInfo(Info inf) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(COL_2, inf.getName()); // Contact Name
        values.put(COL_3, inf.getAddress()); // Contact Phone
 
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    public Info getOne(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_NAME, new String[] { COL_1,
                COL_2, COL_3 }, COL_1 + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Info inf = new Info(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return inf;
    }
     
    // Getting All Contacts
    public List<Info> getAllInfo() {
        List<Info> allInfo = new ArrayList<Info>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Info inf = new Info();
                inf.setID(Integer.parseInt(cursor.getString(0)));
                inf.setName(cursor.getString(1));
                inf.setAddress(cursor.getString(2));
                // Adding contact to list
                allInfo.add(inf);
            } while (cursor.moveToNext());
        }
        // return contact list
        return allInfo;
    }
 
    // Updating single contact
    public int updateInfo(Info inf) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(COL_2, inf.getName());
        values.put(COL_3, inf.getAddress());
 
        // updating row
        return db.update(TABLE_NAME, values, COL_1 + " = ?",
                new String[] { String.valueOf(inf.getID()) });
    }
 
    // Deleting single contact
    public void deleteInfo(Info inf) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ?", new String[] { String.valueOf(inf.getID()) });
        db.close();
    }
 
    // Getting contacts Count
    public int getCountInfo() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}