package com.askyr.eatmore;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.CategoryHolder>{

    List<FoodCategory> data;                //Holds the content of the Recycler View
    Context context;
    int selectedItemPos = 0;

    public FoodCategoryAdapter(List<FoodCategory> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.category_view_holder, parent, false);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.img_CategoryIcon.setImageResource(data.get(position).getImage());
        holder.txt_CategoryTitle.setText(data.get(position).getName());

        if(position == selectedItemPos) {
            holder.txt_CategoryTitle.setTextColor(context.getColor(R.color.red));
            holder.img_CategoryIcon.setColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.SRC_IN);
            holder.card_Category.setOutlineAmbientShadowColor(context.getColor(R.color.red));
            holder.card_Category.setOutlineSpotShadowColor(context.getColor(R.color.red));
            holder.card_Category.setStrokeWidth(2);
        }
        else {
            holder.card_Category.setOutlineSpotShadowColor(context.getColor(R.color.grey_variant_1));
            holder.card_Category.setOutlineAmbientShadowColor(context.getColor(R.color.grey_variant_1));
            holder.card_Category.setStrokeWidth(0);
            holder.img_CategoryIcon.setColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.SRC_IN);
            holder.txt_CategoryTitle.setTextColor(context.getColor(R.color.grey_variant_1));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        TextView txt_CategoryTitle;
        ImageView img_CategoryIcon;
        MaterialCardView card_Category;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            txt_CategoryTitle = itemView.findViewById(R.id.txtCategoryTitle);
            img_CategoryIcon = itemView.findViewById(R.id.imgCategoryIcon);
            card_Category = itemView.findViewById(R.id.cardCategory);

            card_Category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItemPos = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
