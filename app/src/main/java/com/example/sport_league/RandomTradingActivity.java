package com.example.sport_league;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTradingActivity extends AppCompatActivity {

    TextView statusTextView, usernameTextView;
    private DatabaseReference databaseUsers, tradeRequestsRef;
    private Button findUserButton;
    private ImageButton sendRequestButton;
    private String selectedUserId = "", selectedUsername = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_trading);

        statusTextView = findViewById(R.id.statusTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        findUserButton = findViewById(R.id.randomButton);
        sendRequestButton = findViewById(R.id.call);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        tradeRequestsRef = FirebaseDatabase.getInstance().getReference("tradeRequests");

        findUserButton.setOnClickListener(v -> findRandomOnlineUser());
        sendRequestButton.setOnClickListener(v -> sendTradeRequest());
    }

    private void findRandomOnlineUser() {
        databaseUsers.orderByChild("isOnline").equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> userIds = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userIds.add(snapshot.getKey());
                }

                if (!userIds.isEmpty()) {
                    Random random = new Random();
                    int index = random.nextInt(userIds.size());
                    selectedUserId = userIds.get(index);
                    selectedUsername = "Anonymous User"; // Changed to a generic name
                    usernameTextView.setText(selectedUsername);
                    statusTextView.setText("Random online user found. Ready to send a trade request.");
                } else {
                    statusTextView.setText("No online users found.");
                    usernameTextView.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                statusTextView.setText("Failed to load users: " + databaseError.getMessage());
                usernameTextView.setText("");
            }
        });
    }

    private void sendTradeRequest() {
        if (!selectedUserId.isEmpty()) {
            TradeRequest tradeRequest = new TradeRequest(FirebaseAuth.getInstance().getCurrentUser().getUid(), selectedUserId, "pending");
            tradeRequestsRef.push().setValue(tradeRequest)
                    .addOnSuccessListener(aVoid -> Toast.makeText(RandomTradingActivity.this, "Trade request sent.", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(RandomTradingActivity.this, "Failed to send trade request.", Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(this, "No user selected.", Toast.LENGTH_SHORT).show();
        }
    }
}
