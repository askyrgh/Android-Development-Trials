package com.askyr.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private ArrayList<Message> messages;
    private String senderImg, receiverImg;
    private Context context;

    public MessageAdapter(ArrayList<Message> messages, String senderImg, String receiverImg, Context context) {
        this.messages = messages;
        this.senderImg = senderImg;
        this.receiverImg = receiverImg;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_holder, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.txtMessage.setText(messages.get(position).getContent());

        // using constraint layout to manipulate constraints through Java
        ConstraintLayout constraintLayout = holder.ccll;

        if(messages.get(position).getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
            // we are sender of the message so we put constraints to bind everything to right.

            // using glide to display images for users for each cases i.e. successful load, error, and default placeholder
            Glide.with(context)
                    .load(senderImg)
                    .error(R.drawable.account_img)
                    .placeholder(R.drawable.account_img)
                    .into(holder.profileImage);

            ConstraintSet constraintSet = new ConstraintSet();

            // creating a copy of constraint layout
            constraintSet.clone(constraintLayout);

            // clearing the left binding constraints
            constraintSet.clear(R.id.profileCardView, ConstraintSet.LEFT);
            constraintSet.clear(R.id.txtMessageContent, ConstraintSet.LEFT);

            // setting new constraints for binding to right
            constraintSet.connect(R.id.profileCardView, ConstraintSet.RIGHT, R.id.ccLayout, ConstraintSet.RIGHT, 0);
            constraintSet.connect(R.id.txtMessageContent, ConstraintSet.RIGHT, R.id.profileCardView, ConstraintSet.LEFT, 0);

            // applying the changes
            constraintSet.applyTo(constraintLayout);
        }
        else {
            // we are not sender of the message so bind everything to the left

            // using glide to display images for users for each cases i.e. successful load, error, and default placeholder
            Glide.with(context)
                    .load(receiverImg)
                    .error(R.drawable.account_img)
                    .placeholder(R.drawable.account_img)
                    .into(holder.profileImage);

            ConstraintSet constraintSet = new ConstraintSet();

            // creating a copy of constraint layout
            constraintSet.clone(constraintLayout);

            // clearing the right binding constraints
            constraintSet.clear(R.id.profileCardView, ConstraintSet.RIGHT);
            constraintSet.clear(R.id.txtMessageContent, ConstraintSet.RIGHT);

            // setting new constraints for binding to right
            constraintSet.connect(R.id.profileCardView, ConstraintSet.LEFT, R.id.ccLayout, ConstraintSet.LEFT, 0);
            constraintSet.connect(R.id.txtMessageContent, ConstraintSet.LEFT, R.id.profileCardView, ConstraintSet.RIGHT, 0);

            // applying the changes
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder {

        ConstraintLayout ccll;
        TextView txtMessage;
        ImageView profileImage;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);

            ccll = itemView.findViewById(R.id.ccLayout);
            txtMessage = itemView.findViewById(R.id.txtMessageContent);
            profileImage = itemView.findViewById(R.id.smallProfileImage);

        }
    }
}
