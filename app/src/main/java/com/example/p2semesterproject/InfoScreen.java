package com.example.p2semesterproject;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoScreen extends AppCompatActivity {

    public static void setFoodItem(FoodObject _foodItem) {
        foodItem = _foodItem;
    }

    static FoodObject foodItem;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);
        TextView foodName = findViewById(R.id.foodName);
        TextView description = findViewById(R.id.description);
        TextView storageTime = findViewById(R.id.storageTime);
        ImageView foodImage = findViewById(R.id.foodImage);
        ImageView storageImage=findViewById(R.id.storageImage);
        foodName.setText(foodItem.getName());
        description.setText(foodItem.getDescription());
        storageTime.setText(foodItem.getStorageTime()+" day(s)");
        foodImage.setImageDrawable(foodItem.getFoodIcon());
        storageImage.setImageDrawable(foodItem.getOptimalInfoScreenStorageIcon());

        if (foodItem.getOptimalStorageSpace()=="Counter") {
            foodImage.setTranslationY(-490);
        } else if(foodItem.getOptimalStorageSpace()=="Fridge") {
            switch (foodItem.getFridgePosition()) {
                case "Crisper":
                    foodImage.setY(200); //Tanja Stuff
                    foodImage.setX(200);
                    break;
                case "Bottom Shelf":
                    foodImage.setY(200);
                    foodImage.setX(200);
                    break;
                case "Top Shelf":
                    foodImage.setY(200);
                    foodImage.setX(200);
                    break;
            }

        }
    }
}
