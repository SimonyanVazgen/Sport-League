package com.example.sport_league;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class start_football extends AppCompatActivity {

    private ImageView randomImageView;
    private Button leftButton;
    private Button rightButton;
    private int currentImageIndex = 0;
    private String[] imageUrls = new String[]{
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_football);

        randomImageView = findViewById(R.id.ImageView);
        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);

        Glide.with(this).load(imageUrls[currentImageIndex]).into(randomImageView);

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImageIndex > 0) {
                    currentImageIndex--;
                } else {
                    currentImageIndex = imageUrls.length - 1;
                }
                updateImageView();
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImageIndex < imageUrls.length - 1) {
                    currentImageIndex++;
                } else {
                    currentImageIndex = 0;
                }
                updateImageView();
            }
        });

        randomImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the activity corresponding to the current image
                Intent intent = new Intent(start_football.this, start_football_sr.class);
                startActivity(intent);


            }
        });



    }

    private void updateImageView() {
        Glide.with(this)
                .load(imageUrls[currentImageIndex])
                .into(randomImageView);
    }












}
