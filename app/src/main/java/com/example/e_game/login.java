package com.example.e_game;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    public String a;
    DatabaseHelper databaseHelper = new DatabaseHelper(this, "membership",null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button signin = findViewById(R.id.signin);
        Button login = findViewById(R.id.login);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();// 與 testDB 資料庫連線/開啟資料庫
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signin.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                EditText account = findViewById(R.id.edit_account);
                a = account.getText().toString();
                Intent intent = new Intent(login.this, Enter.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", account.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);

                EditText password = findViewById(R.id.edit_password);
                String inputAccount = account.getText().toString();
                String inputPassword = password.getText().toString();
//                 Cursor c 類似 C# 的 SqlDataReader dataReader
                Cursor c = db.rawQuery("SELECT * FROM SQLite", null);
                int num = 0;
                if (c.moveToFirst()) { // 資料庫有資料才做 do-while, 從(頭) First 開始 如果開頭有資料返回值為 true
                    do {
                        if(inputAccount.equals(c.getString(0))){
                            if(inputPassword.equals(c.getString(1))) {
                                Toast.makeText(login.this, "成功登入", Toast.LENGTH_SHORT).show();
                                Intent intent2 = new Intent(login.this, Enter.class);
                                startActivity(intent2);
                            }
                            else
                                Toast.makeText(login.this, "輸入密碼錯誤", Toast.LENGTH_SHORT).show();
                            num++;
                        }
                        
                    }
                    while (c.moveToNext()); // 當有下一筆資料 return 為 true
                }
                if(num == 0)
                    Toast.makeText(login.this, "查無此帳號", Toast.LENGTH_SHORT).show();
            }
        });
    }
//        public void deposit(String name, int amount) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("balance", amount);
//        db.insert("members", null, values);
//        db.close();
//    }
}
