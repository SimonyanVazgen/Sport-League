package com.example.sport_league;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class paks_heto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paks_heto);



        Button openRandomImagesButton = findViewById(R.id.pac1_open);
        openRandomImagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the RandomImagesActivity when the button is clicked
                Intent intent = new Intent(paks_heto.this, packs_open.class);
                startActivity(intent);
            }
        });



        Button button4 = findViewById(R.id.pac2_open);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(paks_heto.this, packs_open2.class);
                startActivity(intent4);
            }

        });
    }

}