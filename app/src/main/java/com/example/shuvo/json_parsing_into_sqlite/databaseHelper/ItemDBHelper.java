package com.example.shuvo.json_parsing_into_sqlite.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "news.db";
    public static final int DATABASE_VERSION = 1;

    public ItemDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_NEWSLIST_TABLE = "CREATE TABLE " +
                ItemConten.ItemEntry.TABLE_NAME + " (" +
                ItemConten.ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ItemConten.ItemEntry.POST_CONTENT + " TEXT, " +
                ItemConten.ItemEntry.POST_TITTLE + " TEXT, " +
                ItemConten.ItemEntry.IMAGE_SRC + " TEXT," +
                ItemConten.ItemEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_NEWSLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ItemConten.ItemEntry.TABLE_NAME);
        onCreate(db);

    }



    public long insertItems(SQLiteDatabase mDatabase, String title, String content, String imgSrc){

        ContentValues cv = new ContentValues();
        cv.put(ItemConten.ItemEntry.POST_TITTLE, title);
        cv.put(ItemConten.ItemEntry.POST_CONTENT, content);
        cv.put(ItemConten.ItemEntry.IMAGE_SRC, imgSrc);

        return mDatabase.insert(ItemConten.ItemEntry.TABLE_NAME,null,cv);
    }
}
