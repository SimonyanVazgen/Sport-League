package com.example.sport_league;

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
    boolean ara = false;
    int musr = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        // Assuming you have separate TextViews to display the remaining amounts
        final TextView remainingAmountTextView = findViewById(R.id.yndanurpox);
        final TextView remainingAmountTextView1 = findViewById(R.id.yndanurpox);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Assuming you have Buttons to trigger the purchases
        Button purchaseButton = findViewById(R.id.get_market);
        Button purchaseButton1 = findViewById(R.id.market_open);

        // Assuming you have TextViews to display the cost of the products
        final int productCost1 = 300;
        final int productCost2 = 200;

        // Retrieve total amounts from SharedPreferences
        totalAmount = sharedPreferences.getInt("totalAmount", 1200);

        // Display the initial total amount
        remainingAmountTextView.setText(String.valueOf(totalAmount));

        // Set an onClickListener for the purchaseButton
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if the total amount is sufficient for the first product
                if (totalAmount >= productCost1) {
                    // Subtract the product cost from the total amount
                    totalAmount -= productCost1;

                    ara = true;
                    // Update the remainingAmountTextView with the new total amount
                    remainingAmountTextView.setText(String.valueOf(totalAmount));
                    musr = 1;

                    // Save the updated totalAmount to SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("totalAmount", totalAmount);

                    editor.apply();


                    } else {
                        Toast.makeText(market.this, "Not enough currency", Toast.LENGTH_SHORT).show();
                    }
                }



        });








        // Set an onClickListener for the purchaseButton1


        // Inside the OnClickListener for your GET button in the market activity


// In the OnClickListener for the GET button in the market activity
//        Button getButton = findViewById(R.id.get_market);
//        getButton.setOnClickListener(v -> {
//            // Perform the purchase logic, check the user's balance, etc.
//
//            // If the purchase is successful, send back the result
//            Intent resultIntent = new Intent();
//            resultIntent.putExtra("newCardImage", R.drawable.cordoba); // Use the actual resource ID of the new card
//            setResult(Activity.RESULT_OK, resultIntent);
//            finish(); // This will close the market activity and go back to My_cards
//        });

        // In market activity
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("newCardImage", R.drawable.cordoba);
//        setResult(Activity.RESULT_OK, resultIntent);
//        finish();


        // Methods related to Mcardadd
        }














    }

