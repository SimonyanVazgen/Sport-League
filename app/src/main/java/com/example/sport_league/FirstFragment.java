package com.example.sport_league;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstFragment extends Fragment {

    private static final int IMAGE_SELECTION_REQUEST = 1;
    private Button[] buttons = new Button[11];
    private int selectedButtonId;
    private int cardsSelected = 0;
    private TextView ratingTextView;
    private HashMap<Integer, String> cardRatingsMap = new HashMap<>();




    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        ratingTextView = view.findViewById(R.id.ratingTextView);


        int[] buttonIds = {
                R.id.button, R.id.button2, R.id.button123, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11
        };

        for (int i = 0; i < buttonIds.length; i++) {
            buttons[i] = view.findViewById(buttonIds[i]);
            if (buttons[i] == null) {
                Log.e("FirstFragment", "Button with ID index " + i + " (" + getResources().getResourceEntryName(buttonIds[i]) + ") is null.");
            } else {
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedButtonId = v.getId();
                        if (cardsSelected < 11) {
                            selectCard();
                        } else {
                            showRating(selectedButtonId);
                        }
                    }
                });
            }
        }

        return view;
    }

    private void selectCard() {
        if (cardsSelected < 11) {
            Intent intent = new Intent(getActivity(), My_cards.class);
            startActivityForResult(intent, IMAGE_SELECTION_REQUEST);
        } else {
            Toast.makeText(getActivity(), "Maximum of 11 cards are already selected.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_SELECTION_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            String imageUri = data.getStringExtra("imageUri");
            String cardRating = data.getStringExtra("cardRating");
            Button buttonToUpdate = getView().findViewById(selectedButtonId);
            cardRatingsMap.put(selectedButtonId, cardRating);
            cardsSelected++;
            Glide.with(this).load(imageUri).into(new CustomTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    buttonToUpdate.setBackground(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                }
            });
            if (cardsSelected >= 11) {
                disableCardSelection();
            }
        }
    }

    private void disableCardSelection() {
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showRating(v.getId());
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    private void showRating(int buttonId) {
        if (cardRatingsMap.containsKey(buttonId)) {
            String rating = cardRatingsMap.get(buttonId);
            ratingTextView.setText("Rating: " + rating);
            ratingTextView.setVisibility(View.VISIBLE);
        }
    }
}
