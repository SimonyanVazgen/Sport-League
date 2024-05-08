package com.example.sport_league;

import static com.example.sport_league.start_football_sr.TEAM_DISPLAY_REQUEST_CODE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.HashMap;

public class treading extends AppCompatActivity {

    private Button[] buttons = new Button[2];
    private Button lastClickedButton = null;
    private int selectedButtonId;
    private int cardsSelected = 0;
    private static final int MY_CARDS_REQUEST_CODE = 2;
    private static final int TEAM_DISPLAY_REQUEST_CODE = 1;
    private HashMap<Integer, String> cardRatingsMap = new HashMap<>();

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treading);

        Button button6 = findViewById(R.id.button14);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(treading.this, RandomTradingActivity.class);
                startActivity(intent6);
            }

        });



        int[] buttonIds = {
                R.id.button13, R.id.button12
        };


        for (int i = 0; i < buttonIds.length; i++) {
            buttons[i] = findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastClickedButton != null && lastClickedButton != v) {
                        resetAnimation(lastClickedButton);
                    }
                    selectedButtonId = v.getId();
                    if (cardsSelected < 2) {
                        selectCard();
                    } else {
                        lastClickedButton = (Button) v;
                    }
                }
            });

        }
    }

        private void resetAnimation(View view) {animateButton(view, 1.5f, 1.0f);}

    private void animateButton(View view, float startScale, float endScale) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                startScale, endScale,
                startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setRepeatCount(0);
        scaleAnimation.setFillAfter(true);

        view.startAnimation(scaleAnimation);

    }


    private void selectCard() {
        if (cardsSelected < 11) {
            Intent intent = new Intent(this, My_cards.class);
            startActivityForResult(intent, MY_CARDS_REQUEST_CODE);
        } else {
            Toast.makeText(this, "Maximum of 11 cards are already selected.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == TEAM_DISPLAY_REQUEST_CODE) {
                // Handling the team display activity result
                String cardRating = data.getStringExtra("cardRating");
                if (cardRating != null) {
                    if (cardsSelected == 2) {

                    }
                }
            } else if (requestCode == MY_CARDS_REQUEST_CODE) {
                // Handling the My_cards activity result
                String imageUri = data.getStringExtra("imageUri");
                String cardRating = data.getStringExtra("cardRating");
                if (imageUri != null && cardRating != null) {
                    updateButtonWithImage(imageUri, cardRating);
                }
            }
        }
    }

    private void updateButtonWithImage(String imageUri, String cardRating) {
        Button buttonToUpdate = findViewById(selectedButtonId);
        if (buttonToUpdate != null) {
            this.cardRatingsMap.put(selectedButtonId, cardRating);
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
            if (cardsSelected == 2) {
            }
        }

    }
}

