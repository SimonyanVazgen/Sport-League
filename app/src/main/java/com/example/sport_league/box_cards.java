package com.example.sport_league;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import java.util.HashSet;

/**
 *
 */
public class box_cards extends AppCompatActivity {

    private String img2;
    private String img1;
    private String img3;
    private String img4;
    private String img5;
    private String img6;
    private String img7;


    /**
     *
     */
    public static HashSet<String> selectedImageUris = new HashSet<>();


    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_cards);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("newCardUri") && intent.hasExtra("newCardRating")) {
            String newCardUri = intent.getStringExtra("newCardUri");
            String newCardRating = intent.getStringExtra("newCardRating");  // Use this rating as needed

            ImageView newCardImageView = findViewById(R.id.imageView20);  // Make sure this ID exists
            Glide.with(this).load(newCardUri).into(newCardImageView);

            setResult(Activity.RESULT_OK);  // Signal success
        } else {
//            Toast.makeText(this, "No new card data received.", Toast.LENGTH_LONG).show();
            setResult(Activity.RESULT_CANCELED);
        }


        ImageView imageView1 = findViewById(R.id.imageView);
        img1 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DGunNJZXkAAj1sq.png?alt=media&token=b310592c-02d8-4a57-9cbb-80ebc433dee8";
        Glide.with(this).load(this.img1).into(imageView1);

        ImageView imageView2 = findViewById(R.id.imageView2);
        img2 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DGunOgtXYAIg4oH.png?alt=media&token=aa4c7db5-72a6-403e-bfe0-5a65a4631696";
        Glide.with(this).load(img2).into(imageView2);

        ImageView imageView3 = findViewById(R.id.imageView3);
        img3 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DGunPdEXkAA1CW0.png?alt=media&token=a587d921-59f2-4936-bdb0-196851a47465";
        Glide.with(this).load(img3).into(imageView3);

        ImageView imageView4 = findViewById(R.id.imageView4);
        img4 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DHHdOk7XUAEDnD2.png?alt=media&token=2bcc3b4d-e3f4-49d2-af27-a46dfbe3e9bf";
        Glide.with(this).load(img4).into(imageView4);

        ImageView imageView5 = findViewById(R.id.imageView5);
        img5 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DHHdZYKXcAA_Xya.png?alt=media&token=9b521c82-6142-4bab-8e4f-527ff82155e7";
        Glide.with(this).load(img5).into(imageView5);

        ImageView imageView6 = findViewById(R.id.imageView6);
        img6 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DHHdfeWWAAE5I1J.png?alt=media&token=283bca78-3497-47ff-b48c-ed2b02661d01";
        Glide.with(this).load(img6).into(imageView6);

        ImageView imageView7 = findViewById(R.id.imageView7);
        img7 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DUvcHqIXUAANiDY.png?alt=media&token=9027609f-0944-498d-905f-aae0db707410";
        Glide.with(this).load(img7).into(imageView7);


        imageView1.setOnClickListener(v -> {
            String rating = "88";
            returnResult(img1, imageView1, rating);
        });
        imageView2.setOnClickListener(v -> {
            String rating = "92";
            returnResult(img2, imageView2, rating);
        });
        imageView3.setOnClickListener(v -> {
            String rating = "91";
            returnResult(img3, imageView3, rating);
        });
        imageView4.setOnClickListener(v -> {
            String rating = "96";
            returnResult(img4, imageView4, rating);
        });
        imageView5.setOnClickListener(v -> {
            String rating = "91";
            returnResult(img5, imageView5, rating);
        });
        imageView6.setOnClickListener(v -> {
            String rating = "96";
            returnResult(img6, imageView6, rating);
        });
        imageView7.setOnClickListener(v -> {
            String rating = "93";
            returnResult(img7, imageView7, rating);
        });


        if (selectedImageUris.contains(img1)) {
            imageView1.setVisibility(View.GONE);
        }

        if (selectedImageUris.contains(img2)) {
            imageView2.setVisibility(View.GONE);
        }

        if (selectedImageUris.contains(img3)) {
            imageView3.setVisibility(View.GONE);
        }

        if (selectedImageUris.contains(img4)) {
            imageView4.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img5)) {
            imageView5.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img6)) {
            imageView6.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img7)) {
            imageView7.setVisibility(View.GONE);
        }


    }


    private void returnResult(String imageUri, ImageView imageView, String rating) {
        Log.d("My_cards", "Returning result: " + imageUri + " with rating: " + rating);
        selectedImageUris.add(imageUri);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("imageUri", imageUri);
        resultIntent.putExtra("cardRating", rating);
        setResult(RESULT_OK, resultIntent);
        imageView.setVisibility(View.GONE);
        finish();
    }

}
