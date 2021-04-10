package com.askyr.chatapp;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    private ArrayList<User> users;
    private Context context;
    private OnUserClickListener oncUserClickListener;

    public UsersAdapter(ArrayList<User> users, Context context, OnUserClickListener oncUserClickListener) {
        this.users = users;
        this.context = context;
        this.oncUserClickListener = oncUserClickListener;
    }

    // for passing onClick events to Friends Activity
    interface OnUserClickListener {
        // to let friends activity know which user is clicked
        void onUserClicked(int position);
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_holder, parent, false);

        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.txtUsername.setText(users.get(position).getUserName());

        // using Glide we will display the profile image for users
        // specifying default image in glide when encountering an error in loading image or profile picture isn't available
        Glide.with(context).load(users.get(position).getProfilePicture()).error(R.drawable.account_img).placeholder(R.drawable.account_img).into(holder.imgUser);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        TextView txtUsername;
        ImageView imgUser;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oncUserClickListener.onUserClicked(getAdapterPosition());
                }
            });

            txtUsername = itemView.findViewById(R.id.txtUsername);
            imgUser = itemView.findViewById(R.id.imgUser);
        }
    }
}