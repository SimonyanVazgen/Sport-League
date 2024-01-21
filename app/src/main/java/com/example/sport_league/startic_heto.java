package com.example.sport_league;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sport_league.R;
import com.example.sport_league.start_bascketball;

public class startic_heto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startic_heto2);

        Button button = findViewById(R.id.football);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startic_heto.this, start_football.class);
                startActivity(intent);
            }

        });

        Button button1 = findViewById(R.id.BASKETBALL);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(startic_heto.this, start_bascketball.class);
                startActivity(intent1);


            }
        });


        Button button2 = findViewById(R.id.BOXING);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(startic_heto.this,start_box.class);
                startActivity(intent2);


            }
        });



        Button button4 = findViewById(R.id.v2button);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(startic_heto.this,start_2v2.class);
                startActivity(intent4);


            }
        });
    }
}