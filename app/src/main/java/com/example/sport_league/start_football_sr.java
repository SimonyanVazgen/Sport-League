package com.example.sport_league;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.HashMap;

public class start_football_sr extends AppCompatActivity {

    private static final int IMAGE_SELECTION_REQUEST = 1;
    private Button[] buttons = new Button[11];
    private int selectedButtonId;
    private Button viewTeamButton;
    private final boolean selectionsLocked = false;
    private int cardsSelected = 0;
    private TextView ratingTextView;
    private HashMap<Integer, String> cardRatingsMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_football_sr);

        viewTeamButton = findViewById(R.id.viewTeamButton);
        viewTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(start_football_sr.this, TeamDisplayActivity.class);
                startActivity(intent);
            }
        });

        ratingTextView = findViewById(R.id.ratingTextView);


        int[] buttonIds = {
                R.id.button, R.id.button2, R.id.button123, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11
        };

        for (int i = 0; i < buttonIds.length; i++) {
            buttons[i] = findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(v -> {
                selectedButtonId = v.getId();
                if (cardsSelected < 11) {
                    selectCard();
                } else {
                    showRating(selectedButtonId);  // Show the rating in the TextView
                }
            });
        }





    }

    private void selectCard() {
        // Check if the number of selected cards is less than 11 before proceeding
        if (this.cardsSelected < 11) {
            Intent intent = new Intent(this, My_cards.class);
            startActivityForResult(intent, IMAGE_SELECTION_REQUEST);
        } else {
            // Optionally, inform the user that no more cards can be selected
            Toast.makeText(this, "Maximum of 11 cards are already selected.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == start_football_sr.IMAGE_SELECTION_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            String imageUri = data.getStringExtra("imageUri");
            String cardRating = data.getStringExtra("cardRating");
            Button buttonToUpdate = findViewById(this.selectedButtonId);
            this.cardRatingsMap.put(this.selectedButtonId, cardRating);
            this.cardsSelected++;
            Glide.with(this).load(imageUri).into(new CustomTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    buttonToUpdate.setBackground(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                }
            });
            if (this.cardsSelected >= 11) {
                disableCardSelection();
            }
        }
    }


    private void disableCardSelection() {

        for (Button button : this.buttons) {
            button.setOnClickListener(v -> showRating(v.getId()));
        }

    }


    @SuppressLint("SetTextI18n")
    private void showRating(int buttonId) {
        // Retrieve the rating from the map and display it in the TextView
        if (this.cardRatingsMap.containsKey(buttonId)) {
            String rating = this.cardRatingsMap.get(buttonId);
            this.ratingTextView.setText("Rating: " + rating);
            this.ratingTextView.setVisibility(View.VISIBLE);
        }
    }



}