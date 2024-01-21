package com.example.sport_league;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sport_league.R;
import com.example.sport_league.start_football_sr;
import com.example.sport_league.start_football_sr2;
import com.example.sport_league.start_football_sr3;
import com.example.sport_league.start_football_sr4;
import com.example.sport_league.start_football_sr5;
import com.example.sport_league.start_football_sr6;
import com.example.sport_league.start_football_sr7;
import com.example.sport_league.start_football_sr8;

import java.util.Random;

public class start_football extends AppCompatActivity {

    private ImageView randomImageView;
    private int[] imageArray1 = {R.drawable.sr1, R.drawable.sr2, R.drawable.sr3, R.drawable.sr4, R.drawable.sr5,R.drawable.sr6,R.drawable.sr7,R.drawable.sr8};
    private Class<? extends AppCompatActivity>[] activityArray = new Class[]{
            start_football_sr.class, start_football_sr2.class, start_football_sr3.class, start_football_sr4.class, start_football_sr5.class, start_football_sr6.class, start_football_sr7.class, start_football_sr8.class
            // Add more activity classes as needed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_football);

        randomImageView = findViewById(R.id.randomImageView);
        randomImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click on the random image, for example, start a new activity
                startRandomActivity();
            }
        });
    }

    public void onRandomButtonClick(View view) {
        // Set a random image when the button is clicked
        int randomIndex1 = new Random().nextInt(imageArray1.length);
        randomImageView.setImageResource(imageArray1[randomIndex1]);
    }

    private void startRandomActivity() {
        // Select a random activity from the activityArray
        int randomIndex = new Random().nextInt(activityArray.length);
        Class<? extends AppCompatActivity> selectedActivity = activityArray[randomIndex];

        // Start the selected activity
        Intent intent = new Intent(this, selectedActivity);
        startActivity(intent);
    }

}
