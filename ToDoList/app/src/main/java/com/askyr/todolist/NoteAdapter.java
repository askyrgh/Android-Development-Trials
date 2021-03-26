package com.askyr.todolist;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    // This class will be providing notes on screen inside the RecyclerView
    // Using NoteHolder as a type for RecyclerView.Adapter

    ArrayList<Note> notes;
    Context context; // for inflating the view-holder
    ItemClicked itemClicked;
    ViewGroup parent;
    public NoteAdapter(ArrayList<Note> arrayList, Context context, ItemClicked itemClicked){
        notes = arrayList;
        this.context = context;
        this.itemClicked = itemClicked; // itemClicked lets us communicate with adapter from main activity
    }
    
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // it will inflate the viewHolder from the context that we are getting from the main activity
        View view = LayoutInflater.from(context).inflate(R.layout.note_holder, parent, false);
        this.parent = parent;

        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.description.setText(notes.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        // This class is used to display data inside the note_holder layout

        TextView title;
        TextView description;
        ImageView imgEdit;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txt_note_name);
            description = itemView.findViewById(R.id.txt_note_description);
            imgEdit = itemView.findViewById(R.id.img_edit);

            // To expand each Note for elaborated display
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(description.getMaxLines() == 1) {
                        description.setMaxLines(Integer.MAX_VALUE);
                    }
                    else {
                        description.setMaxLines(1);
                    }
                    TransitionManager.beginDelayedTransition(parent);
                }
            });

            // To trigger the animation on clicking the Note
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // The whole view is passed instead of just the icon because the whole view needs to be animated
                    itemClicked.onClick(getAdapterPosition(), itemView);
                }
            });
        }
    }

    // Interface to execute animation
    interface ItemClicked {
        void onClick(int position, View view);
    }
}
