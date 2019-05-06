package com.example.p2semesterproject;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CategorizedList extends AppCompatActivity {

    private String[][] foodListData={{"Apple","Banana","Grapes","Kiwi","Melon","Orange","Pear","Raspberry","Strawberry","Water melon"},
            {"Butter","Cheese","Eggs","Milk","Yogurt"},{"Rye bread","White bread"},{"Beef","Chicken","Fish","Pork"},{"Bell pepper","Cabbage","Corn","Cucumber","Garlic","Iceberg","Onion","Potato","Tomato"}};

    private Drawable[] foodImageList={{}}

    private static int categoryIndex;

    private RecyclerView recyclerView;
    private FoodListAdapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorized_list);
        recyclerView=findViewById(R.id.foodList);
        //recyclerView.setHasFixedSize(true); //for performance (I don't know what it does)
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter =new FoodListAdapter(foodListData[categoryIndex], getDrawable(R.drawable.cow));
        recyclerView.setAdapter(listAdapter);

    }

    public int getCategoryIndex() {   //Index of categories
        return categoryIndex;
    }

    public static void setCategoryIndex(int _categoryIndex) {
        categoryIndex = _categoryIndex;
    }
}
