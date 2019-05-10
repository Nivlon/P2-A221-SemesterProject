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
                            new FoodObject("Apple", res.getDrawable(R.drawable.apple), "Fridge","7-28","Store in room temperature until ripe. Leave unwashed and in plastic bags.","Crisper"),
                            new FoodObject("Banana", res.getDrawable(R.drawable.banana), "Counter","2-3","Should be hung up and kept in a cool place until they are ready to eat. Do not break the bananas apart and avoid putting bananas in the fridge as they brown quickly and can cause chill injury to the fruit. Bananas secrete ethylene gas which accelerates the ripening of other food items, which cause them to spoil faster. So try and store them separately from other items."),
                            new FoodObject("Grapes", res.getDrawable(R.drawable.grape), "Fridge","7","","Crisper"),
                            new FoodObject("Kiwi", res.getDrawable(R.drawable.kiwi), "Fridge", "3-4","Store in room temperature until ripe. "),
                            new FoodObject("Melon", res.getDrawable(R.drawable.melon), "Fridge","14","Store in room temperature until ripe. Store in fridge when ripe or cut.","Crisper"),
                           // new FoodObject("Orange", res.getDrawable(R.drawable.orange), "Counter"),
                            new FoodObject("Pear", res.getDrawable(R.drawable.pear), "Fridge","3-4","Store in room temperature until ripe","Crisper"),
                            new FoodObject("Raspberry", res.getDrawable(R.drawable.raspberry), "Fridge","1-2","Remove any spoiled or crushed fruits before storing.","Crisper"),
                            new FoodObject("Strawberry", res.getDrawable(R.drawable.strawberry), "Fridge","1-2","Remove any spoiled or crushed fruits before storing.","Crisper"),
                            new FoodObject("Watermelon", res.getDrawable(R.drawable.watermelon), "Fridge","14","Store in room temperature until ripe. Store in fridge when ripe or cut.","Crisper"),
                            //new FoodObject("Lemon", res.getDrawable(R.drawable.lemon), "Counter"),
                    },
                    //Dairy and Eggs
                    {
                            new FoodObject("Cheese", res.getDrawable(R.drawable.cheese), "Fridge","21-28","Can be kept in the freezer for 3-4 months","Top Shelf"),
                            new FoodObject("Eggs", res.getDrawable(R.drawable.egg), "Fridge","21-28","If you are only using either the egg yolk or the egg white and want to store the leftover, expect that the yolk can last 1 day and the whites 2-3 days in the fridge.","Top Shelf"),
                            new FoodObject("Milk", res.getDrawable(R.drawable.milk), "Fridge","7","","Top Shelf"),
                            new FoodObject("Yogurt", res.getDrawable(R.drawable.yogurt), "Fridge","28-31","","Top Shelf"),
                            new FoodObject("Butter", res.getDrawable(R.drawable.butter), "Fridge","89-92","Can be kept in the freezer for 6-9 months."),
                    },
                    //Baked goods
                    {
                            new FoodObject("White Bread", res.getDrawable(R.drawable.bread), "Counter","2-4","Keep it in airtight packaging, like a plastic bag, or it will dry out and become stale."),
                            new FoodObject("Rye Bread", res.getDrawable(R.drawable.ryebread), "Counter","4-5","Keep it in airtight packaging, like a plastic bag, or it will dry out and become stale."),
                    },
                    //Meat
                    {
                            new FoodObject("Beef", res.getDrawable(R.drawable.beef), "Fridge","3-5","Leave in store-bought packaging when stored in the fridge, if you need to reseal the meat, put it in an airtight plastic bag with as little air as possible. It will last about a day like this. When storing in the freezer, rewrap it in airtight freezer bags. Can be kept in the freezer for 4-12 months.","Bottom Shelf"),
                            new FoodObject("Chicken", res.getDrawable(R.drawable.chicken), "Fridge","1-2","Leave in store-bought packaging when stored in the fridge, if you need to reseal the meat, put it in an airtight plastic bag with as little air as possible. It will last about a day like this. When storing in the freezer, rewrap it in airtight freezer bags. Can be kept in the freezer for up to 9 months.","Bottom Shelf"),
                            new FoodObject("Fish", res.getDrawable(R.drawable.fish), "Fridge","3-5","Can be kept in the freezer for 6-9 months.","Top Shelf"),
                            new FoodObject("Pork", res.getDrawable(R.drawable.pork), "Fridge","3-5","Leave in store-bought packaging when stored in the fridge, if you need to reseal the meat, put it in an airtight plastic bag with as little air as possible. It will last about a day like this. When storing in the freezer, rewrap it in airtight freezer bags. Can be kept in the freezer for 4-12 months.","Bottom Shelf")
                    },
                    //Vegetables
                    {
                            new FoodObject("Bell Pepper", res.getDrawable(R.drawable.bellpep), "Fridge","4-5","","Crisper"),
                            new FoodObject("Cabbage", res.getDrawable(R.drawable.cabbage), "Fridge","7-14","","Crisper"),
                            new FoodObject("Carrot", res.getDrawable(R.drawable.carrot), "Fridge","21","","Crisper"),
                            new FoodObject("Chili", res.getDrawable(R.drawable.chilli), "Fridge","4-5","","Crisper"),
                            new FoodObject("Corn", res.getDrawable(R.drawable.corn), "Fridge","1-2","","Crisper"),
                            new FoodObject("Cucumber", res.getDrawable(R.drawable.cucumb), "Fridge","4-5","","Crisper"),
                            new FoodObject("Garlic", res.getDrawable(R.drawable.garlic), "Counter","28-31"),
                            new FoodObject("Onion", res.getDrawable(R.drawable.onion), "Counter","14-28","Store in a dry area and away from light."),
                            new FoodObject("Potato", res.getDrawable(R.drawable.potatoes), "Counter","28-62","Potatoes are a bit fragile, avoid bumping them as this can cause them to produce solanine, a toxic substance. They should be stored in a cool dry place and away from light."),
                            new FoodObject("Tomato", res.getDrawable(R.drawable.tomato), "Fridge","5-6","Store unripened tomatoes in room temperature and away from light until ripe.","Crisper"),
                            new FoodObject("Lettuce", res.getDrawable(R.drawable.lettuce), "Fridge","7-14","Store in a bag.","Crisper")
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
        for(int i=0;i<foodData[categoryIndex].length;i++) {
            if(foodData[categoryIndex][i].isPinned()) {
                pinnedCount++;
            }
        }
        if(pinnedCount>0) {
            FoodObject[] tempFoodData = new FoodObject[pinnedCount];
            int tempIndex = 0;
            for (int i = 0; i < foodData[categoryIndex].length; i++) {
                if (foodData[categoryIndex][i].isPinned()) {
                    tempFoodData[tempIndex] = foodData[categoryIndex][i];
                    tempIndex++;
                }
            }
            return tempFoodData;
        } else { return null;}

    }


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
