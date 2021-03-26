package com.askyr.todolist;

import android.content.Context;
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

    public NoteAdapter(ArrayList<Note> arrayList, Context context){
        notes = arrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.note_holder, parent);// it will inflate the viewHolder from the context that we are getting from the main activity

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
        }
    }
}
