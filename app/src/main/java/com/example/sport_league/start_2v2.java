package com.example.sport_league;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start_2v2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2v2);

        Button button = findViewById(R.id.whitfriend2v2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(start_2v2.this, whit_frend_2v2.class);
                startActivity(intent5);
            }

        });

        Button button1 = findViewById(R.id.whitonline2v2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(start_2v2.this, online_2v2.class);
                startActivity(intent5);
            }

        });
    }
}