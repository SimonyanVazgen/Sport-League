package com.example.sport_league;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        holder.messageTextView.setText(message.getMessage());
        holder.senderIdTextView.setText(message.getSenderId());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView senderIdTextView;
        public TextView messageTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            senderIdTextView = itemView.findViewById(R.id.senderIdTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
        }
    }
}
