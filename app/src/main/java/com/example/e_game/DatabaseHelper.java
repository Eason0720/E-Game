package com.example.e_game;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String dbName, @Nullable CursorFactory factory,
                          int dbVersion) {
        super(context, dbName, factory, dbVersion);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCommand = "CREATE TABLE IF NOT EXISTs SQLite(" +
                "account text not null," +
                "password text not null," +
                "money int not null)";
        db.execSQL(sqlCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onUpgrade 則是如果資料庫結構有改變了就會觸發 onUpgrade
        final String sqlCommand = "DROP TABLE Users";
        db.execSQL(sqlCommand);
    }


//    查询余额
//    public int getBalance(String name) {
//        int balance = 0;
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns = {"balance"};
//        String selection = "name=?";
//        String[] selectionArgs = {name};
//        Cursor cursor = db.query("members", columns, selection, selectionArgs, null, null, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            balance = cursor.getInt(cursor.getColumnIndex());
//            cursor.close();
//        }
//        db.close();
//        return balance;
//    }
}