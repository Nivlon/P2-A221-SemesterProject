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

    Resources res;

    private static int categoryIndex;

    private RecyclerView recyclerView;
    private FoodListAdapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FoodObject MakeFoodObject(String name,int ID) {
        return new FoodObject(name,res.getDrawable(ID, getTheme()));
    }

    /*private Drawable DCon(String imageName) {
        int resID = getResources().getIdentifier(imageName, "drawable", "package.name");
        return res.getDrawable(resID, getTheme());
    }*/

    public static void setCategoryIndex(int _categoryIndex) {
        categoryIndex = _categoryIndex;
    }

    private FoodObject[][] foodData;

    private String[][] foodListData={{"Apple","Banana","Grapes","Kiwi","Melon","Orange","Pear","Raspberry","Strawberry","Water melon"},
            {"Butter","Cheese","Eggs","Milk","Yogurt"},{"Rye bread","White bread"},{"Beef","Chicken","Fish","Pork"},{"Bell pepper","Cabbage","Corn","Cucumber","Garlic","Iceberg","Onion","Potato","Tomato"}};



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorized_list);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        res = getResources();
        foodData= new FoodObject[][]{
                //Fruit
                new FoodObject[]{
                    MakeFoodObject("Lemon",R.drawable.lemon),
                    MakeFoodObject("Kiwi",R.drawable.kiwi)},
                //Dairy and Eggs
                new FoodObject[]{
                        MakeFoodObject("Butter",R.drawable.butter)},
                //Baked goods
                new FoodObject[]{
                        MakeFoodObject("Cabbage",R.drawable.cabbage)},
                //Meat
                new FoodObject[]{
                        MakeFoodObject("Bread",R.drawable.bread)},
                //Vegetables
                new FoodObject[]{
                        MakeFoodObject("Beef",R.drawable.beef)}};
        recyclerView=findViewById(R.id.foodList);
        //recyclerView.setHasFixedSize(true); //for performance (I don't know what it does)
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter =new FoodListAdapter(foodData[categoryIndex]);
        recyclerView.setAdapter(listAdapter);

    }

}
