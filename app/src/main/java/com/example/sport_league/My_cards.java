package com.example.sport_league;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import java.util.HashSet;

public class My_cards extends AppCompatActivity {

    // Static variable to keep track of selected image URIs
    private String img2;
    private String img1;
    private String img3;
    private String img4;
    private String img5;
    private String img6;
    private String img7;
    private String img8;
    private String img9;
    private String img10;
    private String img11;
    private String img12;
    private String img13;
    private String img14;
    private String img15;
    private String img16;
    private String img17;
    private String img18;
    private String img19;


    public static HashSet<String> selectedImageUris = new HashSet<>();


    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cards);




        ImageView imageView1 = findViewById(R.id.imageView);
        img1 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/apedipele.png?alt=media&token=cba783b8-24bb-4aed-9023-d77e0cae0a00";
        Glide.with(this).load(img1).into(imageView1);

        ImageView imageView2 = findViewById(R.id.imageView2);
        img2 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/haland.png?alt=media&token=db841016-0a48-4856-a753-f69b87b22ead";
        Glide.with(this).load(img2).into(imageView2);

        ImageView imageView3 = findViewById(R.id.imageView3);
        img3 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/smolarek.png?alt=media&token=4086bb21-b17a-49dc-a6cf-ff7d2c28bebb";
        Glide.with(this).load(img3).into(imageView3);

        ImageView imageView4 = findViewById(R.id.imageView4);
        img4 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sancho.png?alt=media&token=17818fe7-2f6c-4847-ad20-7e69ae2d7a63";
        Glide.with(this).load(img4).into(imageView4);

        ImageView imageView5 = findViewById(R.id.imageView5);
        img5 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/cordoba.png?alt=media&token=5f77fa5e-8ded-4405-b4bc-54b6a997bafe";
        Glide.with(this).load(img5).into(imageView5);

        ImageView imageView6 = findViewById(R.id.imageView6);
        img6 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/donarumma.png?alt=media&token=603b0df6-5884-42cd-9bef-e42e1c2d4553";
        Glide.with(this).load(img6).into(imageView6);

        ImageView imageView7 = findViewById(R.id.imageView7);
        img7 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/dybala.png?alt=media&token=be64c1cf-df4c-48a8-8aa3-557e105c0ad3";
        Glide.with(this).load(img7).into(imageView7);

        ImageView imageView8 = findViewById(R.id.imageView8);
        img8 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/ferrari.png?alt=media&token=ac707f26-7ae9-4678-ae46-867c2b334462";
        Glide.with(this).load(img8).into(imageView8);

        ImageView imageView9 = findViewById(R.id.imageView9);
        img9 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/fred.png?alt=media&token=b4b98187-407f-4f90-86aa-02e4475a1d22";
        Glide.with(this).load(img9).into(imageView9);

        ImageView imageView10 = findViewById(R.id.imageView10);
        img10 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/gomez.png?alt=media&token=a8d31bfa-248d-464c-b62d-1bf6b4ba6fbf";
        Glide.with(this).load(img10).into(imageView10);


        ImageView imageView11 = findViewById(R.id.imageView11);
        img11 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/insigne.png?alt=media&token=778b7fd7-8aae-4c39-b651-d70c5ba82827";
        Glide.with(this).load(img11).into(imageView11);



        ImageView imageView12 = findViewById(R.id.imageView12);
        img12 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/jesus.png?alt=media&token=810a9ccc-9bf4-48ab-a4e7-e11d061c20c2";
        Glide.with(this).load(img12).into(imageView12);


        ImageView imageView13 = findViewById(R.id.imageView13);
        img13 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/karvajal.png?alt=media&token=18bee876-fd67-49a8-afa0-5662d657e1d6";
        Glide.with(this).load(img13).into(imageView13);


        ImageView imageView14 = findViewById(R.id.imageView14);
        img14 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/konate.png?alt=media&token=14362e64-01d5-401c-ad86-54a0f09024ce";
        Glide.with(this).load(img14).into(imageView14);





        ImageView imageView15 = findViewById(R.id.imageView15);
        img15 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/mkitaryan.png?alt=media&token=40f77487-d86f-407c-953b-73ac54066969";
        Glide.with(this).load(img15).into(imageView15);

        ImageView imageView16 = findViewById(R.id.imageView16);
        img16 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/mudryk.png?alt=media&token=6203ca9a-e8d4-484c-81c0-2a89f71b41a9";
        Glide.with(this).load(img16).into(imageView16);



        ImageView imageView17 = findViewById(R.id.imageView17);
        img17 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/muriel.png?alt=media&token=d1e30692-b168-47fb-9d2c-9bd70821d53e";
        Glide.with(this).load(img17).into(imageView17);



        ImageView imageView18 = findViewById(R.id.imageView18);
        img18 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/reus.png?alt=media&token=963c164b-5be0-44c8-9c7d-07c19261c7ce";
        Glide.with(this).load(img18).into(imageView18);



        ImageView imageView19 = findViewById(R.id.imageView19);
        img19 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/rubenneves.png?alt=media&token=d8de4942-5ed2-4c76-b729-257cd1f3f765";
        Glide.with(this).load(img19).into(imageView19);



        imageView1.setOnClickListener(v -> {String rating = "98"; returnResult(img1, imageView1, rating);});
        imageView2.setOnClickListener(v -> {String rating = "99";returnResult(img2, imageView2,rating);});
        imageView3.setOnClickListener(v -> {String rating = "96";returnResult(img3, imageView3,rating);});
        imageView4.setOnClickListener(v -> {String rating = "96";returnResult(img4, imageView4,rating);});
        imageView5.setOnClickListener(v -> {String rating = "97";returnResult(img5, imageView5,rating);});
        imageView6.setOnClickListener(v -> {String rating = "99";returnResult(img6, imageView6,rating);});
        imageView7.setOnClickListener(v -> {String rating = "98";returnResult(img7, imageView7,rating);});
        imageView8.setOnClickListener(v -> {String rating = "92";returnResult(img8, imageView8,rating);});
        imageView9.setOnClickListener(v -> {String rating = "96";returnResult(img9, imageView9,rating);});
        imageView10.setOnClickListener(v -> {String rating = "96";returnResult(img10, imageView10,rating);});
        imageView11.setOnClickListener(v -> {String rating = "95";returnResult(img11, imageView11,rating);});
        imageView12.setOnClickListener(v -> {String rating = "97";returnResult(img12, imageView12,rating);});
        imageView13.setOnClickListener(v -> {String rating = "96";returnResult(img13, imageView13,rating);});
        imageView14.setOnClickListener(v -> {String rating = "97";returnResult(img14, imageView14,rating);});
        imageView15.setOnClickListener(v -> {String rating = "96";returnResult(img15, imageView15,rating);});
        imageView16.setOnClickListener(v -> {String rating = "97";returnResult(img16, imageView16,rating);});
        imageView17.setOnClickListener(v -> {String rating = "95";returnResult(img17, imageView17,rating);});
        imageView18.setOnClickListener(v -> {String rating = "97";returnResult(img18, imageView18,rating);});
        imageView19.setOnClickListener(v -> {String rating = "95";returnResult(img19, imageView19,rating);});






        if (selectedImageUris.contains(img1)) {
            imageView1.setVisibility(View.GONE);
        }

        if (selectedImageUris.contains(img2)) {
            imageView2.setVisibility(View.GONE);
        }

        if (selectedImageUris.contains(img3)) {
            imageView3.setVisibility(View.GONE);
        }

        if (selectedImageUris.contains(img4)) {
            imageView4.setVisibility(View.GONE);
        }



        if (selectedImageUris.contains(img5)) {
            imageView5.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img6)) {
            imageView6.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img7)) {
            imageView7.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img8)) {
            imageView8.setVisibility(View.GONE);
        }



        if (selectedImageUris.contains(img9)) {
            imageView9.setVisibility(View.GONE);
        }




        if (selectedImageUris.contains(img10)) {
            imageView10.setVisibility(View.GONE);
        }



        if (selectedImageUris.contains(img11)) {
            imageView11.setVisibility(View.GONE);
        }



        if (selectedImageUris.contains(img12)) {
            imageView12.setVisibility(View.GONE);
        }



        if (selectedImageUris.contains(img13)) {
            imageView13.setVisibility(View.GONE);
        }



        if (selectedImageUris.contains(img14)) {
            imageView14.setVisibility(View.GONE);
        }



        if (selectedImageUris.contains(img15)) {
            imageView15.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img16)) {
            imageView16.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img17)) {
            imageView17.setVisibility(View.GONE);
        }



        if (selectedImageUris.contains(img18)) {
            imageView18.setVisibility(View.GONE);
        }


        if (selectedImageUris.contains(img19)) {
            imageView19.setVisibility(View.GONE);
        }



    }


    private void returnResult(String imageUri, ImageView imageView, String rating) {
        Log.d("My_cards", "Returning result: " + imageUri + " with rating: " + rating);
        selectedImageUris.add(imageUri); // Track the selected URIs
        Intent resultIntent = new Intent();
        resultIntent.putExtra("imageUri", imageUri);
        resultIntent.putExtra("cardRating", rating); // Include the rating in the result
        setResult(RESULT_OK, resultIntent);
        imageView.setVisibility(View.GONE); // Hide the ImageView
        finish();
    }



}
