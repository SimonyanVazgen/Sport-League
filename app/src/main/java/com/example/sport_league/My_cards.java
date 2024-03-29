package com.example.sport_league;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class My_cards extends AppCompatActivity {

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cards);

        // Define your ImageViews and their corresponding image URLs
        ImageView imageView1 = findViewById(R.id.imageView);
        String img1 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/apedipele.png?alt=media&token=cba783b8-24bb-4aed-9023-d77e0cae0a00";
        Glide.with(this).load(img1).into(imageView1);

        ImageView imageView2 = findViewById(R.id.imageView2);
        String img2 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/haland.png?alt=media&token=db841016-0a48-4856-a753-f69b87b22ead";
        Glide.with(this).load(img2).into(imageView2);

        ImageView imageView3 = findViewById(R.id.imageView3);
        String img3 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/smolarek.png?alt=media&token=4086bb21-b17a-49dc-a6cf-ff7d2c28bebb";
        Glide.with(this).load(img3).into(imageView3);

        ImageView imageView4 = findViewById(R.id.imageView4);
        String img4 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sancho.png?alt=media&token=17818fe7-2f6c-4847-ad20-7e69ae2d7a63";
        Glide.with(this).load(img4).into(imageView4);

        ImageView imageView5 = findViewById(R.id.imageView5);
        String img5 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/cordoba.png?alt=media&token=5f77fa5e-8ded-4405-b4bc-54b6a997bafe";
        Glide.with(this).load(img5).into(imageView5);

        ImageView imageView6 = findViewById(R.id.imageView6);
        String img6 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/donarumma.png?alt=media&token=603b0df6-5884-42cd-9bef-e42e1c2d4553";
        Glide.with(this).load(img6).into(imageView6);


        


        // Set click listeners for each ImageView
        imageView1.setOnClickListener(v -> returnResult(img1));
        imageView2.setOnClickListener(v -> returnResult(img2));
        imageView3.setOnClickListener(v -> returnResult(img3));
        imageView4.setOnClickListener(v -> returnResult(img4));
        imageView5.setOnClickListener(v -> returnResult(img5));
        imageView6.setOnClickListener(v -> returnResult(img6));

    }

    private void returnResult(String imageUri) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("imageUri", imageUri);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
