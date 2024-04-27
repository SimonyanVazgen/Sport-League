package com.example.sport_league;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class SecondFragment extends Fragment {

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
    private TextView ratingTextView;
    private Button viewTeamButton;
    private HashMap<Integer, String> ratings;
    private HashMap<Integer, String> imageUris;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ratingTextView = view.findViewById(R.id.ratingTextView);

        


        ImageView teamMember1 = view.findViewById(R.id.imageViewTeam1);
        img1 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/apedipele.png?alt=media&token=cba783b8-24bb-4aed-9023-d77e0cae0a00";
        Glide.with(this).load(img1).into(teamMember1);
        teamMember1.setOnClickListener(v -> showRating("98"));

        ImageView teamMember2 = view.findViewById(R.id.imageViewTeam2);
        img2 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/haland.png?alt=media&token=db841016-0a48-4856-a753-f69b87b22ead";
        Glide.with(this).load(img2).into(teamMember2);
        teamMember2.setOnClickListener(v -> showRating("99"));



        ImageView teamMember3 = view.findViewById(R.id.imageViewTeam3);
        img3 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sancho.png?alt=media&token=17818fe7-2f6c-4847-ad20-7e69ae2d7a63";
        Glide.with(this).load(img3).into(teamMember3);
        teamMember3.setOnClickListener(v -> showRating("96"));

        ImageView teamMember4 = view.findViewById(R.id.imageViewTeam4);
        img4 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/sancho.png?alt=media&token=17818fe7-2f6c-4847-ad20-7e69ae2d7a63";
        Glide.with(this).load(img4).into(teamMember4);
        teamMember4.setOnClickListener(v -> showRating("96"));

        ImageView teamMember5 = view.findViewById(R.id.imageViewTeam5);
        img5 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/cordoba.png?alt=media&token=5f77fa5e-8ded-4405-b4bc-54b6a997bafe";
        Glide.with(this).load(img5).into(teamMember5);
        teamMember5.setOnClickListener(v -> showRating("97"));

        ImageView teamMember6 = view.findViewById(R.id.imageViewTeam6);
        img6 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/donarumma.png?alt=media&token=603b0df6-5884-42cd-9bef-e42e1c2d4553";
        Glide.with(this).load(img6).into(teamMember6);
        teamMember6.setOnClickListener(v -> showRating("99"));

        ImageView teamMember7 = view.findViewById(R.id.imageViewTeam7);
        img7 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/dybala.png?alt=media&token=be64c1cf-df4c-48a8-8aa3-557e105c0ad3";
        Glide.with(this).load(img7).into(teamMember7);
        teamMember7.setOnClickListener(v -> showRating("98"));

        ImageView teamMember8 = view.findViewById(R.id.imageViewTeam8);
        img8 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/ferrari.png?alt=media&token=ac707f26-7ae9-4678-ae46-867c2b334462";
        Glide.with(this).load(img8).into(teamMember8);
        teamMember8.setOnClickListener(v -> showRating("92"));

        ImageView teamMember9 = view.findViewById(R.id.imageViewTeam9);
        img9 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/fred.png?alt=media&token=b4b98187-407f-4f90-86aa-02e4475a1d22";
        Glide.with(this).load(img9).into(teamMember9);
        teamMember9.setOnClickListener(v -> showRating("96"));

        ImageView teamMember10 = view.findViewById(R.id.imageViewTeam10);
        img10 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/gomez.png?alt=media&token=a8d31bfa-248d-464c-b62d-1bf6b4ba6fbf";
        Glide.with(this).load(img10).into(teamMember10);
        teamMember10.setOnClickListener(v -> showRating("96"));

        ImageView teamMember11 = view.findViewById(R.id.imageViewTeam11);
        img11 = "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/insigne.png?alt=media&token=778b7fd7-8aae-4c39-b651-d70c5ba82827";
        Glide.with(this).load(img11).into(teamMember11);
        teamMember11.setOnClickListener(v -> showRating("95"));




        return view;
    }

    private void showRating(String rating) {
        // Update the TextView with the rating
        ratingTextView.setText("Rating: " + rating);
    }
}
