package com.example.e_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class Enter extends AppCompatActivity {
    DatabaseHelper databaseHelper = new DatabaseHelper(this, "membership",null, 1);
    public static Boolean flag1 = true;
    public static Boolean flag2 = true;
    public static Boolean emperor = false;
    public static Boolean slave = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        String s = "";
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Button emporer = findViewById(R.id.emporer);
        Button slave = findViewById(R.id.slave);
        Button add = findViewById(R.id.addmoney);
        Button back = findViewById(R.id.back);
        TextView tv = findViewById(R.id.money);
        EditText money = findViewById(R.id.editText);
        EditText spend2 = findViewById(R.id.spend2);
        String a = getIntent().getExtras().getString("key");
//        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
        Cursor cursor = db.rawQuery("SELECT * FROM SQLite where account = '" + a + "'", null);
        if (cursor.moveToFirst()) {
           do {
                s = cursor.getString(2);
                tv.setText("剩餘金額:" + s);
            } while (cursor.moveToNext());
        }
        cursor.close();
        String finalS = s;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = money.getText().toString();
                try
                {
                    int m = Integer.valueOf(finalS) + Integer.valueOf(s1);
                    db.execSQL("UPDATE SQLite SET money = " + m + " where account = '" + a + "'");
                    Toast.makeText(Enter.this, "儲值成功", Toast.LENGTH_SHORT).show();
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(Enter.this, "請輸入數字", Toast.LENGTH_SHORT).show();
                }
            }
        });

        emporer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Enter.this, Emporer.class);
                startActivity(intent);
            }
        });
        slave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Enter.this, Slave.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Enter.this, login.class);
                startActivity(intent);
            }
        });
    }
}