package com.example.p2semesterproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Quiz Variables
    private static TextView quizText;
    private static CardView quizView;
    private static FoodObject quizFood;

    //Legend Variables
    private static ImageView theLegendImage;
    private static Button theLegendButton;
    private static boolean isLegendOn=false;
    private Drawable legendOpenImg;
    private Drawable legendClosedImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        legendOpenImg=getResources().getDrawable(R.drawable.iconinformationopen);
        legendClosedImg=getResources().getDrawable(R.drawable.iconinformationclosed);
        quizView=findViewById(R.id.quiz_view);
        quizText = findViewById(R.id.textView2);
        theLegendImage = findViewById(R.id.thelegend27);
        theLegendButton = findViewById(R.id.legend_button);
        theLegendButton.setOnClickListener(legendButtonListener);
        theLegendImage.setImageDrawable(legendClosedImg);
        // Quiz buttons
        Button option_1 = findViewById(R.id.button);
        Button option_2 = findViewById(R.id.button2);
        Button option_3 = findViewById(R.id.button3);


        // Quiz onClick listener
        option_1.setOnClickListener(quizButtonListener);
        option_2.setOnClickListener(quizButtonListener);
        option_3.setOnClickListener(quizButtonListener);


        // Capture button from layout
        Button fruitBut = findViewById(R.id.fruit_category);
        Button meatBut = findViewById(R.id.meat_category);
        Button dairyBut = findViewById(R.id.dairy_category);
        Button bakedBut = findViewById(R.id.baked_category);
        Button vegBut = findViewById(R.id.veg_category);

        // Register the onClick listener with the implementation above
        fruitBut.setOnClickListener(categoryButtonListener);
        meatBut.setOnClickListener(categoryButtonListener);
        dairyBut.setOnClickListener(categoryButtonListener);
        bakedBut.setOnClickListener(categoryButtonListener);
        vegBut.setOnClickListener(categoryButtonListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        if (CategorizedList.foodDataCreated) {
            if (CategorizedList.getPinnedList() != null) {
                quizGenerator(CategorizedList.getPinnedList()[new Random().nextInt(CategorizedList.getPinnedList().length)]);
                quizView.setVisibility(View.VISIBLE);
            } else {
                quizView.setVisibility(View.INVISIBLE);
            }
        } else {
            quizView.setVisibility(View.INVISIBLE);
        }
    }


    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener categoryButtonListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v) { //Activates when a category button is clicked

            //Go to list Activity
            startActivity(new Intent(getApplicationContext(),CategorizedList.class));

            // Which button is clicked?
            switch (v.getId()) {
                case R.id.fruit_category:
                    CategorizedList.setCategoryIndex(0);
                    break;
                case R.id.dairy_category:
                    CategorizedList.setCategoryIndex(1);
                    break;
                case R.id.baked_category:
                    CategorizedList.setCategoryIndex(2);
                    break;
                case R.id.meat_category:
                    CategorizedList.setCategoryIndex(3);
                    break;
                case R.id.veg_category:
                    CategorizedList.setCategoryIndex(4);
                    break;
            }
        }
    };


    //Quiz Functions

    //Set which food the quiz will ask you about
    public static void setQuizFood(FoodObject quizFood) { MainActivity.quizFood = quizFood; }

    //Creates question
    public static void quizGenerator(FoodObject food){
        quizText.setText("Where do you store " + food.getName() + " ?");
        setQuizFood(food);
    }

    //Checks which answer button is pressed and provides an answer
    public static void quiz(View v, FoodObject food){
        String buttonText="";
        switch(v.getId()){
            case R.id.button:
                buttonText="Fridge";
                break;
            case R.id.button2:
                buttonText="Freezer";
                break;
            case R.id.button3:
                buttonText="Counter";
                break;
        }
        if(buttonText==food.getOptimalStorageSpace())
            if(food.getOptimalStorageSpace()=="Counter")
                quizText.setText("That is correct it can be stored for " + food.getStorageTime() + " on the " + food.getOptimalStorageSpace());
            else
                quizText.setText("That is correct it can be stored for " + food.getStorageTime() + " in the " + food.getOptimalStorageSpace());
        else
            quizText.setText("That is unfortunately wrong");
    }

    private View.OnClickListener legendButtonListener= new View.OnClickListener() {
        public void onClick(View v) {
            isLegendOn=!isLegendOn;
            if(isLegendOn) {
                theLegendImage.setImageDrawable(legendOpenImg);

            } else {
                theLegendImage.setImageDrawable(legendClosedImg);

                theLegendImage.bringToFront();
            }
        }
    };


    private View.OnClickListener quizButtonListener = new View.OnClickListener(){
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v){
            quiz(v, quizFood);
        }
    };
}

