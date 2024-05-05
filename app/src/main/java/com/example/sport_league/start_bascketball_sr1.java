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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.HashMap;

public class start_bascketball_sr1 extends AppCompatActivity {

    private Button[] buttons = new Button[5];
    private Button lastClickedButton = null;
    private TextView ratingTextView, ratingTextView2, scoreTextView;
    private HashMap<Integer, String> cardRatingsMap = new HashMap<>();
    private int cardsSelected = 0;
    private int selectedButtonId;
    private Button button1;
    private int scoreWins = 0;
    private int scoreLosses = 0;
    private static final int TEAM_DISPLAY_REQUEST_CODE = 1;
    private static final int MY_CARDS_REQUEST_CODE = 2;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_bascketball_sr1);

        button1 = findViewById(R.id.viewTeamButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        ratingTextView2 = findViewById(R.id.ratingTextView2);

        button1.setOnClickListener(v -> {
            Intent intent = new Intent(start_bascketball_sr1.this, TeamDisplayActivity1.class);
            startActivityForResult(intent, TEAM_DISPLAY_REQUEST_CODE);
        });

        int[] buttonIds = {
                R.id.button12bsr1, R.id.button13bsr1, R.id.button14bsr1, R.id.button15bsr1, R.id.button16bsr1
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
                    if (cardsSelected < 5) {
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
        if (cardsSelected < 5) {
            Intent intent = new Intent(start_bascketball_sr1.this, start_basketball_my_cards.class);
            startActivityForResult(intent, MY_CARDS_REQUEST_CODE);
        } else {
            Toast.makeText(this, "Maximum of 5 cards are already selected.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == TEAM_DISPLAY_REQUEST_CODE) {
                handleTeamDisplayResult(data);
            } else if (requestCode == MY_CARDS_REQUEST_CODE) {
                handleMyCardsResult(data);
            }
        }
    }

    private void handleMyCardsResult(Intent data) {
        String imageUri = data.getStringExtra("imageUri");
        String cardRating = data.getStringExtra("cardRating");
        if (imageUri != null && cardRating != null) {
            updateButtonWithImage(imageUri, cardRating);
        }
    }

    private void handleTeamDisplayResult(Intent data) {
        String cardRating = data.getStringExtra("cardRating");
        if (cardRating != null) {
            ratingTextView2.setText("Rating: " + cardRating);
            if (cardsSelected == 5) {
                compareRatings(cardRating);
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
            if (cardsSelected == 5) {
                compareRatings(cardRating);
            }
        } else {
            Toast.makeText(this, "Button to update not found.", Toast.LENGTH_SHORT).show();
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
            int averageRating = sumOfRatings / cardRatingsMap.size();

            if (newRatingValue > averageRating) {
                scoreWins++;
            } else if (newRatingValue < averageRating) {
                scoreLosses++;
            }

            scoreTextView.setText(scoreWins + " - " + scoreLosses);

            checkForEndGame();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid rating format", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkForEndGame() {
        if (scoreWins == 5 || scoreLosses == 5) {
            displayResultButton();
        }
    }

    private void displayResultButton() {
        Button resultButton = findViewById(R.id.resultButton);
        resultButton.setVisibility(View.VISIBLE);

        Intent resultIntent = new Intent(start_bascketball_sr1.this, market.class);
        if (scoreWins >= 5) {
            resultButton.setText("You won! +500 coins +5 sport cup");
            resultIntent.putExtra("gameResult", "win");
            resultIntent.putExtra("coinChange", 500);
        } else if (scoreLosses >= 3) {
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
