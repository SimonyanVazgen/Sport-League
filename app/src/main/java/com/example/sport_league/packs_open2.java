package com.example.sport_league;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class packs_open2 extends AppCompatActivity {
    private List<String> imageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packs_open2);

        // Initialize Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get ImageViews
        ImageView imageView1 = findViewById(R.id.randomImageView5);
        ImageView imageView2 = findViewById(R.id.randomImageView6);
        ImageView imageView3 = findViewById(R.id.randomImageView7);
        ImageView imageView4 = findViewById(R.id.randomImageView8);

        // Fetch image URLs from Firestore
        db.collection("images").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    imageUrls.add(document.getString("imageUrl"));  // assuming the field containing the URL is named "imageUrl"
                }

                // Display four random images each time the activity is created
                displayRandomImages(new ImageView[]{imageView1, imageView2, imageView3, imageView4});
            } else {
                System.out.println("Error getting documents: " + task.getException());
            }
        });
    }

    private void displayRandomImages(ImageView[] imageViews) {
        if (imageUrls.size() < 4) {
            System.out.println("Not enough images available");
            return;
        }

        Collections.shuffle(imageUrls);  // Shuffle the list of image URLs
        for (int i = 0; i < 4; i++) {
            Glide.with(this)
                    .load(imageUrls.get(i))
                    .into(imageViews[i]);
        }
    }
}
