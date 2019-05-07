package com.example.p2semesterproject;

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);
        TextView foodName = findViewById(R.id.foodName);
        ImageView foodImage = findViewById(R.id.foodImage);
        foodName.setText(foodItem.getName());
        //foodImage.setBackground(CategorizedList.lemonPic);
    }
}
