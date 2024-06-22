package com.askyr.eatmore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView_Categories, recyclerView_FoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView_Categories = findViewById(R.id.recyclerViewCategories);
        recyclerView_FoodItems = findViewById(R.id.recyclerViewFoodItems);

        setCategories();
        setFoodItems(0);
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

        FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter(data, HomeActivity.this, new FoodCategoryAdapter.OnCategoryClickedListener() {
            @Override
            public void onCategoryClick(int position) {
                // category change updated
                setFoodItems(position);
            }
        });

        recyclerView_Categories.setLayoutManager(new LinearLayoutManager( HomeActivity.this, RecyclerView.HORIZONTAL, false));
        recyclerView_Categories.setAdapter(foodCategoryAdapter);

        foodCategoryAdapter.notifyDataSetChanged();
    }

    private void setFoodItems(int categoryPosition) {

        List<FoodItem> data = new ArrayList<>();

        FoodItem foodItem1, foodItem2, foodItem3, foodItem4, foodItem5;

        switch (categoryPosition) {
            case 0 :
                foodItem1 = new FoodItem("Chicken-1", 3.5f, 200, R.drawable.grill_chicken_1);
                foodItem2 = new FoodItem("Chicken-2", 4.2f, 400, R.drawable.grill_chicken_2);
                foodItem3 = new FoodItem("Chicken-3", 5.0f, 600, R.drawable.grill_chicken_3);

                data.add(foodItem1);
                data.add(foodItem2);
                data.add(foodItem3);

                break;
            case 1 :
                foodItem1 = new FoodItem("Burger-1", 3.5f, 150, R.drawable.burger);
                foodItem2 = new FoodItem("Burger-2", 4.2f, 300, R.drawable.burger_two);

                data.add(foodItem1);
                data.add(foodItem2);

                break;
            case 2 :
                foodItem1 = new FoodItem("Pizza-1", 3.5f, 100, R.drawable.pizza_1);
                foodItem2 = new FoodItem("Pizza-2", 4.2f, 200, R.drawable.pizza_2);
                foodItem3 = new FoodItem("Pizza-3", 4.0f, 300, R.drawable.pizza_3);
                foodItem4 = new FoodItem("Pizza-4", 5.0f, 400, R.drawable.pizza_4);
                foodItem5 = new FoodItem("Pizza-5", 4.0f, 500, R.drawable.pizza_5);

                data.add(foodItem1);
                data.add(foodItem2);
                data.add(foodItem3);
                data.add(foodItem4);
                data.add(foodItem5);

                break;
            case 3 :
                foodItem1 = new FoodItem("Noodles-1", 3.5f, 150, R.drawable.pizza_1);
                foodItem2 = new FoodItem("Noodles-2", 4.2f, 250, R.drawable.pizza_2);
                foodItem3 = new FoodItem("Noodles-3", 4.0f, 350, R.drawable.pizza_3);
                foodItem4 = new FoodItem("Noodles-4", 5.0f, 450, R.drawable.pizza_4);
                foodItem5 = new FoodItem("Noodles-5", 4.0f, 550, R.drawable.pizza_5);

                data.add(foodItem1);
                data.add(foodItem2);
                data.add(foodItem3);
                data.add(foodItem4);
                data.add(foodItem5);

                break;
            case 4 :
                foodItem1 = new FoodItem("Drink-1", 3.5f, 50, R.drawable.pizza_1);
                foodItem2 = new FoodItem("Drink-2", 4.2f, 75, R.drawable.pizza_2);
                foodItem3 = new FoodItem("Drink-3", 4.0f, 100, R.drawable.pizza_3);
                foodItem4 = new FoodItem("Drink-4", 5.0f, 125, R.drawable.pizza_4);
                foodItem5 = new FoodItem("Drink-5", 4.0f, 150, R.drawable.pizza_5);

                data.add(foodItem1);
                data.add(foodItem2);
                data.add(foodItem3);
                data.add(foodItem4);
                data.add(foodItem5);

                break;
        }

        FoodItemAdapter foodItemAdapter = new FoodItemAdapter(data, HomeActivity.this);
        recyclerView_FoodItems.setLayoutManager(new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false));
        recyclerView_FoodItems.setAdapter(foodItemAdapter);

        foodItemAdapter.notifyDataSetChanged();
    }
}