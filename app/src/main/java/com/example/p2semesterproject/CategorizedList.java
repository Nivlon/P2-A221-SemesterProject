package com.example.p2semesterproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CategorizedList extends AppCompatActivity {

    public static Resources res;

    public static Drawable lemonPic;
    public static Drawable counterPic;

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private static int categoryIndex;

    private RecyclerView recyclerView;
    private FoodListAdapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static void setCategoryIndex(int _categoryIndex) {
        categoryIndex = _categoryIndex;
    }

    public static FoodObject[][] foodData;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorized_list);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        res = getResources();
        foodData=new FoodObject[][]{
                //Fruit
                {
                    new FoodObject("Apple",res.getDrawable(R.drawable.apple),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Banana",res.getDrawable(R.drawable.banana),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Grapes",res.getDrawable(R.drawable.grape),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Kiwi",res.getDrawable(R.drawable.kiwi),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Melon",res.getDrawable(R.drawable.melon),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Orange",res.getDrawable(R.drawable.orange),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Pear",res.getDrawable(R.drawable.pear),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Raspberry",res.getDrawable(R.drawable.raspberry),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Strawberry",res.getDrawable(R.drawable.strawberry),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Watermelon",res.getDrawable(R.drawable.watermelon),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Lemon",res.getDrawable(R.drawable.lemon),res.getDrawable(R.drawable.counter)),
                },
                //Dairy and Eggs
                {
                    new FoodObject("Butter",res.getDrawable(R.drawable.butter),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Cheese",res.getDrawable(R.drawable.cheese),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Eggs",res.getDrawable(R.drawable.milknegg),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Milk",res.getDrawable(R.drawable.milknegg),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Yogurt",res.getDrawable(R.drawable.yogurt),res.getDrawable(R.drawable.fridge1)),
                },
                //Baked goods
                {
                    new FoodObject("White Bread",res.getDrawable(R.drawable.bread),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Rye Bread",res.getDrawable(R.drawable.ryebread),res.getDrawable(R.drawable.counter)),
                },
                //Meat
                {
                    new FoodObject("Beef",res.getDrawable(R.drawable.beef),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Chicken",res.getDrawable(R.drawable.chicken),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Fish",res.getDrawable(R.drawable.fish),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Pork",res.getDrawable(R.drawable.pork),res.getDrawable(R.drawable.fridge1))
                },
                //Vegetables
                {
                    new FoodObject("Bell Pepper",res.getDrawable(R.drawable.bellpep),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Cabbage",res.getDrawable(R.drawable.cabbage),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Carrot",res.getDrawable(R.drawable.carrot),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Chili",res.getDrawable(R.drawable.chilli),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Corn",res.getDrawable(R.drawable.corn),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Cucumber",res.getDrawable(R.drawable.cucumb),res.getDrawable(R.drawable.fridge1)),
                    new FoodObject("Garlic",res.getDrawable(R.drawable.garlic),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Onion",res.getDrawable(R.drawable.onion),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Potato",res.getDrawable(R.drawable.potatoes),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Tomato",res.getDrawable(R.drawable.tomato),res.getDrawable(R.drawable.counter)),
                    new FoodObject("Lettuce",res.getDrawable(R.drawable.lettuce),res.getDrawable(R.drawable.fridge1))
                }};



        recyclerView=findViewById(R.id.foodList);
        lemonPic=res.getDrawable(R.drawable.lemon);
        counterPic=res.getDrawable(R.drawable.counter);
        recyclerView.setHasFixedSize(true); //for performance (I don't know what it does)
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter =new FoodListAdapter(foodData[categoryIndex]);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getApplicationContext(),InfoScreen.class));
                InfoScreen.setFoodItem(foodData[categoryIndex][position]);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        MainActivity.quizGenerator(foodData[0][5]);
    }

}
