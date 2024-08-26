package com.example.chatsage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ChatModal> chatModalArrayList;
    private Context context;

    public ChatAdapter(ArrayList<ChatModal> chatModalArrayList, Context context) {
        this.chatModalArrayList = chatModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_item, parent, false);
                return new UserViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg_item, parent, false);
                return new BotViewHolder(view);
        }
        throw new IllegalArgumentException("Invalid view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatModal chatModal = chatModalArrayList.get(position);
        if (holder instanceof UserViewHolder && chatModal.getSender().equals("user")) {
            ((UserViewHolder) holder).usertv.setText(chatModal.getMessage());
        } else if (holder instanceof BotViewHolder && chatModal.getSender().equals("bot")) {
            ((BotViewHolder) holder).botmsgtv.setText(chatModal.getMessage());
        }
    }


    @Override
    public int getItemCount() {
        return chatModalArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatModal chatModal = chatModalArrayList.get(position);
        return chatModal.getSender().equals("user") ? 0 : 1;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usertv;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usertv = itemView.findViewById(R.id.idtvuser);
        }
    }

    public static class BotViewHolder extends RecyclerView.ViewHolder {
        TextView botmsgtv;

        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botmsgtv = itemView.findViewById(R.id.idtvbot);
        }
    }
}
