package com.example.sport_league;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class call extends AppCompatActivity {
    private DatabaseReference databaseInteractions;
    private TextView usernameTextView;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        // Initialize Firebase references
        databaseInteractions = FirebaseDatabase.getInstance().getReference("interactions");
        usernameTextView = findViewById(R.id.usernameTextView); // Ensure this ID matches in your layout
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        listenForInteractionRequests();
    }

    private void listenForInteractionRequests() {
        Query query = databaseInteractions.orderByChild("target").equalTo(currentUserId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String initiatorId = snapshot.child("initiator").getValue(String.class);
                    fetchInitiatorUsername(initiatorId);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(call.this, "Error fetching interaction data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchInitiatorUsername(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
        userRef.child("username").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String username = task.getResult().getValue(String.class);
                usernameTextView.setText(username);
            } else {
                Toast.makeText(call.this, "Failed to fetch username.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
