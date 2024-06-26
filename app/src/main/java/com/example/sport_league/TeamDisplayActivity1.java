package com.example.sport_league;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TeamDisplayActivity1 extends AppCompatActivity {

    private List<ImageView> imageViews;
    private List<String> imageUris;
    private List<String> ratings;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_display1);

        initializeImageViews();
        loadCardData();
        postDelayReveal(); // Use a delay to reveal a card automatically
    }

    private void initializeImageViews() {
        imageViews = new ArrayList<>();
        int[] ids = {
               R.id.imageViewTeam2, R.id.imageViewTeam3, R.id.imageViewTeam4, R.id.imageViewTeam5, R.id.imageViewTeam6
        };

        for (int id : ids) {
            ImageView imageView = findViewById(id);
            imageView.setVisibility(View.INVISIBLE); // Start with all cards invisible
            imageViews.add(imageView);
        }
    }

    private void loadCardData() {
        imageUris = new ArrayList<>();
        ratings = new ArrayList<>();

        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-nba-2k17-logo-png-2439932.png?alt=media&token=a1570b43-d22b-4c8c-b9b5-87ddffe8c94a", "95");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-nba-2k16-png-2754401.png?alt=media&token=2c82ff16-3ded-43ea-9ea9-b02758d9229f", "98");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-carmelo-anthony-png-653272.png?alt=media&token=8cf4e89e-73fc-4d83-8044-1a907f42c172", "95");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-kawhi-leonard-png-1329602.png?alt=media&token=7c30a97f-b24d-403b-8113-569adc0ab26d", "92");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-kobe-bryant-png-328894.png?alt=media&token=80eadee8-a517-4bec-a71c-90cb4df04e4b", "100");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-larry-bird-png-3047540.png?alt=media&token=bc2e8c5c-5630-4211-9fb1-639d02c5688a", "100");





        long seed = System.nanoTime();
        Collections.shuffle(imageUris, new Random(seed));
        Collections.shuffle(ratings, new Random(seed));
    }

    private void addCardData(String uri, String rating) {
        imageUris.add(uri);
        ratings.add(rating);
    }

    private void postDelayReveal() {
        // Post a delay to simulate asynchronous operation and reveal a card
        new android.os.Handler().postDelayed(this::revealRandomCard, 1000); // 1000 ms delay
    }

    private void revealRandomCard() {
        if (!imageUris.isEmpty()) {
            int index = random.nextInt(imageUris.size());
            ImageView selectedImageView = imageViews.get(index);
            Glide.with(this).load(imageUris.get(index)).into(selectedImageView);

            // Make the selected card permanently visible
            selectedImageView.setVisibility(View.VISIBLE);

            // Disable all other cards to prevent further interaction
            for (ImageView imageView : imageViews) {
                if (imageView != selectedImageView) {
                    imageView.setEnabled(false);
                }
            }

            // Return the rating of the selected card
            returnRating(ratings.get(index));
        } else {
            Toast.makeText(this, "No cards available", Toast.LENGTH_SHORT).show();
        }
    }

    private void returnRating(String rating) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("cardRating", rating);
        setResult(Activity.RESULT_OK, resultIntent);
        finish(); // Optionally close the activity if desired
    }
}