package com.example.sport_league;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TradeScreenActivity extends AppCompatActivity {
    private TextView tradeDetailsTextView;
    private Button acceptButton, rejectButton;
    private DatabaseReference sessionRef;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_screen);

        tradeDetailsTextView = findViewById(R.id.tradeDetailsTextView);
        acceptButton = findViewById(R.id.acceptButton);
        rejectButton = findViewById(R.id.rejectButton);

        // Retrieve session ID passed from previous activity
        sessionId = getIntent().getStringExtra("sessionId");
        if (sessionId == null) {
            Toast.makeText(this, "Session ID is required", Toast.LENGTH_LONG).show();
            finish(); // Exit the activity if no session ID
            return;
        }

        // Firebase reference to the session
        sessionRef = FirebaseDatabase.getInstance().getReference("sessions").child(sessionId);

        setupTradeDetails();

        acceptButton.setOnClickListener(v -> updateTradeStatus("accepted"));
        rejectButton.setOnClickListener(v -> updateTradeStatus("rejected"));
    }

    private void setupTradeDetails() {
        sessionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Assuming trade details are stored as a string under a child node named 'tradeDetails'
                String tradeDetails = dataSnapshot.child("tradeDetails").getValue(String.class);
                if (tradeDetails != null) {
                    tradeDetailsTextView.setText(tradeDetails);
                } else {
                    tradeDetailsTextView.setText("No details available for this trade.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(TradeScreenActivity.this, "Error fetching trade details: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTradeStatus(String status) {
        sessionRef.child("tradeStatus").setValue(status)
                .addOnSuccessListener(aVoid -> Toast.makeText(TradeScreenActivity.this, "Trade " + status, Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(TradeScreenActivity.this, "Failed to update trade status.", Toast.LENGTH_SHORT).show());
    }
}
