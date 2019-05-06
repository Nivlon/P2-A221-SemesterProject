package com.example.p2semesterproject;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CategorizedList extends AppCompatActivity {

    public static Resources res;

    public static Drawable lemonPic;

    private static int categoryIndex;

    private RecyclerView recyclerView;
    private FoodListAdapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FoodObject MakeFoodObject(String name) {
        return MakeFoodObject(name);
    }

    private FoodObject MakeFoodObject(String name, int ID) {
        return new FoodObject(name,res.getDrawable(ID, getTheme()));
    }

    public static void setCategoryIndex(int _categoryIndex) {
        categoryIndex = _categoryIndex;
    }

    private static FoodObject[][] foodData;

    private String[][] foodListData={{"Apple","Banana","Grapes","Kiwi","Melon","Orange","Pear","Raspberry","Strawberry","Water melon"},
            {"Butter","Cheese","Eggs","Milk","Yogurt"},
            {"Rye bread","White bread"},
            {"Beef","Chicken","Fish","Pork"},
            {"Bell pepper","Cabbage","Corn","Cucumber","Garlic","Iceberg","Onion","Potato","Tomato"}};



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorized_list);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        res = getResources();
        foodData[0]=
                //Fruit
                new FoodObject[]{
                    MakeFoodObject("Apple"),
                    MakeFoodObject("Banana"),
                    MakeFoodObject("Grapes",R.drawable.grape),
                    MakeFoodObject("Kiwi",R.drawable.kiwi),
                    MakeFoodObject("Melon",R.drawable.melon),
                    MakeFoodObject("Orange"),
                    MakeFoodObject("Pear"),
                    MakeFoodObject("Raspberry"),
                    MakeFoodObject("Strawberry"),
                    MakeFoodObject("Watermelon"),
                    MakeFoodObject("Lemon",R.drawable.lemon)
                };
        foodData[1]=
                //Dairy and Eggs
                new FoodObject[]{
                    MakeFoodObject("Butter",R.drawable.butter),
                    MakeFoodObject("Cheese",R.drawable.cheese),
                    MakeFoodObject("Eggs"),
                    MakeFoodObject("Milk"),
                    MakeFoodObject("Yogurt")
                };
        foodData[2]=
                //Baked goods
                new FoodObject[]{
                    MakeFoodObject("White Bread",R.drawable.bread),
                    MakeFoodObject("Rye Bread")
                };
        foodData[3]=
                //Meat
                new FoodObject[]{
                    MakeFoodObject("Beef",R.drawable.beef),
                    MakeFoodObject("Chicken",R.drawable.chicken),
                    MakeFoodObject("Fish",R.drawable.fish),
                    MakeFoodObject("Pork")
                };
        foodData[4]=
                //Vegetables
                new FoodObject[]{
                    MakeFoodObject("Bell Pepper"),
                    MakeFoodObject("Cabbage",R.drawable.cabbage),
                    MakeFoodObject("Carrot",R.drawable.carrot),
                    MakeFoodObject("Chili",R.drawable.chilli),
                    MakeFoodObject("Corn",R.drawable.corn),
                    MakeFoodObject("Cucumber"),
                    MakeFoodObject("Garlic",R.drawable.garlic),
                    MakeFoodObject("Iceberg"),
                    MakeFoodObject("Onion"),
                    MakeFoodObject("Potato"),
                    MakeFoodObject("Tomato"),
                    MakeFoodObject("Lettuce",R.drawable.lettuce)
                };
        recyclerView=findViewById(R.id.foodList);
        lemonPic=res.getDrawable(R.drawable.lemon, getTheme());
        recyclerView.setHasFixedSize(true); //for performance (I don't know what it does)
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter =new FoodListAdapter(foodData[categoryIndex]);
        recyclerView.setAdapter(listAdapter);

    }

}
