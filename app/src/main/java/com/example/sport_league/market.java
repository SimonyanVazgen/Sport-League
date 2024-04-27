package com.example.sport_league;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class market extends AppCompatActivity {
    private int totalAmount = 1200;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        final TextView remainingAmountTextView = findViewById(R.id.yndanurpox);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        totalAmount = sharedPreferences.getInt("totalAmount", 1200);  // Get the initial total amount
        remainingAmountTextView.setText(String.valueOf(totalAmount));  // Display the initial total amount

        Button purchaseButton = findViewById(R.id.get_market);
        final int productCost = 300;  // Cost of the card

        purchaseButton.setOnClickListener(v -> {
            if (totalAmount >= productCost) {
                totalAmount -= productCost;  // Subtract the cost from the total amount
                remainingAmountTextView.setText(String.valueOf(totalAmount));  // Update the display
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("totalAmount", totalAmount);  // Save the new total amount
                editor.apply();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("newCardImage", R.drawable.cordoba);  // Replace 'card_image' with your actual drawable resource ID
                setResult(Activity.RESULT_OK, resultIntent);  // Set the result for the My_cards activity
                finish();  // Close this activity and return
            } else {
                Toast.makeText(this, "Not enough currency", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
