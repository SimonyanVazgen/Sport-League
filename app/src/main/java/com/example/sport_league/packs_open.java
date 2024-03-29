package com.example.sport_league;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class packs_open extends AppCompatActivity {
    private int[] imageResources = {
            R.drawable.mkitaryan,
            R.drawable.mudryk,
            R.drawable.muriel,
            R.drawable.sancho,
            R.drawable.smolarek,

            R.drawable.karvajal,
            R.drawable.smolarek,
            R.drawable.mudryk,
            R.drawable.rubenneves
    };

    // Use a set to keep track of displayed image indices
    private Set<Integer> displayedImageIndices = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packs_open);

        // Get ImageViews
        ImageView imageView1 = findViewById(R.id.randomImageView1);
        ImageView imageView2 = findViewById(R.id.randomImageView2);
        ImageView imageView3 = findViewById(R.id.randomImageView3);
        ImageView imageView4 = findViewById(R.id.randomImageView4);

        // Display random images without repetition
        displayRandomImage(imageView1);
        displayRandomImage(imageView2);
        displayRandomImage(imageView3);
        displayRandomImage(imageView4);
        // Call displayRandomImage for the other ImageViews as needed
    }

    private void displayRandomImage(ImageView imageView) {
        // Get a random index that has not been displayed yet
        int randomIndex;
        do {
            randomIndex = new Random().nextInt(imageResources.length);
        } while (displayedImageIndices.contains(randomIndex));

        // Add the index to the set of displayed indices
        displayedImageIndices.add(randomIndex);

        // Set the randomly chosen image to the ImageView
        imageView.setImageResource(imageResources[randomIndex]);
    }
}
