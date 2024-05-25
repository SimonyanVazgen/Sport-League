package com.example.sport_league;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user;
    private  Button callButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeButtons();
        checkUserAuthentication();
    }

    private void initializeButtons() {
        findViewById(R.id.Start).setOnClickListener(v -> startNewActivity(startic_heto.class));
        findViewById(R.id.box_card).setOnClickListener(v -> startNewActivity(box_cards.class));
        findViewById(R.id.card).setOnClickListener(v -> startNewActivity(My_cards.class));
        findViewById(R.id.market).setOnClickListener(v -> startNewActivity(market.class));
        findViewById(R.id.card_bascetball).setOnClickListener(v -> startNewActivity(start_basketball_my_cards.class));
        findViewById(R.id.trading).setOnClickListener(v -> startNewActivity(treading.class));
        findViewById(R.id.logout).setOnClickListener(v -> logoutUser());
        callButton = findViewById(R.id.call);
        callButton.setOnClickListener(v -> startNewActivity(call.class));
    }

    private void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

    private void checkUserAuthentication() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            logoutUser();
        }
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }
}
