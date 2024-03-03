package com.example.sport_league;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class treding_heto extends AppCompatActivity {

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treding_heto);

         Button button3 = findViewById(R.id.button12);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(treding_heto.this, My_cards.class);
                startActivity(intent);
            }

        });



        Button button = findViewById(R.id.button13);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(treding_heto.this, My_cards.class);
                startActivity(intent);
            }

        });
    }
}