package com.askyr.eatmore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView_Categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView_Categories = findViewById(R.id.recyclerViewCategories);

        setCategories();
    }

    private void setCategories() {
        List<FoodCategory> data = new ArrayList<>();

        FoodCategory foodCategory1 = new FoodCategory("Chicken", R.drawable.ic_chicken);
        FoodCategory foodCategory2 = new FoodCategory("Burger", R.drawable.ic_burger);
        FoodCategory foodCategory3 = new FoodCategory("Pizza", R.drawable.ic_pizza);
        FoodCategory foodCategory4 = new FoodCategory("Noodles", R.drawable.ic_noodles);
        FoodCategory foodCategory5 = new FoodCategory("Drinks", R.drawable.ic_drinks);

        data.add(foodCategory1);
        data.add(foodCategory2);
        data.add(foodCategory3);
        data.add(foodCategory4);
        data.add(foodCategory5);

        FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter(data, HomeActivity.this);

        recyclerView_Categories.setLayoutManager(new LinearLayoutManager( HomeActivity.this, RecyclerView.HORIZONTAL, false));
        recyclerView_Categories.setAdapter(foodCategoryAdapter);

        foodCategoryAdapter.notifyDataSetChanged();
    }
}