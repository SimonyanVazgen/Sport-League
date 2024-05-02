//package com.example.sport_league;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class market extends AppCompatActivity {
//    private int totalAmount = 1200;
//    private SharedPreferences sharedPreferences;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_market);
//
//        final TextView remainingAmountTextView = findViewById(R.id.yndanurpox);
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        totalAmount = sharedPreferences.getInt("totalAmount", 1200);  // Get the initial total amount
//        remainingAmountTextView.setText(String.valueOf(totalAmount));  // Display the initial total amount
//
//        Button purchaseButton = findViewById(R.id.get_market);
//        final int productCost = 300;  // Cost of the card
//
//        purchaseButton.setOnClickListener(v -> {
//            if (totalAmount >= productCost) {
//                totalAmount -= productCost;  // Subtract the cost from the total amount
//                remainingAmountTextView.setText(String.valueOf(totalAmount));  // Update the display
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putInt("totalAmount", totalAmount);  // Save the new total amount
//                editor.apply();
//
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra("newCardImage", R.drawable.cordoba);  // Replace 'card_image' with your actual drawable resource ID
//                setResult(Activity.RESULT_OK, resultIntent);  // Set the result for the My_cards activity
//                finish();  // Close this activity and return
//            } else {
//                Toast.makeText(this, "Not enough currency", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
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

public class market extends AppCompatActivity {
    private int totalAmount;
    private SharedPreferences sharedPreferences;
    private String img17;


    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);


      TextView remainingAmountTextView = findViewById(R.id.coinsTextView);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        totalAmount = sharedPreferences.getInt("totalAmount", 1200); // Default value
        remainingAmountTextView.setText(String.valueOf(totalAmount));

        Button purchaseButton = findViewById(R.id.get_market);
        int productCost = 300;

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalAmount >= productCost) {
                    totalAmount -= productCost;
                    remainingAmountTextView.setText(String.valueOf(totalAmount));
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("totalAmount", totalAmount);
                    editor.apply();

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newCardImage", R.drawable.cordoba);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(market.this, "Not enough currency", Toast.LENGTH_SHORT).show();
                }
            }
        });



        ImageView imageView17 = findViewById(R.id.imageView7);
        img17 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/muriel.png?alt=media&token=d1e30692-b168-47fb-9d2c-9bd70821d53e";
        Glide.with(this).load(img17).into(imageView17);



    }


}
