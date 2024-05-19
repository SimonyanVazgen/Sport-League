package com.example.sport_league;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TradeManager {
    private DatabaseReference tradeRequestsRef = FirebaseDatabase.getInstance().getReference("tradeRequests");

    // Send a trade request
    public void sendTradeRequest(String fromUserId, String toUserId) {
        String requestId = tradeRequestsRef.push().getKey();
        if (requestId != null) {
            TradeRequest request = new TradeRequest(fromUserId, toUserId);
            tradeRequestsRef.child(requestId).setValue(request);
        }
    }

    // Inner class to model Trade Request
    public static class TradeRequest {
        public String fromUserId;
        public String toUserId;
        public String status;

        public TradeRequest(String fromUserId, String toUserId) {
            this.fromUserId = fromUserId;
            this.toUserId = toUserId;
            this.status = "pending";
        }
    }
}
