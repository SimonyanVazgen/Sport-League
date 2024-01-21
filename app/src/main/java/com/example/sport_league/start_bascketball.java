package com.example.sport_league;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sport_league.R;
import com.example.sport_league.start_bascketball_sr1;
import com.example.sport_league.start_bascketball_sr2;
import com.example.sport_league.start_bascketball_sr3;
import com.example.sport_league.start_bascketball_sr4;
import com.example.sport_league.start_bascketball_sr5;

import java.util.Random;

public class start_bascketball extends AppCompatActivity {

    private ImageView randomImageView;
    private int[] imageArray1 = {R.drawable.bsr1, R.drawable.bsr2, R.drawable.bsr3, R.drawable.bsr4, R.drawable.bsr5};
    private Class<? extends AppCompatActivity>[] activityArray = new Class[]{
            start_bascketball_sr1.class,    start_bascketball_sr2.class,    start_bascketball_sr3.class,    start_bascketball_sr4.class,    start_bascketball_sr5.class
            // Add more activity classes as needed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_bascketball);

        randomImageView = findViewById(R.id.ImageView);
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
