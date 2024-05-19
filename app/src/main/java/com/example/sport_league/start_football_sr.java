package com.example.sport_league;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.HashMap;
import java.util.HashSet;

public class start_football_sr extends AppCompatActivity {

    private Button[] buttons = new Button[11];
    private Button lastClickedButton = null;
    private TextView ratingTextView, ratingTextView2, scoreTextView;
    private HashMap<Integer, String> cardRatingsMap = new HashMap<>();
    private int cardsSelected = 0;
    private int selectedButtonId;
    private int scoreWins = 0;
    private int scoreLosses = 0;
    static final int TEAM_DISPLAY_REQUEST_CODE = 1;
    private static final int MY_CARDS_REQUEST_CODE = 2;
    private Button viewTeamButton;
    public static HashSet<String> selectedImageUris = new HashSet<>();

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_football_sr);

        viewTeamButton = findViewById(R.id.viewTeamButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        ratingTextView2 = findViewById(R.id.ratingTextView2);

        viewTeamButton.setOnClickListener(v -> {
            Intent intent = new Intent(start_football_sr.this, TeamDisplayActivity.class);
            startActivityForResult(intent, TEAM_DISPLAY_REQUEST_CODE);
        });

        int[] buttonIds = {
                R.id.button, R.id.button2, R.id.button123, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11
        };

        for (int i = 0; i < buttonIds.length; i++) {
            buttons[i] = findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(v -> {
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
            });
        }
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
                String cardRating = data.getStringExtra("cardRating");
                if (cardRating != null) {
                    ratingTextView2.setText("Rating: " + cardRating);
                    if (cardsSelected == 11) {
                        compareRatings(cardRating);
                    }
                }
            } else if (requestCode == MY_CARDS_REQUEST_CODE) {
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
            if (cardsSelected == 11) {
                compareRatings(cardRating);
            }
        }
    }

    private void showRatingAndAnimate(View view) {
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

    private void compareRatings(String newRating) {
        try {
            int newRatingValue = Integer.parseInt(newRating);
            int sumOfRatings = 0;
            for (String rating : cardRatingsMap.values()) {
                sumOfRatings += Integer.parseInt(rating);
            }
            int averageRating = sumOfRatings / Math.max(1, cardRatingsMap.size());

            if (newRatingValue > averageRating) {
                scoreWins++;
            } else if(newRatingValue == averageRating){
                scoreWins++;
                scoreLosses++;

            } else {
                scoreLosses++;
            }
            cardRatingsMap.remove(selectedButtonId);

            updateScoreDisplay();
            checkForEndGame();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid rating format", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateScoreDisplay() {
        scoreTextView.setText("Wins: " + scoreWins + " | Losses: " + scoreLosses);
    }

    private void checkForEndGame() {
        if (scoreWins == 5 || scoreLosses == 5) {
            displayResultButton();
        }
    }

    private void displayResultButton() {
        Button resultButton = findViewById(R.id.resultButton);
        resultButton.setVisibility(View.VISIBLE);

        Intent resultIntent = new Intent(this, market.class);
        if (scoreWins >= 5) {
            resultButton.setText("You won! +500 coins +5 sport cup");
            resultIntent.putExtra("gameResult", "win");
            resultIntent.putExtra("coinChange", 500);
        } else if (scoreLosses >= 5) {
            resultButton.setText("You lost! -300 coins -3 sport cup");
            resultIntent.putExtra("gameResult", "loss");
            resultIntent.putExtra("coinChange", -300);
        }

        resultButton.setOnClickListener(v -> {
            startActivity(resultIntent);
            finish();
        });
    }
}
