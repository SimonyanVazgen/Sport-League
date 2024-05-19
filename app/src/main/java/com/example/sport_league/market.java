package com.example.sport_league;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sport_league.My_cards;

import java.util.Timer;
import java.util.TimerTask;

public class market extends AppCompatActivity {
    private int totalAmount;
    private SharedPreferences sharedPreferences;
    private long purchaseTime;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        TextView remainingAmountTextView = findViewById(R.id.coinsTextView);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.totalAmount = this.sharedPreferences.getInt("totalAmount", 1200);

        Intent incomingIntent = getIntent();
        if (incomingIntent.hasExtra("coinChange")) {
            int coinChange = incomingIntent.getIntExtra("coinChange", 0);
            this.totalAmount += coinChange;
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putInt("totalAmount", this.totalAmount);
            editor.apply();
        }

        remainingAmountTextView.setText(String.valueOf(this.totalAmount));

        // Setup the purchase button
        Button purchaseButton = findViewById(R.id.get_market);
        int productCost = 300;
        purchaseButton.setOnClickListener(v -> {
            if (this.totalAmount >= productCost) {
                this.totalAmount -= productCost;
                remainingAmountTextView.setText(String.valueOf(this.totalAmount));
                SharedPreferences.Editor editor = this.sharedPreferences.edit();
                editor.putInt("totalAmount", this.totalAmount);
                editor.apply();

                Intent intent = new Intent(this, My_cards.class);
                intent.putExtra("newCardUri", "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/muriel.png?alt=media&token=d1e30692-b168-47fb-9d2c-9bd70821d53e");
                intent.putExtra("newCardRating", "95");
                startActivityForResult(intent, 1);

                // Store the purchase time
                purchaseTime = System.currentTimeMillis();

                // Start the timer
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // Change the card and display the time
                        changeCardAndShowTime();
                    }
                }, 300000); // 5 minutes
            } else {
                Toast.makeText(market.this, "Not enough currency", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imageView17 = findViewById(R.id.imageView7);
        String img17 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/muriel.png?alt=media&token=d1e30692-b168-47fb-9d2c-9bd70821d53e";
        Glide.with(this).load(img17).into(imageView17);
    }

    private void changeCardAndShowTime() {
        // Change the card image
        ImageView imageView = findViewById(R.id.imageView7);
        String newCardImageUri = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DHHdfeWWAAE5I1J.png?alt=media&token=283bca78-3497-47ff-b48c-ed2b02661d01";
        Glide.with(this).load(newCardImageUri).into(imageView);

        // Display the current time
        TextView timeTextView = findViewById(R.id.timeTextView);
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - purchaseTime;
        String timeElapsed = formatElapsedTime(elapsedTime);
        timeTextView.setText(timeElapsed);
    }

    private String formatElapsedTime(long elapsedTime) {
        long minutes = elapsedTime / 60000; // 60,000 milliseconds in a minute
        long seconds = (elapsedTime % 60000) / 1000;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // Handle the result if necessary
        }
    }
}
