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

public class TeamDisplayActivity extends AppCompatActivity {

    private List<ImageView> imageViews;
    private List<String> imageUris;
    private List<String> ratings;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_display);

        initializeImageViews();
        loadCardData();
        postDelayReveal(); // Use a delay to reveal a card automatically
    }

    private void initializeImageViews() {
        imageViews = new ArrayList<>();
        int[] ids = {
                R.id.imageViewForward1, R.id.imageViewForward2, R.id.imageViewForward3,
                R.id.imageViewMidfielder1, R.id.imageViewMidfielder2, R.id.imageViewMidfielder3,
                R.id.imageViewDefender1, R.id.imageViewDefender2, R.id.imageViewDefender3,
                R.id.imageViewDefender4, R.id.imageViewGoalkeeper
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

        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/apedipele.png?alt=media&token=cba783b8-24bb-4aed-9023-d77e0cae0a00", "98");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/haland.png?alt=media&token=db841016-0a48-4856-a753-f69b87b22ead", "99");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/smolarek.png?alt=media&token=4086bb21-b17a-49dc-a6cf-ff7d2c28bebb", "96");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sancho.png?alt=media&token=17818fe7-2f6c-4847-ad20-7e69ae2d7a63", "96");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/cordoba.png?alt=media&token=5f77fa5e-8ded-4405-b4bc-54b6a997bafe", "97");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/donarumma.png?alt=media&token=603b0df6-5884-42cd-9bef-e42e1c2d4553", "99");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/dybala.png?alt=media&token=be64c1cf-df4c-48a8-8aa3-557e105c0ad3", "98");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/ferrari.png?alt=media&token=ac707f26-7ae9-4678-ae46-867c2b334462", "92");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/fred.png?alt=media&token=b4b98187-407f-4f90-86aa-02e4475a1d22", "96");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/gomez.png?alt=media&token=a8d31bfa-248d-464c-b62d-1bf6b4ba6fbf", "96");
        addCardData("https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/insigne.png?alt=media&token=778b7fd7-8aae-4c39-b651-d70c5ba82827", "95");

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