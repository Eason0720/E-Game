package com.example.e_game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "balance.db";
    private static final int DATABASE_VERSION = 1;

    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建立資料表
        String createTableQuery = "CREATE TABLE money (id INTEGER PRIMARY KEY, amount INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 升級資料庫
        String dropTableQuery = "DROP TABLE IF EXISTS balance";
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
}
