package com.example.sport_league;

import android.os.Bundle;
import android.view.View;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomTradingActivity extends AppCompatActivity {
    private TextView statusTextView;
    private DatabaseReference databaseUsers;
    private Button findUserButton;
    private DatabaseReference databaseInteractions;
    private String currentUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_trading);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        databaseInteractions = FirebaseDatabase.getInstance().getReference("interactions");
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        ImageButton callButton = findViewById(R.id.call);
        callButton.setOnClickListener(view -> initiateInteractionWithRandomUser());

        statusTextView = findViewById(R.id.statusTextView);
        findUserButton = findViewById(R.id.randomButton);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        findUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findRandomOnlineUser();
            }
        });
    }

    private void findRandomOnlineUser() {
        databaseUsers.orderByChild("isOnline").equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> userIds = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Ensure not to select the current user
                    if (snapshot.child("isOnline").getValue(Boolean.class) && !snapshot.getKey().equals(FirebaseDatabase.getInstance().getReference("users").push().getKey())) {
                        userIds.add(snapshot.getKey());
                    }
                }

                if (!userIds.isEmpty()) {
                    Random random = new Random();
                    int index = random.nextInt(userIds.size());
                    String selectedUserId = userIds.get(index);
                    databaseUsers.child(selectedUserId).child("username").get().addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.getResult().exists()) {
                            String username = task.getResult().getValue(String.class);
                            statusTextView.setText("Random online user: " + username);
                            setOnline(selectedUserId);
                        } else {
                            statusTextView.setText("Failed to fetch username.");
                        }
                    });
                } else {
                    statusTextView.setText("No online users found.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                statusTextView.setText("Failed to load users: " + databaseError.getMessage());
            }
        });
    }

    private void setOnline(String userId) {
        databaseUsers.child(userId).child("isOnline").setValue(true)
                .addOnSuccessListener(aVoid -> Toast.makeText(RandomTradingActivity.this, "User set to online successfully.", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(RandomTradingActivity.this, "Failed to set user online.", Toast.LENGTH_SHORT).show());
    }



    private void initiateInteractionWithRandomUser() {
        // Assuming you fetch a random user here and get their userId as 'selectedUserId'
        String selectedUserId = "userId2"; // This should be dynamically determined
        String sessionId = databaseInteractions.push().getKey();

        Map<String, Object> interactionData = new HashMap<>();
        interactionData.put("initiator", currentUserId);
        interactionData.put("target", selectedUserId);
        interactionData.put("status", "requested");

        databaseInteractions.child(sessionId).setValue(interactionData)
                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Interaction initiated.", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to initiate interaction.", Toast.LENGTH_SHORT).show());
    }
}

