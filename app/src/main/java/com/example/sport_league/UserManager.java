package com.example.sport_league;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserManager {
    private DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

    // Method to update user data
    public void updateUserData(String userId, User user) {
        usersRef.child(userId).setValue(user);
    }

    // Inner class to model User data
    public static class User {
        public String username;
        public String email;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }
}
