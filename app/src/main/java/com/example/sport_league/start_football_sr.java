package com.example.sport_league;

import android.annotation.SuppressLint;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class start_football_sr extends AppCompatActivity {

    private static final int IMAGE_SELECTION_REQUEST = 1;
    private Button[] buttons = new Button[11];
    private int selectedButtonId;
    private TextView ratingTextView;
    private int[] cardRatings = {98, 99, 96, 99, 96, 97, 98, 92, 96, 96, 82};
    private View lockBtn;
    private boolean selectionsLocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_football_sr);

        lockBtn = findViewById(R.id.lockBtn);
        ratingTextView = findViewById(R.id.ratingTextView);

        lockBtn.setOnClickListener(v -> {
            if (!selectionsLocked) {
                startCountdown();
            }
        });

        int[] buttonIds = {
                R.id.button, R.id.button2, R.id.button123, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11
        };

        for (int i = 0; i < buttonIds.length; i++) {
            final int index = i;
            buttons[i] = findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(v -> {
                selectedButtonId = v.getId();
                showRating(index);
                selectCard();
            });
        }
    }

    private void selectCard() {
        Intent intent = new Intent(this, My_cards.class);
        startActivityForResult(intent, IMAGE_SELECTION_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_SELECTION_REQUEST && resultCode == RESULT_OK && data != null) {
            String imageUri = data.getStringExtra("imageUri");
            Button buttonToUpdate = findViewById(selectedButtonId);
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
    }
    private void startCountdown() {
        new CountDownTimer(3000, 2000) {
            public void onTick(long millisUntilFinished) {
                ((Button) lockBtn).setText("Unlock " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                ((Button) lockBtn).setText("Unlock");
                animateButtonDisappearance();
                selectionsLocked = true;
                disableCardSelection();
            }
        }.start();
    }

    private void disableCardSelection() {
        for (Button button : buttons) {
            button.setClickable(false);
        }
    }

    private void animateButtonDisappearance() {
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(1000);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                lockBtn.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        lockBtn.startAnimation(fadeOut);
    }

    private void showRating(int index) {
        String ratingText = "Rating: " + cardRatings[index];
        ratingTextView.setText(ratingText);
        ratingTextView.setVisibility(View.VISIBLE);
    }
}
