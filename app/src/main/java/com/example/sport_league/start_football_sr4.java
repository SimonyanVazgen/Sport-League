////package com.example.sport_league;
////
////import android.annotation.SuppressLint;
////import android.content.Intent;
////import android.graphics.drawable.Drawable;
////import android.os.Bundle;
////import android.widget.Button;
////import androidx.annotation.NonNull;
////import androidx.annotation.Nullable;
////import androidx.appcompat.app.AppCompatActivity;
////import com.bumptech.glide.Glide;
////import com.bumptech.glide.request.target.CustomTarget;
////import com.bumptech.glide.request.transition.Transition;
////
////public class start_football_sr4 extends AppCompatActivity {
////
////    private static final int IMAGE_SELECTION_REQUEST = 1;
////    private boolean[] buttonImagesSet = new boolean[11]; // Tracking state for each button
////    private int selectedButtonId = 0; // To track the currently selected button
////    private Button[] buttons = new Button[11]; // To keep references to all buttons for easy access
////
////    @Override
////    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_start_football_sr4);
////
////        // Initialize buttonImagesSet to false
////        for (int i = 0; i < buttonImagesSet.length; i++) {
////            buttonImagesSet[i] = false;
////        }
////
////        int[] buttonIds = new int[]{
////                R.id.buttonsr4, R.id.button2sr4, R.id.button3sr4, R.id.button4sr4,
////                R.id.button5sr4, R.id.button6sr4, R.id.button7sr4, R.id.button8sr4,
////                R.id.button9sr4, R.id.button10sr4, R.id.button11sr4
////        };
////
////        for (int i = 0; i < buttonIds.length; i++) {
////            buttons[i] = findViewById(buttonIds[i]);
////            setupButtonClickListener(buttons[i], buttonIds[i]);
////        }
////
////        checkNextButtonState();
////    }
////
////    private void setupButtonClickListener(Button button, int buttonId) {
////        button.setOnClickListener(v -> {
////            Intent intent = new Intent(start_football_sr4.this, My_cards.class);
////            startActivityForResult(intent, IMAGE_SELECTION_REQUEST);
////            selectedButtonId = buttonId; // Remember which button was clicked
////        });
////    }
////
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == IMAGE_SELECTION_REQUEST && resultCode == RESULT_OK && data != null) {
////            String imageUri = data.getStringExtra("imageUri");
////            if (selectedButtonId != 0) { // Check if a valid button was selected
////                Button button = findViewById(selectedButtonId);
////                Glide.with(this).load(imageUri).into(new CustomTarget<Drawable>() {
////                    @Override
////                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
////                        button.setBackground(resource);
////                        updateButtonImageSetState(selectedButtonId, true);
////                    }
////
////                    @Override
////                    public void onLoadCleared(@Nullable Drawable placeholder) {
////                    }
////                });
////            }
////        }
////    }
////
////    private void updateButtonImageSetState(int buttonId, boolean state) {
////        int index = findButtonIndexById(buttonId);
////        if (index != -1) {
////            buttonImagesSet[index] = state;
////            checkNextButtonState();
////        }
////    }
////
////    private int findButtonIndexById(int buttonId) {
////        // Assuming the button IDs are in the same order as your buttons array
////        for (int i = 0; i < buttons.length; i++) {
////            if (buttons[i].getId() == buttonId) {
////                return i;
////            }
////        }
////        return -1; // Not found
////    }
////
////    private void checkNextButtonState() {
////        Button nextButton = findViewById(R.id.lockBtn);
////        boolean allSet = true;
////
////        for (boolean setImage : buttonImagesSet) {
////            if (!setImage) {
////                allSet = false;
////                break;
////            }
////        }
////
////        nextButton.setEnabled(allSet); // Enable the "Next" button only if all images are set
////
////    }
////}
//
//
//package com.example.sport_league;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.view.View;
//import android.view.animation.AlphaAnimation;
//import android.view.animation.Animation;
//import android.widget.Button;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.target.CustomTarget;
//import com.bumptech.glide.request.transition.Transition;
//
//public class start_football_sr4 extends AppCompatActivity {
//
//    private static final int IMAGE_SELECTION_REQUEST = 1;
//    private boolean[] buttonImagesSet = new boolean[11]; // Tracks if an image has been set for each button
//    private Button[] buttons = new Button[11]; // References to all selection buttons
//    private boolean selectionsLocked = false; // Tracks if selections are locked
//    private int selectedButtonId; // ID of the last clicked button for image selection
//    private Button lockBtn;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_start_football_sr4);
//
//        lockBtn = findViewById(R.id.lockBtn); // Replace with your lock button ID
//        configureLockBtn();
//
//        // Initialize button IDs and listeners
//        int[] buttonIds = {
//                R.id.buttonsr4, R.id.button2sr4, R.id.button3sr4, R.id.button4sr4,
//                R.id.button5sr4, R.id.button6sr4, R.id.button7sr4, R.id.button8sr4,
//        R.id.button9sr4, R.id.button10sr4, R.id.button11sr4
//
//
//        };
//
//        for (int i = 0; i < buttonIds.length; i++) {
//            buttons[i] = findViewById(buttonIds[i]);
//            buttons[i].setOnClickListener(v -> {
//                if (!selectionsLocked) {
//                    selectedButtonId = v.getId(); // Store the ID of the clicked button
//                    selectCard();
//                }
//            });
//        }
//    }
//
//    private void selectCard() {
//        // Only proceed if all cards are not yet selected
//        Intent intent = new Intent(this,My_cards.class); // Replace with your actual activity for card selection
//        startActivityForResult(intent, IMAGE_SELECTION_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (selectionsLocked || requestCode != IMAGE_SELECTION_REQUEST || resultCode != RESULT_OK || data == null) {
//            return; // Do not proceed if selections are locked or if result is not as expected
//        }
//
//        String imageUri = data.getStringExtra("imageUri");
//        Button buttonToUpdate = findViewById(selectedButtonId);
//        Glide.with(this).load(imageUri).into(new CustomTarget<Drawable>() {
//            @Override
//            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                buttonToUpdate.setBackground(resource);
//                buttonImagesSet[findButtonIndexById(selectedButtonId)] = true;
//                checkAllButtonsSet();
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//                // Required implementation
//            }
//        });
//    }
//
//    private int findButtonIndexById(int buttonId) {
//        for (int i = 0; i < buttons.length; i++) {
//            if (buttons[i].getId() == buttonId) {
//                return i;
//            }
//        }
//        return -1; // Button ID not found
//    }
//
//    private void checkAllButtonsSet() {
//        for (boolean isSet : buttonImagesSet) {
//            if (!isSet) {
//                return; // Return early if any button has not been set
//            }
//        }
//        // If all buttons have images set, enable the lock button
//        lockBtn.setEnabled(true);
//    }
//
//    private void configureLockBtn() {
//        lockBtn.setOnClickListener(v -> {
//            if (areAllCardsSelected()) {
//                startCountdown();
//            }
//        });
//        lockBtn.setEnabled(false); // Initially, the lock button is disabled
//    }
//
//    private boolean areAllCardsSelected() {
//        for (boolean selected : buttonImagesSet) {
//            if (!selected) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private void startCountdown() {
//        new CountDownTimer(3000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                lockBtn.setText("Unlock " + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//                lockBtn.setText("Unlock");
//                animateButtonDisappearance();
//                selectionsLocked = true;
//            }
//        }.start();
//    }
//
//    private void animateButtonDisappearance() {
//        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
//        fadeOut.setDuration(1000);
//        fadeOut.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                // Nothing needed here
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                lockBtn.setVisibility(View.GONE); // Make the lock button disappear
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                // Not needed, but must be implemented
//            }
//        });
//
//        lockBtn.startAnimation(fadeOut);
//    }
//}



package com.example.sport_league;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class start_football_sr4 extends AppCompatActivity {

    private static final int IMAGE_SELECTION_REQUEST = 1;
    private boolean[] buttonImagesSet = new boolean[11];
    private Button[] buttons = new Button[11];
    private boolean selectionsLocked = false;
    private int selectedButtonId;
    private Button lockBtn;
    private TextView textViewGo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_football_sr4);

        // Reference to the TextView
        textViewGo = findViewById(R.id.textViewGo);

        lockBtn = findViewById(R.id.lockBtn);
        configureLockBtn();

        // Initialize button IDs and listeners
        int[] buttonIds = {

                R.id.buttonsr4, R.id.button2sr4, R.id.button3sr4, R.id.button4sr4,
                R.id.button5sr4, R.id.button6sr4, R.id.button7sr4, R.id.button8sr4,
        R.id.button9sr4, R.id.button10sr4, R.id.button11sr4

        };

        for (int i = 0; i < buttonIds.length; i++) {
            buttons[i] = findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(v -> {
                if (!selectionsLocked) {
                    selectedButtonId = v.getId(); // Store the ID of the clicked button
                    selectCard();
                }
            });
        }
    }

    private void selectCard() {
        Intent intent = new Intent(this, My_cards.class); // Replace with your actual activity for card selection
        startActivityForResult(intent, IMAGE_SELECTION_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (selectionsLocked || requestCode != IMAGE_SELECTION_REQUEST || resultCode != RESULT_OK || data == null) {
            return; // Do not proceed if selections are locked or if result is not as expected
        }

        String imageUri = data.getStringExtra("imageUri");
        Button buttonToUpdate = findViewById(selectedButtonId);
        Glide.with(this).load(imageUri).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                buttonToUpdate.setBackground(resource);
                buttonImagesSet[findButtonIndexById(selectedButtonId)] = true;
                checkAllButtonsSet();
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });
    }

    private int findButtonIndexById(int buttonId) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getId() == buttonId) {
                return i;
            }
        }
        return -1; // Button ID not found
    }

    private void checkAllButtonsSet() {
        for (boolean isSet : buttonImagesSet) {
            if (!isSet) return;
        }
        lockBtn.setEnabled(true);
    }

    private void configureLockBtn() {
        lockBtn.setOnClickListener(v -> {
            if (areAllCardsSelected()) {
                startCountdown();
            }
        });
        lockBtn.setEnabled(false);
    }

    private boolean areAllCardsSelected() {
        for (boolean selected : buttonImagesSet) {
            if (!selected) return false;
        }
        return true;
    }

    private void startCountdown() {
        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                lockBtn.setText("Unlock in " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                selectionsLocked = true;
                animateButtonDisappearance();

            }
        }.start();
    }

    private void animateButtonDisappearance() {
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(1000); // Duration in milliseconds for the fade-out effect
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Nothing needed here
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                lockBtn.setVisibility(View.GONE); // Hide the button after the animation ends
                animateGoText(); // Trigger the "GO" text animation
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Not needed, but required to be implemented
            }
        });

        lockBtn.startAnimation(fadeOut); // Start the fade-out animation on the lock button
    }

    private void animateGoText() {
        textViewGo.setVisibility(View.VISIBLE); // Make the "GO" text visible

        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.0f, 1.0f, // Scale from 0 (not visible) to 1 (full size)
                0.0f, 1.0f, // Scale for Y axis
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point for X to be in the center
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point for Y to be in the center

        scaleAnimation.setDuration(3000); // Duration for the scale animation
        scaleAnimation.setFillAfter(false); // Do not keep the "GO" text at its final size after the animation ends

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Nothing needed here
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Fade out the "GO" text after it scales up
                fadeOutGoText();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Not needed, but required to be implemented
            }
        });

        textViewGo.startAnimation(scaleAnimation); // Start the scale animation for "GO" text
    }

    private void fadeOutGoText() {
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f); // Fade from fully visible to invisible
        fadeOut.setDuration(1000); // Duration for the fade-out effect
        fadeOut.setFillAfter(true); // Keep the "GO" text invisible after the animation ends

        textViewGo.startAnimation(fadeOut); // Start the fade-out animation for "GO" text
    }
}