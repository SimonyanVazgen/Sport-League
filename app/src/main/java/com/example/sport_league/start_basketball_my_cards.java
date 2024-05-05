package com.example.sport_league;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import java.util.HashSet;

/**
 *
 */
public class start_basketball_my_cards extends AppCompatActivity {

    private String img2;
    private String img1;
    private String img3;
    private String img4;
    private String img5;
    private String img6;



    /**
     *
     */
    public static HashSet<String> selectedImageUris = new HashSet<>();


    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_basketball_my_cards);


        ImageView imageView1 = findViewById(R.id.imageView);
        img1 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-nba-2k17-logo-png-2439932.png?alt=media&token=a1570b43-d22b-4c8c-b9b5-87ddffe8c94a";
        Glide.with(this).load(this.img1).into(imageView1);

        ImageView imageView2 = findViewById(R.id.imageView2);
        img2 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-nba-2k16-png-2754401.png?alt=media&token=2c82ff16-3ded-43ea-9ea9-b02758d9229f";
        Glide.with(this).load(img2).into(imageView2);

        ImageView imageView3 = findViewById(R.id.imageView3);
        img3 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-carmelo-anthony-png-653272.png?alt=media&token=8cf4e89e-73fc-4d83-8044-1a907f42c172";
        Glide.with(this).load(img3).into(imageView3);

        ImageView imageView4 = findViewById(R.id.imageView4);
        img4 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-larry-bird-png-3047540.png?alt=media&token=bc2e8c5c-5630-4211-9fb1-639d02c5688a";
        Glide.with(this).load(img4).into(imageView4);

        ImageView imageView5 = findViewById(R.id.imageView5);
        img5 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-kobe-bryant-png-328894.png?alt=media&token=80eadee8-a517-4bec-a71c-90cb4df04e4b";
        Glide.with(this).load(img5).into(imageView5);

        ImageView imageView6 = findViewById(R.id.imageView6);
        img6 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-kawhi-leonard-png-1329602.png?alt=media&token=7c30a97f-b24d-403b-8113-569adc0ab26d";
        Glide.with(this).load(img6).into(imageView6);


        imageView1.setOnClickListener(v -> {
            String rating = "95";
            returnResult(img1, imageView1, rating);
        });
        imageView2.setOnClickListener(v -> {
            String rating = "98";
            returnResult(img2, imageView2, rating);
        });
        imageView3.setOnClickListener(v -> {
            String rating = "95";
            returnResult(img3, imageView3, rating);
        });
        imageView4.setOnClickListener(v -> {
            String rating = "100";
            returnResult(img4, imageView4, rating);
        });
        imageView5.setOnClickListener(v -> {
            String rating = "100";
            returnResult(img5, imageView5, rating);
        });
        imageView6.setOnClickListener(v -> {
            String rating = "92";
            returnResult(img6, imageView6, rating);
        });


        if (selectedImageUris.contains(img1)) {
            imageView1.setVisibility(View.GONE);
        }

        if (selectedImageUris.contains(img2)) {
            imageView2.setVisibility(View.GONE);
        }

        if (selectedImageUris.contains(img3)) {
            imageView3.setVisibility(View.GONE);
        }

        if (selectedImageUris.contains(img4)) {
            imageView4.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img5)) {
            imageView5.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img6)) {
            imageView6.setVisibility(View.GONE);
        }


    }


    private void returnResult(String imageUri, ImageView imageView, String rating) {
        Log.d("My_cards", "Returning result: " + imageUri + " with rating: " + rating);
        selectedImageUris.add(imageUri);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("imageUri", imageUri);
        resultIntent.putExtra("cardRating", rating);
        setResult(RESULT_OK, resultIntent);
        imageView.setVisibility(View.GONE);
        finish();
    }

}
