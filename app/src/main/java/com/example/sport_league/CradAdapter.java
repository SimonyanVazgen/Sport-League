package com.example.sport_league;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CradAdapter extends RecyclerView.Adapter<CradAdapter.MyHolder> {

    Context context;
    ArrayList<Model>arrayList;
    LayoutInflater layoutInflater;

    public   CradAdapter(Context context,ArrayList<Model> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public CradAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_file,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CradAdapter.MyHolder holder, int position) {
        holder.cardName.setText(arrayList.get(position).getCardName());
        holder.carNum.setText(arrayList.get(position).getCardNum());
        holder.img.setImageResource(arrayList.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView  cardName,carNum;
        ImageView img;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cardName = itemView.findViewById(R.id.text);
            carNum = itemView.findViewById(R.id.text2);
            img = itemView.findViewById(R.id.imageView10);
        }


    }
}
