package com.example.sport_league;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start_box extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_box);

        Button button5 = findViewById(R.id.box_qash_1);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(start_box.this, boks1_qahs.class);
                startActivity(intent5);
            }

        });


        Button button6 = findViewById(R.id.box_qash_2);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(start_box.this, boks2_qash.class);
                startActivity(intent5);
            }

        });
    }
}