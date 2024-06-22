package com.askyr.animalsdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalRowHolder> {

    ArrayList<Animal> animalData;
    Context context;
    MyClickInterface myClickInterface;

    public AnimalAdapter(ArrayList<Animal> animalData, Context context, MyClickInterface myClickInterface) {

        this.context = context;
        this.animalData = animalData;
        this.myClickInterface = myClickInterface;
    }

    // This method is called when a new Row-item is created in the recycler view
    @NonNull
    @Override
    public AnimalRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Creating a new animal_row view component by instantiating AnimalRowHolder class
        View view = LayoutInflater.from(context).inflate(R.layout.animal_row, parent, false);
        return new AnimalRowHolder(view);
    }

    /* This method is called whenever a row item which was out of focus comes back into focus,
    so binding of actual data to the respective view components takes place here.*/
    @Override
    public void onBindViewHolder(@NonNull AnimalRowHolder holder, int position) {

        holder.txtAnimalName.setText(animalData.get(position).getName());
        holder.imgAnimal.setImageResource(animalData.get(position).getImage());
    }

    // This method is called to get the number of items in the RecyclerView
    @Override
    public int getItemCount() {

        // Returning the size of the animalData array
        return animalData.size();
    }

    // Class to reference the animal_row view component so it can be used in the code
    class AnimalRowHolder extends RecyclerView.ViewHolder {

        TextView txtAnimalName;
        ImageView imgAnimal;

        // Constructor using the animal_row view component as View parameter
        public AnimalRowHolder(@NonNull View itemView) {
            super(itemView);

            // Referencing the view components through their id
            txtAnimalName = itemView.findViewById(R.id.txt_animal_name);
            imgAnimal = itemView.findViewById(R.id.img_animal);

            // setting onClickListener when animal_row component is clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    interface MyClickInterface {
        void onItemClick(int positionOfTheAnimal);
    }
}
