package com.example.sport_league;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fortradingtwoplayer extends AppCompatActivity {
    private EditText chatInput;
    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages = new ArrayList<>();
    private DatabaseReference chatRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortradingtwoplayer);

        chatInput = findViewById(R.id.chatInput);
        Button sendChatButton = findViewById(R.id.sendChatButton);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);

        // Setup RecyclerView
        chatAdapter = new ChatAdapter(chatMessages);
        chatRecyclerView.setAdapter(chatAdapter);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String sessionId = getIntent().getStringExtra("sessionId");
        if (TextUtils.isEmpty(sessionId)) {
            Toast.makeText(this, "Session ID is required", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Reference to the chat node in Firebase
        chatRef = FirebaseDatabase.getInstance().getReference("chats").child(sessionId);

        // Adding a listener to Firebase to retrieve chat messages
        initializeChat();

        sendChatButton.setOnClickListener(v -> {
            String text = chatInput.getText().toString().trim();
            if (!text.isEmpty()) {
                ChatMessage message = new ChatMessage(FirebaseAuth.getInstance().getCurrentUser().getUid(), text);
                chatRef.push().setValue(message);
                chatInput.setText("");  // Clear input after sending
            }
        });
    }

    private void initializeChat() {
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatMessages.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatMessage message = snapshot.getValue(ChatMessage.class);
                    if (message != null) {
                        chatMessages.add(message);
                    }
                }
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Fortradingtwoplayer.this, "Failed to load chat data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
