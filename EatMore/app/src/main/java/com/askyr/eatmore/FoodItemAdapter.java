package com.askyr.eatmore;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemHolder> {

    List<FoodItem> data;
    Context context;
    int selectedFoodItemPos = 0;

    public FoodItemAdapter(List<FoodItem> data, Context context) {
        
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.food_view_holder, parent, false);

        return new FoodItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemHolder holder, int position) {

        holder.txt_FoodItemName.setText(data.get(position).getName());
        holder.txt_FoodItemPrice.setText(String.format("₹%d", data.get(position).getPrice()));
        holder.img_FoodItemThumbnail.setImageResource(data.get(position).getImage());
        holder.rtb_FoodItemRating.setRating(data.get(position).getRating());

        if(selectedFoodItemPos == position) {
            
            holder.txt_FoodItemName.setTextColor(Color.WHITE);
            holder.txt_FoodItemPrice.setTextColor(Color.WHITE);

            holder.card_FoodItem.animate().scaleX(1.1f);
            holder.card_FoodItem.animate().scaleY(1.1f);

            holder.linearLayout.setBackgroundResource(R.drawable.launcher_image);
        }
        else {
            
            holder.txt_FoodItemName.setTextColor(Color.BLACK);
            holder.txt_FoodItemPrice.setTextColor(Color.BLACK);

            holder.card_FoodItem.animate().scaleX(1f);
            holder.card_FoodItem.animate().scaleY(1f);

            holder.linearLayout.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FoodItemHolder extends RecyclerView.ViewHolder {

        TextView txt_FoodItemName, txt_FoodItemPrice;
        RatingBar rtb_FoodItemRating;
        ImageView img_FoodItemThumbnail;

        CardView card_FoodItem;
        LinearLayout linearLayout;

        public FoodItemHolder(@NonNull View itemView) {

            super(itemView);

            txt_FoodItemName = itemView.findViewById(R.id.txtFoodItemName);
            txt_FoodItemPrice = itemView.findViewById(R.id.txtFoodItemPrice);
            rtb_FoodItemRating = itemView.findViewById(R.id.rtbFoodItemRating);
            img_FoodItemThumbnail = itemView.findViewById(R.id.imgFoodItemThumbnail);

            card_FoodItem = itemView.findViewById(R.id.cardFoodItem);
            linearLayout = itemView.findViewById(R.id.llBackground);

            card_FoodItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedFoodItemPos = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
