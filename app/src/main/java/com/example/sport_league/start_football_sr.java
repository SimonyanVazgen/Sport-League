package com.example.sport_league;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.HashMap;

public class start_football_sr extends TeamDisplayActivity {

    private Button[] buttons = new Button[11];
    private Button lastClickedButton = null;
    private TextView ratingTextView,ratingTextView2, scoreTextView, scoreTextView2;
    private HashMap<Integer, String> cardRatingsMap = new HashMap<>();
    private int cardsSelected = 0;
    private int selectedButtonId;
    private Button button1;
    private static final int TEAM_DISPLAY_REQUEST_CODE = 1;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_football_sr);
        button1  = findViewById(R.id.viewTeamButton);


        button1.setOnClickListener(v -> {
            Intent intent = new Intent(start_football_sr.this, TeamDisplayActivity.class);
            startActivityForResult(intent, TEAM_DISPLAY_REQUEST_CODE);
        });


        displayTeamMemberRating("95");

        ratingTextView = findViewById(R.id.ratingTextView);
        ratingTextView2 = findViewById(R.id.ratingTextView2);


        int[] buttonIds = {
                R.id.button, R.id.button2, R.id.button123, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11
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
                    if (cardsSelected < 11) {
                        selectCard();
                    } else {
                        showRatingAndAnimate(v);
                        lastClickedButton = (Button) v;
                    }
                }
            });
        }
    }

    private void selectCard() {
        if (this.cardsSelected <= 11) {
            Intent intent = new Intent(this, My_cards.class);
            startActivityForResult(intent, 1);
        } else {
            ratingTextView.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Maximum of 11 cards are already selected.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String cardRating = data.getStringExtra("cardRating");
        if (requestCode == TEAM_DISPLAY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                if (cardRating != null) {
                    ratingTextView2.setText("Rating: " + cardRating);
                    // Call compareRatings if 11 cards have been selected.
                    if (cardsSelected == 11) {
                        compareRatings(cardRating);
                    }
                }
            }
            if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
                String imageUri = data.getStringExtra("imageUri");
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
            }

            if (scoreTextView != null) {
                // Update the scoreTextView when a new card is added, but only if we have 11 cards.
                if (cardsSelected == 11) {
                    compareRatings(cardRating);
                }
            }
        } else {
            Toast.makeText(this, "Maximum of 11 cards are already selected.", Toast.LENGTH_SHORT).show();
        }



        }

    @SuppressLint("SetTextI18n")
    private void showRatingAndAnimate(View view) {
        // Animate and show rating
        if (this.cardRatingsMap.containsKey(view.getId())) {
            String rating = this.cardRatingsMap.get(view.getId());
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    1.0f, 1.5f,
                    1.0f, 1.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(300);
            scaleAnimation.setRepeatCount(0);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    ratingTextView.setText("Rating: " + rating);
                    ratingTextView.setVisibility(View.VISIBLE);



                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                   
                }
            });

            view.startAnimation(scaleAnimation);
        }
    }

    private void resetAnimation(View view) {
        animateButton(view, 1.5f, 1.0f);
    }

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


    private void displayTeamMemberRating(String rating) {
        Intent intent = new Intent(this, TeamDisplayActivity.class);
        intent.putExtra("rating", rating);  // Pass the rating to TeamDisplayActivity
        startActivity(intent);
    }



    private void compareRatings(String newRating) {
        int newRatingValue = Integer.parseInt(newRating);
        int sumOfRatings = 0;
        int numberOfCards = 0;

        // Loop through all the selected card ratings to get the sum
        for (String rating : cardRatingsMap.values()) {
            sumOfRatings += Integer.parseInt(rating);
            numberOfCards++;
        }

        // If 11 cards are selected, compare the average rating with the new rating
        if (numberOfCards == 11) {
            int averageRating = sumOfRatings / numberOfCards;

            String comparisonResult;
            if (newRatingValue > averageRating) {
                comparisonResult = "New rating is higher than the average of selected cards.";
            } else if (newRatingValue < averageRating) {
                comparisonResult = "New rating is lower than the average of selected cards.";
            } else {
                comparisonResult = "New rating is equal to the average of selected cards.";
            }

            // Display the comparison result and score
            Toast.makeText(this, comparisonResult, Toast.LENGTH_LONG).show();
            
        }
    }




}
