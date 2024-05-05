package com.example.sport_league;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class TeamDisplayActivity1 extends AppCompatActivity {

    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String img6;
    private TextView ratingTextView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_display1);
        ratingTextView = findViewById(R.id.ratingTextView);



        ImageView teamMember2 = findViewById(R.id.imageViewTeam2);
        img2 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-carmelo-anthony-png-653272.png?alt=media&token=8cf4e89e-73fc-4d83-8044-1a907f42c172";
        Glide.with(this).load(img2).into(teamMember2);
        teamMember2.setOnClickListener(v -> returnRating("95"));

        ImageView teamMember3 = findViewById(R.id.imageViewTeam3);
        img3 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-james-harden-png-279421.png?alt=media&token=9e8c30c4-1a34-4d61-b28a-3dafc8cc5e99";
        Glide.with(this).load(img3).into(teamMember3);
        teamMember3.setOnClickListener(v -> returnRating("99"));

        ImageView teamMember4 = findViewById(R.id.imageViewTeam4);
        img4 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-kobe-bryant-png-328894.png?alt=media&token=80eadee8-a517-4bec-a71c-90cb4df04e4b";
        Glide.with(this).load(img4).into(teamMember4);
        teamMember4.setOnClickListener(v -> returnRating("100"));

        ImageView teamMember5 = findViewById(R.id.imageViewTeam5);
        img5 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-nba-2k17-png-2617263.png?alt=media&token=40576ae0-8c3d-4eff-a3a0-883a485376ea";
        Glide.with(this).load(img5).into(teamMember5);
        teamMember5.setOnClickListener(v -> returnRating("95"));

        ImageView teamMember6 = findViewById(R.id.imageViewTeam6);
        img6 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-russell-westbrook-png-168995.png?alt=media&token=ff69f6da-5362-4355-97fc-29e4a2021005";
        Glide.with(this).load(img6).into(teamMember6);
        teamMember6.setOnClickListener(v -> returnRating("92"));




    }

    private void returnRating(String rating) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("cardRating", rating);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }




}