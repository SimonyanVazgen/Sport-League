package com.example.sport_league;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SessionManager {
    private DatabaseReference sessionsRef = FirebaseDatabase.getInstance().getReference("sessions");

    // Create a new session
    public void createSession(String playerOneId, String playerTwoId) {
        String sessionId = sessionsRef.push().getKey();
        if (sessionId != null) {
            Session session = new Session(playerOneId, playerTwoId);
            sessionsRef.child(sessionId).setValue(session);
        }
    }

    // Update session status
    public void updateSessionStatus(String sessionId, boolean isCompleted) {
        sessionsRef.child(sessionId).child("tradeCompleted").setValue(isCompleted);
    }

    // Inner class to model Session data
    public static class Session {
        public String playerOneId;
        public String playerTwoId;
        public boolean tradeCompleted;

        public Session(String playerOneId, String playerTwoId) {
            this.playerOneId = playerOneId;
            this.playerTwoId = playerTwoId;
            this.tradeCompleted = false;
        }
    }
}
