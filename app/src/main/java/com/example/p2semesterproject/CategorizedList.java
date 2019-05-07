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

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CategorizedList extends AppCompatActivity {

    public static Resources res;

    public static Drawable lemonPic;

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

    private static FoodObject[][] foodData;


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
                    new FoodObject("Apple"),
                    new FoodObject("Banana"),
                    new FoodObject("Grapes",res.getDrawable(R.drawable.grape)),
                    new FoodObject("Kiwi",res.getDrawable(R.drawable.kiwi, getTheme())),
                    new FoodObject("Melon",res.getDrawable(R.drawable.melon, getTheme())),
                    new FoodObject("Orange",res.getDrawable(R.drawable.orange, getTheme())),
                    new FoodObject("Pear",res.getDrawable(R.drawable.pear, getTheme())),
                    new FoodObject("Raspberry",res.getDrawable(R.drawable.raspberry, getTheme())),
                    new FoodObject("Strawberry",res.getDrawable(R.drawable.strawberry, getTheme())),
                    new FoodObject("Watermelon",res.getDrawable(R.drawable.watermelon, getTheme())),
                    new FoodObject("Lemon",res.getDrawable(R.drawable.lemon, getTheme()))
                },
                //Dairy and Eggs
                {
                    new FoodObject("Butter",res.getDrawable(R.drawable.butter, getTheme())),
                    new FoodObject("Cheese",res.getDrawable(R.drawable.cheese, getTheme())),
                    new FoodObject("Eggs"),
                    new FoodObject("Milk"),
                    new FoodObject("Yogurt",res.getDrawable(R.drawable.yogurt, getTheme()))
                },
                //Baked goods
                {
                    new FoodObject("White Bread",res.getDrawable(R.drawable.bread, getTheme())),
                    new FoodObject("Rye Bread",res.getDrawable(R.drawable.ryebread,getTheme()))
                },
                //Meat
                {
                    new FoodObject("Beef",res.getDrawable(R.drawable.beef, getTheme())),
                    new FoodObject("Chicken",res.getDrawable(R.drawable.chicken, getTheme())),
                    new FoodObject("Fish",res.getDrawable(R.drawable.fish, getTheme())),
                    new FoodObject("Pork",res.getDrawable(R.drawable.pork, getTheme()))
                },
                //Vegetables
                {
                    new FoodObject("Bell Pepper",res.getDrawable(R.drawable.bellpep, getTheme())),
                    new FoodObject("Cabbage",res.getDrawable(R.drawable.cabbage, getTheme())),
                    new FoodObject("Carrot",res.getDrawable(R.drawable.carrot, getTheme())),
                    new FoodObject("Chili",res.getDrawable(R.drawable.chilli, getTheme())),
                    new FoodObject("Corn",res.getDrawable(R.drawable.corn, getTheme())),
                    new FoodObject("Cucumber",res.getDrawable(R.drawable.cucumb, getTheme())),
                    new FoodObject("Garlic",res.getDrawable(R.drawable.garlic, getTheme())),
                    new FoodObject("Onion",res.getDrawable(R.drawable.onion, getTheme())),
                    new FoodObject("Potato",res.getDrawable(R.drawable.potatoes, getTheme())),
                    new FoodObject("Tomato",res.getDrawable(R.drawable.tomato, getTheme())),
                    new FoodObject("Lettuce",res.getDrawable(R.drawable.lettuce, getTheme()))
                }};



        recyclerView=findViewById(R.id.foodList);
        lemonPic=res.getDrawable(R.drawable.lemon, getTheme());
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

    }



}
