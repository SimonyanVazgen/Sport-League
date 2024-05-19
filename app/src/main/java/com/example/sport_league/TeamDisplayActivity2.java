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

public class TeamDisplayActivity2 extends AppCompatActivity {

    private List<ImageView> imageViews;
    private List<String> imageUris;
    private List<String> ratings;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_display2);

        initializeImageViews();
        loadCardData();
        postDelayReveal(); // Use a delay to reveal a card automatically
    }

    private void initializeImageViews() {
        imageViews = new ArrayList<>();
        int[] ids = {
                R.id.button13, R.id.button12, R.id.button3
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

        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DGunNJZXkAAj1sq.png?alt=media&token=b310592c-02d8-4a57-9cbb-80ebc433dee8", "88");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DGunOgtXYAIg4oH.png?alt=media&token=aa4c7db5-72a6-403e-bfe0-5a65a4631696", "92");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DGunPdEXkAA1CW0.png?alt=media&token=a587d921-59f2-4936-bdb0-196851a47465", "91");





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