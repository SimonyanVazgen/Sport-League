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
public class start_bascketball extends AppCompatActivity {

    private ImageView randomImageView;
    private TextView sportCupTextView;
    private int currentImageIndex = 0;
    private final String[] imageUrls = new String[]{
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/bsr1.jpg?alt=media&token=a8dea3e5-d5f4-414e-9ee3-841d021d7f06",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/bsr2.jpg?alt=media&token=3bf82920-1791-48bb-8593-b73a5bce97bb",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/bsr3.jpg?alt=media&token=18ddf486-6eec-4886-bd1a-9970a79bc896",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/bsr4.jpg?alt=media&token=edfda868-84dc-481e-86a1-99df0034c18a",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/bsr5.jpg?alt=media&token=718785c7-9718-4709-a1b5-61572cab8352"
    };

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_bascketball);

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
            Intent intent = new Intent(start_bascketball.this, start_bascketball_sr1.class);
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
        //saveSportCupCount(currentScore);
    }

//    private void saveSportCupCount(int count) {
//        SharedPreferences sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt("SportCupCount", count);
//        editor.apply();
//    }

    private void loadSportCupCount() {
        SharedPreferences sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        int sportCupCount = sharedPref.getInt("SportCupCount", 0);
        this.sportCupTextView.setText(String.valueOf(sportCupCount));
    }
}
