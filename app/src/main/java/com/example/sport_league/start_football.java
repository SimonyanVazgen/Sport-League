package com.example.sport_league;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

/**
 *
 */
public class start_football extends AppCompatActivity {

    private ImageView randomImageView;
    private TextView sportCupTextView;
    private int currentImageIndex = 0;
    private final String[] imageUrls = new String[]{
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sr1.jpg?alt=media&token=23f7309b-bf59-41ee-8da4-8371182fbb70",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sr2.jpg?alt=media&token=4a937abd-737c-4c0f-a6ab-be5da3cf93cf",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sr1.jpg?alt=media&token=23f7309b-bf59-41ee-8da4-8371182fbb70",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sr2.jpg?alt=media&token=4a937abd-737c-4c0f-a6ab-be5da3cf93cf",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sr3.jpg?alt=media&token=ae725bbc-1241-46cf-9129-efbf19c9e7db",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sr4.jpg?alt=media&token=4c8c561c-91c1-4bdd-a82d-4b952a4bb122",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sr5.jpg?alt=media&token=9da5b5f4-d9f3-4b9f-be79-dfde96b8422d",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sr6.jpg?alt=media&token=8e6e9d99-0f81-4209-b652-1bccb58f928f",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sr7.jpg?alt=media&token=3514d97f-b0eb-4879-bd25-463304d0e269",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sr8.jpg?alt=media&token=f0d649df-9af6-4baa-a000-2c08734a8212"
    };

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_football);

        this.randomImageView = findViewById(R.id.ImageView);
        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        this.sportCupTextView = findViewById(R.id.SportCup);

        loadSportCupCount();

        Glide.with(this).load(this.imageUrls[this.currentImageIndex]).into(this.randomImageView);

        leftButton.setOnClickListener(v -> {
            if (this.currentImageIndex > 0) {
                this.currentImageIndex--;
            } else {
                this.currentImageIndex = this.imageUrls.length - 1;
            }
            updateImageView();
        });

        rightButton.setOnClickListener(v -> {
            if (this.currentImageIndex < this.imageUrls.length - 1) {
                this.currentImageIndex++;
            } else {
                this.currentImageIndex = 0;
            }
            updateImageView();
        });

        this.randomImageView.setOnClickListener(view -> {
            Intent intent = new Intent(start_football.this, start_football_sr.class);
            startActivityForResult(intent, 1);  // Request code 1 for identifying the result
        });



               ImageView imageView1 = findViewById(R.id.imageView20);
        String img1 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DALL%C2%B7E%202024-05-01%2021.31.29%20-%20A%20realistic%20and%20detailed%20illustration%20of%20a%20sports%20trophy%20cup%2C%20typically%20awarded%20at%20sporting%20events.%20The%20cup%20should%20have%20a%20shiny%20metallic%20finish%2C%20possi.webp.png?alt=media&token=13a1fdb8-e14d-45c9-8b73-2699b33e6645";
        Glide.with(this).load(img1).into(imageView1);


    }

    private void updateImageView() {
        Glide.with(this).load(this.imageUrls[this.currentImageIndex]).into(this.randomImageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            String gameResult = data.getStringExtra("gameResult");
            updateSportCupScore(gameResult);
        }
    }

    private void updateSportCupScore(String result) {
        int currentScore = Integer.parseInt(this.sportCupTextView.getText().toString());
        if ("win".equals(result)) {
            currentScore += 5;
        } else if ("loss".equals(result)) {
            currentScore = Math.max(0, currentScore - 3); // Prevent negative scores
        }
        this.sportCupTextView.setText(String.valueOf(currentScore));
        saveSportCupCount(currentScore);
    }

    private void saveSportCupCount(int count) {
        SharedPreferences sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("SportCupCount", count);
        editor.apply();
    }

    private void loadSportCupCount() {
        SharedPreferences sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        int sportCupCount = sharedPref.getInt("SportCupCount", 0);
        this.sportCupTextView.setText(String.valueOf(sportCupCount));
    }
}
