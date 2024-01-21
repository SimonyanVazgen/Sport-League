package com.example.sport_league;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sport_league.R;
import com.example.sport_league.cards_tal;
import com.example.sport_league.cards_tal2;

public class paks_heto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paks_heto);



        Button button3 = findViewById(R.id.pac1_open);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(paks_heto.this, cards_tal.class);
                startActivity(intent3);
            }

        });



        Button button4 = findViewById(R.id.pac2_open);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(paks_heto.this, cards_tal2.class);
                startActivity(intent4);
            }

        });
    }

}