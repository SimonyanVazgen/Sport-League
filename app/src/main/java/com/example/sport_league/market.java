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

/**
 *
 */
public class market extends AppCompatActivity {
    private int totalAmount;
    private SharedPreferences sharedPreferences;

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

        // Set up the purchase button
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
                intent.putExtra("newCardUri", "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/muriel.png?alt=media&token=d1e30692-b168-47fb-9d2c-9bd70821d53e"); // Replace with actual card image URI
                intent.putExtra("newCardRating", "95");
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(market.this, "Not enough currency", Toast.LENGTH_SHORT).show();
            }
        });


        ImageView imageView17 = findViewById(R.id.imageView7);
        String img17 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/muriel.png?alt=media&token=d1e30692-b168-47fb-9d2c-9bd70821d53e";
        Glide.with(this).load(img17).into(imageView17);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

        } else {

        }
    }
}
