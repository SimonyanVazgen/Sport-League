// football_next.java
package com.example.sport_league;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class football_next extends AppCompatActivity {
    private Button footballPlayerButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_next);

        footballPlayerButton = findViewById(R.id.football_player);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        checkPlayerStatus("player1", footballPlayerButton);
    }

    private void checkPlayerStatus(String playerId, Button playerButton) {
        databaseReference.child("players").child(playerId).child("isOnline")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Boolean isOnline = dataSnapshot.getValue(Boolean.class);
                        if (isOnline != null && isOnline) {
                            playerButton.setText(playerId + " is online");
                        } else {
                            playerButton.setText(playerId + " is offline");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
    }
}

