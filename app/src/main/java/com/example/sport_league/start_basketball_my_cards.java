package com.example.sport_league;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class start_basketball_my_cards extends AppCompatActivity {

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_basketball_my_cards);



        Button button1 = findViewById(R.id.gndak);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(start_basketball_my_cards.this, My_cards.class);
                startActivity(intent5);
            }

        });


        Button button2 = findViewById(R.id.basketballgndak);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(start_basketball_my_cards.this, start_basketball_my_cards.class);
                startActivity(intent5);
            }

        });










        // Define your ImageViews and their corresponding image URLs
         ImageView imageView1 = findViewById(R.id.imageView);
        String img1 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-nba-2k17-logo-png-2439932.png?alt=media&token=a1570b43-d22b-4c8c-b9b5-87ddffe8c94a";
        Glide.with(this).load(img1).into(imageView1);

        ImageView imageView2 = findViewById(R.id.imageView2);
        String img2 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-nba-2k16-png-2754401.png?alt=media&token=2c82ff16-3ded-43ea-9ea9-b02758d9229f";
        Glide.with(this).load(img2).into(imageView2);

        ImageView imageView3 = findViewById(R.id.imageView3);
        String img3 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-nba-2k16-png-1424710.png?alt=media&token=05d787d0-34cf-491a-9487-c5b36b6bc384";
        Glide.with(this).load(img3).into(imageView3);

        ImageView imageView4 = findViewById(R.id.imageView4);
        String img4 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-larry-bird-png-3047540.png?alt=media&token=bc2e8c5c-5630-4211-9fb1-639d02c5688a";
        Glide.with(this).load(img4).into(imageView4);

        ImageView imageView5 = findViewById(R.id.imageView5);
        String img5 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-kobe-bryant-png-328894.png?alt=media&token=80eadee8-a517-4bec-a71c-90cb4df04e4b";
        Glide.with(this).load(img5).into(imageView5);

        ImageView imageView6 = findViewById(R.id.imageView6);
        String img6 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-kawhi-leonard-png-1329602.png?alt=media&token=7c30a97f-b24d-403b-8113-569adc0ab26d";
        Glide.with(this).load(img6).into(imageView6);





        // Set click listeners for each ImageView
        imageView1.setOnClickListener(v -> returnResult(img1));
        imageView2.setOnClickListener(v -> returnResult(img2));
        imageView3.setOnClickListener(v -> returnResult(img3));
        imageView4.setOnClickListener(v -> returnResult(img4));
        imageView5.setOnClickListener(v -> returnResult(img5));
        imageView6.setOnClickListener(v -> returnResult(img6));

    }

    private void returnResult(String imageUri) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("imageUri", imageUri);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}