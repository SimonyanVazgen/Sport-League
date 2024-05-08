package com.example.sport_league;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    private TextInputEditText editTextEmail, editTextPassword, editTextPasswordConfirm, editTextUsername;
    private TextView textView;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseUsers, usernamesRef;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        usernamesRef = FirebaseDatabase.getInstance().getReference("usernames"); // Reference to usernames node

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextPasswordConfirm = findViewById(R.id.password_confirm);
        editTextUsername = findViewById(R.id.username);
        textView = findViewById(R.id.loginNow);
        progressBar = findViewById(R.id.prgressBar);

        textView.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Login.class)));

        findViewById(R.id.btn_register).setOnClickListener(v -> {
            if (validateForm()) {
                String username = editTextUsername.getText().toString().trim();
                checkUsernameAvailability(username);
            }
        });
    }

    private boolean validateForm() {
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextPasswordConfirm.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void checkUsernameAvailability(String username) {
        usernamesRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(Register.this, "Username already taken", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(username);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Register.this, "Failed to check username availability: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser(String username) {
        progressBar.setVisibility(View.VISIBLE);
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUserData(new User(username, email, false), user.getUid());
                    } else {
                        Toast.makeText(Register.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void updateUserData(User user, String userId) {
        databaseUsers.child(userId).setValue(user);
        usernamesRef.child(user.username).setValue(true); // Mark username as taken
        sendVerificationEmail(mAuth.getCurrentUser());
    }

    private void sendVerificationEmail(FirebaseUser user) {
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(task -> {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "Verification email sent to " + user.getEmail(), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(Register.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

class User {
    public String username;
    public String email;
    public boolean isOnline;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, boolean isOnline) {
        this.username = username;
        this.email = email;
        this.isOnline = isOnline;
    }
}
