package com.example.sport_league;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class call extends AppCompatActivity {

    private TextView requestTextView;
    private Button acceptButton, declineButton;
    private DatabaseReference tradeRequestsRef, sessionsRef, usersRef;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        requestTextView = findViewById(R.id.requestTextView);
        acceptButton = findViewById(R.id.acceptButton);
        declineButton = findViewById(R.id.declineButton);

        tradeRequestsRef = FirebaseDatabase.getInstance().getReference("tradeRequests");
        sessionsRef = FirebaseDatabase.getInstance().getReference("sessions");
        usersRef = FirebaseDatabase.getInstance().getReference("users");

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            listenForTradeRequests(currentUserId);
        } else {
            Log.e("FirebaseAuth", "User is not logged in");
            Toast.makeText(this, "User is not logged in", Toast.LENGTH_SHORT).show();
            finish();
        }

        acceptButton.setOnClickListener(v -> acceptTradeRequest());
        declineButton.setOnClickListener(v -> declineTradeRequest());
    }

    private void listenForTradeRequests(String userId) {
        tradeRequestsRef.orderByChild("toUserId").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChildren()) {
                    requestTextView.setText("No trade requests");
                    return;
                }
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TradeRequest request = snapshot.getValue(TradeRequest.class);
                    if (request != null && "pending".equals(request.getStatus())) {
                        fetchUsername(request.getFromUserId());
                        acceptButton.setTag(snapshot.getKey());
                        declineButton.setTag(snapshot.getKey());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(call.this, "Error loading trade requests: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchUsername(String userId) {
        usersRef.child(userId).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    requestTextView.setText("Trade request from " + dataSnapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(call.this, "Failed to load username.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void acceptTradeRequest() {
        String requestKey = (String) acceptButton.getTag();
        if (requestKey == null) {
            Toast.makeText(this, "No valid trade request found.", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference requestRef = tradeRequestsRef.child(requestKey);
        requestRef.child("status").setValue("accepted").addOnSuccessListener(aVoid -> {
            String sessionId = sessionsRef.push().getKey();
            sessionsRef.child(sessionId).child("player1Id").setValue(currentUserId);
            requestRef.child("fromUserId").get().addOnSuccessListener(snapshot -> {
                String fromUserId = snapshot.getValue(String.class);
                sessionsRef.child(sessionId).child("player2Id").setValue(fromUserId);

                Intent intent = new Intent(call.this, Fortradingtwoplayer.class);
                intent.putExtra("sessionId", sessionId);
                startActivity(intent);
                finish();
            });
        }).addOnFailureListener(e -> {
            Toast.makeText(call.this, "Failed to accept trade request.", Toast.LENGTH_SHORT).show();
        });
    }

    private void declineTradeRequest() {
        String requestKey = (String) declineButton.getTag();
        if (requestKey == null) {
            Toast.makeText(this, "No valid trade request to decline.", Toast.LENGTH_SHORT).show();
            return;
        }
        tradeRequestsRef.child(requestKey).child("status").setValue("declined").addOnSuccessListener(aVoid -> {
            Toast.makeText(call.this, "Trade request declined.", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(call.this, "Failed to decline trade request.", Toast.LENGTH_SHORT).show();
        });
    }
}
