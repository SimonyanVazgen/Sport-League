package com.example.sport_league;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class start_football_sr4 extends AppCompatActivity {

    private static final int IMAGE_SELECTION_REQUEST = 1;
    // Adjusted the array size to 11 since you have 11 buttons
    private boolean[] buttonImagesSet = new boolean[11]; // Tracking state for each button
    private int selectedButtonId = 0; // To track the currently selected button

    @Override
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_football_sr4);

        // Initialize buttonImagesSet to false
        for (int i = 0; i < buttonImagesSet.length; i++) {
            buttonImagesSet[i] = false;
        }

        // Setup listeners for all buttons
        int[] buttonIds = new int[]{
                R.id.buttonsr4, R.id.button2sr4, R.id.button3sr4, R.id.button4sr4,
                R.id.button5sr4, R.id.button6sr4, R.id.button7sr4, R.id.button8sr4,
                R.id.button9sr4, R.id.button10sr4, R.id.button11sr4
        };

        for (int buttonId : buttonIds) {
            setupButtonClickListener(buttonId);
        }

        // Check and update the state of the "Next" button initially
        checkNextButtonState();
    }

    private void setupButtonClickListener(int buttonId) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(start_football_sr4.this, My_cards.class);
            startActivityForResult(intent, IMAGE_SELECTION_REQUEST);
            selectedButtonId = buttonId; // Remember which button was clicked
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_SELECTION_REQUEST && resultCode == RESULT_OK && data != null) {
            String imageUri = data.getStringExtra("imageUri");
            if (selectedButtonId != 0) { // Check if a valid button was selected
                Button button = findViewById(selectedButtonId);
                Glide.with(this).load(imageUri).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        button.setBackground(resource);
                        updateButtonImageSetState(selectedButtonId, true);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
            }
        }
    }

    private void updateButtonImageSetState(int buttonId, boolean state) {
        int index = -1;
        if (buttonId == R.id.buttonsr4) {
            index = 0;
        } else if (buttonId == R.id.button2sr4) {
            index = 1;
        } else if (buttonId == R.id.button3sr4) {
            index = 2;
        } else if (buttonId == R.id.button4sr4) {
            index = 3;
        } else if (buttonId == R.id.button5sr4) {
            index = 4;
        } else if (buttonId == R.id.button6sr4) {
            index = 5;
        } else if (buttonId == R.id.button7sr4) {
            index = 6;
        } else if (buttonId == R.id.button8sr4) {
            index = 7;
        } else if (buttonId == R.id.button9sr4) {
            index = 8;
        } else if (buttonId == R.id.button10sr4) {
            index = 9;
        } else if (buttonId == R.id.button11sr4) {
            index = 10;
        }

        if (index != -1) {
            buttonImagesSet[index] = state;
            checkNextButtonState();
        }
    }


    private void checkNextButtonState() {
        Button nextButton = findViewById(R.id.next_ft4);
        boolean allSet = true;

        for (boolean setImage : buttonImagesSet) {
            if (!setImage) {
                allSet = false;
                break;
            }
        }

        nextButton.setEnabled(allSet); // Enable the "Next" button only if all images are set

        if (allSet) {
            nextButton.setOnClickListener(v -> {
                Intent intent = new Intent(start_football_sr4.this, football_next.class);
                startActivity(intent);
            });
        } else {
            nextButton.setOnClickListener(null); // Remove click listener if not all images are set
        }
    }
}
