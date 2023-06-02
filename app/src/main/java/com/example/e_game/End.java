package com.example.e_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class End extends AppCompatActivity {
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        TextView tv1 = findViewById(R.id.textView);
        TextView hint = findViewById(R.id.hint);
        TextView money1 = findViewById(R.id.money);
        TextView money2 = findViewById(R.id.money2);
        TextView money3 = findViewById(R.id.money3);
        TextView money4 = findViewById(R.id.money4);
        Button back = findViewById(R.id.back);
        if(Enter.flag1 == true && Enter.flag2 == false){
            hint.setText("你贏了");
        } else if (Enter.flag1 == false && Enter.flag2 == true) {
            hint.setText("你贏了");
        }else if(Enter.flag1 == false && Enter.flag2 == false){
            hint.setText("你輸了");
        } else{
            if(Enter.emperor == true && Enter.slave == false){
                hint.setText("你輸了");
            }else if(Enter.emperor == false && Enter.slave == true){
                hint.setText("你贏了");
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(End.this, Enter.class);
                startActivity(intent);
            }
        });
    }
}