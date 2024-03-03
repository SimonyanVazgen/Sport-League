package com.example.sport_league;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private TextInputEditText editTextEmail, editTextPassword;
    private Button buttonReg;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null && currentUser.isEmailVerified()) {
            // User is signed in and email is verified, navigate to the MainActivity
            navigateToMainActivity();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.prgressBar);
        textView = findViewById(R.id.loginNow);

        // Click listener for navigating to the login screen
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });

        // Click listener for the registration process
        buttonReg.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        progressBar.setVisibility(View.VISIBLE);
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(Register.this, "Enter both email and password", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        // Create a new user with email and password
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        sendVerificationEmail(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void sendVerificationEmail(FirebaseUser user) {
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(this, task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            // Optionally, navigate to another activity here or prompt the user to check their email
                        } else {
                            // Email not sent, so display message and update the UI
                            Toast.makeText(Register.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
