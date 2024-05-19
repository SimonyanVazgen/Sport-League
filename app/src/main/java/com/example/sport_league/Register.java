package com.example.sport_league;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

public class Register extends AppCompatActivity {
    private EditText editTextEmail, editTextUsername, editTextPassword, editTextPasswordConfirm;
    private Button buttonRegister;
    private TextView textViewLogin;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewLogin = findViewById(R.id.textViewLogin);
        progressBar = findViewById(R.id.progressBar);

        textViewLogin.setOnClickListener(v -> {
            startActivity(new Intent(Register.this, Login.class));
        });

        buttonRegister.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String passwordConfirm = editTextPasswordConfirm.getText().toString().trim();

            if (!validateForm(email, username, password, passwordConfirm)) {
                return;
            }

            registerUser(email, username, password);
        });
    }

    private boolean validateForm(String email, String username, String password, String passwordConfirm) {
        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Required.");
            valid = false;
        } else {
            editTextEmail.setError(null);
        }

        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Required.");
            valid = false;
        } else {
            editTextUsername.setError(null);
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            editTextPassword.setError("Required and must be at least 6 characters.");
            valid = false;
        } else {
            editTextPassword.setError(null);
        }

        if (TextUtils.isEmpty(passwordConfirm) || !passwordConfirm.equals(password)) {
            editTextPasswordConfirm.setError("Confirm Password does not match.");
            valid = false;
        } else {
            editTextPasswordConfirm.setError(null);
        }

        return valid;
    }

    private void registerUser(String email, String username, String password) {
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        // Register user in the database
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        writeNewUser(firebaseUser.getUid(), username, email);
                    } else {
                        Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void writeNewUser(String userId, String username, String email) {
        User user = new User(username, email, false);
        databaseUsers.child(userId).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, Login.class));
                        finish();
                    } else {
                        Toast.makeText(Register.this, "Failed to register user in database.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public class User {
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

}
