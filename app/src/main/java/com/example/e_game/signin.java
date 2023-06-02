package com.example.e_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Button login = findViewById(R.id.login);
        Button back = findViewById(R.id.back);
        DatabaseHelper databaseHelper = new DatabaseHelper(this, "membership", null, 1);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                EditText account = findViewById(R.id.editText);
                EditText password = findViewById(R.id.editText2);
                String inputAccount = account.getText().toString();
                String inputPassword = password.getText().toString();
//                 Cursor c 類似 C# 的 SqlDataReader dataReader
                Cursor c = db.rawQuery("SELECT * FROM SQLite", null);
                ContentValues values = new ContentValues();
                int num = 0;
                if (c.moveToFirst()) {
                    do {
                        if(inputAccount.equals(c.getString(0)))
                            num++;
                    }
                    while (c.moveToNext());
                }
                if(num == 0){
                    values.put("account", account.getText().toString());
                    values.put("password", password.getText().toString());
                    values.put("money", 0);
                    db.insert("SQLite", null, values);
                    Toast.makeText(signin.this, "帳號新增完成", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(signin.this, "此帳號已被註冊", Toast.LENGTH_SHORT).show();
                }
            }
        });


//            public void onClick(View v) {
//                String Account = account.getText().toString();
//                SQLiteDatabase db = dbHelper.getWritableDatabase();// 與 testDB 資料庫連線/開啟資料庫
//                Cursor c = db.rawQuery("SELECT account FROM SQLite", null);
//                ContentValues values = new ContentValues();
//                int num = 0;
//                Toast.makeText(signin.this, Account, Toast.LENGTH_SHORT).show();
//                if (c.moveToFirst()) { // 資料庫有資料才做 do-while, 從(頭) First 開始 如果開頭有資料返回值為 true
//                    do {
//                        if(Account.equals(c.getString(0)))
//                            num++;
//                        else
//                            num--;
//                    }
//                    while (c.moveToNext()); // 當有下一筆資料 return 為 true
//                }
//                String s = String.valueOf(num);
////                Toast.makeText(signin.this, s, Toast.LENGTH_SHORT).show();
////                if(num == 0){
////                    values.put("account", account.getText().toString());
////                    values.put("password", password.getText().toString());
////                    values.put("money", 0);
////                    db.insert("SQLite", null, values);
////                    Toast.makeText(signin.this, "帳號新增完成", Toast.LENGTH_SHORT).show();
////                }
////                else
////                    Toast.makeText(signin.this, "此帳號已被註冊", Toast.LENGTH_SHORT).show();
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signin.this, login.class);
                startActivity(intent);
            }
        });
    }
}