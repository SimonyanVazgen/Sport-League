package com.example.sport_league;

public class ChatMessage {
    private String senderId;
    private String message;

    public ChatMessage() {
        // Default constructor required for Firebase DataSnapshot
    }

    public ChatMessage(String senderId, String message) {
        this.senderId = senderId;
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
