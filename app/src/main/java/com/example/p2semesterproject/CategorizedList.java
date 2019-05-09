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

    private static int categoryIndex;

    private RecyclerView recyclerView;
    private FoodListAdapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static FoodObject[][] foodData;
    public static boolean foodDataCreated=false;

    public static boolean isPinPress;
    public static int pinYPosition=800;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorized_list);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        res = getResources();
        FoodObject.setStorageSpaceIcons(new Drawable[]{res.getDrawable(R.drawable.fridge1),res.getDrawable(R.drawable.iceicebaby),res.getDrawable(R.drawable.counter)});
        FoodObject.setStorageInfoScreenIcons(new Drawable[]{res.getDrawable(R.drawable.open_fridge),res.getDrawable(R.drawable.iceicebaby),res.getDrawable(R.drawable.counter)});
        FoodObject.setPinsIcons(new Drawable[]{res.getDrawable(R.drawable.pinned),res.getDrawable(R.drawable.unpinned)});
        if(!foodDataCreated) {
            foodData = new FoodObject[][]{
                    //Fruit
                    {
                            new FoodObject("Apple", res.getDrawable(R.drawable.apple), "Counter","4-5","Its an apple"),
                            new FoodObject("Banana", res.getDrawable(R.drawable.banana), "Counter"),
                            new FoodObject("Grapes", res.getDrawable(R.drawable.grape), "Fridge","7","","Crisper"),
                            new FoodObject("Kiwi", res.getDrawable(R.drawable.kiwi), "Counter", "until ripe","when ripe: in fridge(crisper) for 3-4 days. "),
                            new FoodObject("Melon", res.getDrawable(R.drawable.melon), "Counter"),
                            new FoodObject("Orange", res.getDrawable(R.drawable.orange), "Counter"),
                            new FoodObject("Pear", res.getDrawable(R.drawable.pear), "Counter"),
                            new FoodObject("Raspberry", res.getDrawable(R.drawable.raspberry), "Fridge"),
                            new FoodObject("Strawberry", res.getDrawable(R.drawable.strawberry), "Fridge"),
                            new FoodObject("Watermelon", res.getDrawable(R.drawable.watermelon), "Counter"),
                            new FoodObject("Lemon", res.getDrawable(R.drawable.lemon), "Counter"),
                    },
                    //Dairy and Eggs
                    {
                            new FoodObject("Butter", res.getDrawable(R.drawable.butter), "Fridge"),
                            new FoodObject("Cheese", res.getDrawable(R.drawable.cheese), "Fridge"),
                            new FoodObject("Eggs", res.getDrawable(R.drawable.egg), "Fridge"),
                            new FoodObject("Milk", res.getDrawable(R.drawable.milk), "Fridge"),
                            new FoodObject("Yogurt", res.getDrawable(R.drawable.yogurt), "Fridge"),
                    },
                    //Baked goods
                    {
                            new FoodObject("White Bread", res.getDrawable(R.drawable.bread), "Counter"),
                            new FoodObject("Rye Bread", res.getDrawable(R.drawable.ryebread), "Counter"),
                    },
                    //Meat
                    {
                            new FoodObject("Beef", res.getDrawable(R.drawable.beef), "Fridge"),
                            new FoodObject("Chicken", res.getDrawable(R.drawable.chicken), "Fridge"),
                            new FoodObject("Fish", res.getDrawable(R.drawable.fish), "Fridge"),
                            new FoodObject("Pork", res.getDrawable(R.drawable.pork), "Fridge")
                    },
                    //Vegetables
                    {
                            new FoodObject("Bell Pepper", res.getDrawable(R.drawable.bellpep), "Fridge"),
                            new FoodObject("Cabbage", res.getDrawable(R.drawable.cabbage), "Fridge"),
                            new FoodObject("Carrot", res.getDrawable(R.drawable.carrot), "Fridge"),
                            new FoodObject("Chili", res.getDrawable(R.drawable.chilli), "Fridge"),
                            new FoodObject("Corn", res.getDrawable(R.drawable.corn), "Fridge"),
                            new FoodObject("Cucumber", res.getDrawable(R.drawable.cucumb), "Fridge"),
                            new FoodObject("Garlic", res.getDrawable(R.drawable.garlic), "Counter"),
                            new FoodObject("Onion", res.getDrawable(R.drawable.onion), "Counter"),
                            new FoodObject("Potato", res.getDrawable(R.drawable.potatoes), "Counter"),
                            new FoodObject("Tomato", res.getDrawable(R.drawable.tomato), "Counter"),
                            new FoodObject("Lettuce", res.getDrawable(R.drawable.lettuce), "Fridge")
                    }};
            foodDataCreated=true;
        }

        recyclerView=findViewById(R.id.foodList);
        recyclerView.setHasFixedSize(true); //for performance (I don't know what it does)
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter =new FoodListAdapter(foodData[categoryIndex]);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                if(isPinPress) {
                    foodData[categoryIndex][position].Pin();
                    orderPinnedList();
                } else {
                    startActivity(new Intent(getApplicationContext(), InfoScreen.class));
                    InfoScreen.setFoodItem(foodData[categoryIndex][position]);
                }
                listAdapter =new FoodListAdapter(foodData[categoryIndex]);
                recyclerView.setAdapter(listAdapter);

            }
        }));

    }

    public static void setCategoryIndex(int _categoryIndex) {
        categoryIndex = _categoryIndex;
    }

    public static int getCategoryIndex() {
        return categoryIndex;
    }

    public static void orderPinnedList() {
        FoodObject[] tempFoodData=new FoodObject[foodData[categoryIndex].length];
        int tempIndex=0;
        for(int i=0;i<foodData[categoryIndex].length;i++) {
            if(foodData[categoryIndex][i].isPinned()) {
                tempFoodData[tempIndex]=foodData[categoryIndex][i];
                tempIndex++;
            }
        }
        for(int i=0;i<foodData[categoryIndex].length;i++) {
            if(!foodData[categoryIndex][i].isPinned()) {
                tempFoodData[tempIndex]=foodData[categoryIndex][i];
                tempIndex++;
            }
        }
        foodData[categoryIndex]=tempFoodData;
    }

    public static FoodObject[] getPinnedList() {
        int pinnedCount=0;
        for(int i=0;i<foodData.length;i++) {
            if(foodData[categoryIndex][i].isPinned()) {
                pinnedCount++;
            }
        }
        FoodObject[] tempFoodData=new FoodObject[pinnedCount];
        int tempIndex=0;
        for(int i=0;i<foodData.length;i++) {
            if(foodData[categoryIndex][i].isPinned()) {
                tempFoodData[tempIndex]=foodData[categoryIndex][i];
                tempIndex++;
            }
        }
        return tempFoodData;
    }





    ////      //    //   // // //////
    //  //  //  //  ///  //      //
    //  //  //  //  // / //      //
    //  //  //  //  //  ///      //
    ////      //    //   //      //
    public interface ClickListener {
        void onClick(View view, int position);
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

            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            isPinPress=e.getX()>pinYPosition;
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

}
