package com.example.sport_league;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sport_league.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fortradingtwoplayer extends AppCompatActivity {
    private TextView playerOneName, playerTwoName, chatDisplay;
    private EditText chatInput;
    private Button tradeButton, sendChatButton;
    private DatabaseReference sessionRef, chatRef;
    private String sessionId, playerOneId, playerTwoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortradingtwoplayer);

        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);
        chatDisplay = findViewById(R.id.chatDisplay);
        chatInput = findViewById(R.id.chatInput);
        tradeButton = findViewById(R.id.tradeButton);
        sendChatButton = findViewById(R.id.sendChatButton);

        sessionId = getIntent().getStringExtra("sessionId");
        if (sessionId == null) {
            Toast.makeText(this, "No session ID provided", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        sessionRef = FirebaseDatabase.getInstance().getReference("sessions").child(sessionId);
        chatRef = sessionRef.child("chat");

        sessionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    playerOneId = dataSnapshot.child("player1Id").getValue(String.class);
                    playerTwoId = dataSnapshot.child("player2Id").getValue(String.class);
                    updateUI(playerOneId, playerTwoId);
                } else {
                    Toast.makeText(Fortradingtwoplayer.this, "Session data not found.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Fortradingtwoplayer.this, "Failed to load session data.", Toast.LENGTH_LONG).show();
            }
        });

        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    StringBuilder chatBuilder = new StringBuilder();
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String message = messageSnapshot.child("message").getValue(String.class);
                        chatBuilder.append(message).append("\n");
                    }
                    chatDisplay.setText(chatBuilder.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Fortradingtwoplayer.this, "Failed to load chat data.", Toast.LENGTH_SHORT).show();
            }
        });

        sendChatButton.setOnClickListener(v -> {
            String message = chatInput.getText().toString();
            if (!message.isEmpty()) {
                chatRef.push().child("message").setValue(message);
                chatInput.setText("");
            }
        });

        tradeButton.setOnClickListener(v -> executeTrade());
    }

    private void updateUI(String playerOneId, String playerTwoId) {
        fetchPlayerName(playerOneId, playerOneName);
        fetchPlayerName(playerTwoId, playerTwoName);
    }

    private void fetchPlayerName(String userId, final TextView playerNameView) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("username");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    playerNameView.setText(dataSnapshot.getValue(String.class));
                } else {
                    playerNameView.setText("Unknown Player");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                playerNameView.setText("Error loading username");
            }
        });
    }

    private void executeTrade() {
        sessionRef.child("tradeCompleted").setValue(true)
                .addOnSuccessListener(aVoid -> Toast.makeText(Fortradingtwoplayer.this, "Trade completed!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(Fortradingtwoplayer.this, "Failed to complete trade.", Toast.LENGTH_SHORT).show());
    }
}
