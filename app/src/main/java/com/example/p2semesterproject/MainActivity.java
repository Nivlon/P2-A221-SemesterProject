package com.example.p2semesterproject;

//Importing libraries (functions and features) we need
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

//The Main (Category) Screen's code
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

    //What happens when you first open the screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Idk
        setContentView(R.layout.activity_main); //Switches screen's look to main
        legendOpenImg=getResources().getDrawable(R.drawable.iconinformationopen); //Gets legend pictures
        legendClosedImg=getResources().getDrawable(R.drawable.iconinformationclosed);
        quizView=findViewById(R.id.quiz_view); //Gets reference for items things on screen (so we can modify them in code
        quizText = findViewById(R.id.textView2);
        theLegendImage = findViewById(R.id.thelegend27);
        theLegendButton = findViewById(R.id.legend_button);
        theLegendButton.setOnClickListener(legendButtonListener); //Adds listener (functionality) to the legend button
        theLegendImage.setImageDrawable(legendClosedImg); //Sets legend image picture
        // Quiz buttons (getting reference for them)
        Button option_1 = findViewById(R.id.button);
        Button option_2 = findViewById(R.id.button2);
        Button option_3 = findViewById(R.id.button3);

        // Adding onClickListeners (functionality) to quiz buttons
        option_1.setOnClickListener(quizButtonListener);
        option_2.setOnClickListener(quizButtonListener);
        option_3.setOnClickListener(quizButtonListener);

        // Getting reference for category buttons
        Button fruitBut = findViewById(R.id.fruit_category);
        Button meatBut = findViewById(R.id.meat_category);
        Button dairyBut = findViewById(R.id.dairy_category);
        Button bakedBut = findViewById(R.id.baked_category);
        Button vegBut = findViewById(R.id.veg_category);

        // Register the onClick listeners (functionality) to category buttons
        fruitBut.setOnClickListener(categoryButtonListener);
        meatBut.setOnClickListener(categoryButtonListener);
        dairyBut.setOnClickListener(categoryButtonListener);
        bakedBut.setOnClickListener(categoryButtonListener);
        vegBut.setOnClickListener(categoryButtonListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) //This is just required
    //This function runs when you return to this screen from the list screen
    @Override
    protected void onResume() {
        super.onResume();
        if (CategorizedList.foodDataCreated) { //This outer if is to avoid asking for the food data before we create it in the categorizedList
            if (CategorizedList.getPinnedList() != null) { //If there are pinned items in the last visited category
                FoodObject randomPinnedFood=CategorizedList.getPinnedList()[new Random().nextInt(CategorizedList.getPinnedList().length)]; //Pick one of them
                quizGenerator(randomPinnedFood); //And generate a quiz based on it
                quizView.setVisibility(View.VISIBLE); //And make the quiz visible
            } else {
                quizView.setVisibility(View.INVISIBLE); //Or invisible if not
            }
        } else {
            quizView.setVisibility(View.INVISIBLE);
        }
    }


    // Create an implementation of OnClickListener for the category buttons
    private View.OnClickListener categoryButtonListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v) { //Activates when a category button is clicked

            //Go to list Activity
            startActivity(new Intent(getApplicationContext(),CategorizedList.class));

            // Which button is clicked? (sets the list view to that category)
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

    //Creates a question
    public static void quizGenerator(FoodObject food){
        quizText.setText("Where do you store " + food.getName() + " ?");
        setQuizFood(food);
    }

    //Set which food the quiz will ask you about
    public static void setQuizFood(FoodObject quizFood) { MainActivity.quizFood = quizFood; }

    //Checks which answer button is pressed and provides an answer
    public static void quiz(View v, FoodObject food) {
        String buttonText="";
        switch (v.getId()) { //Gets the storage space the quiz button represents
            case R.id.button:
                buttonText = "Fridge";
                break;
            case R.id.button2:
                buttonText = "Freezer";
                break;
            case R.id.button3:
                buttonText = "Counter";
                break;
        }
        if (buttonText == food.getOptimalStorageSpace()) { //Compares the storage space of the button to the optimal storage space of the food item
            if (food.getOptimalStorageSpace() == "Counter") //Writes 'on' or 'in' based on if it's stored in counter or not
                quizText.setText("\nThat's right! It can be stored for " + food.getStorageTime() + " day(s) on the " + food.getOptimalStorageSpace()+"\n \nDid you know that 30% of all food waste happens in households? Great job on trying to do better!");
            else
                quizText.setText("\nThat's right! It can be stored for " + food.getStorageTime() + " day(s) in the " + food.getOptimalStorageSpace()+"\n \nDid you know that 30% of all food waste happens in households? Great job on trying to do better!");
        Handler handler = new Handler(); //We need this for delaying the quiz disappearing
        handler.postDelayed(new Runnable() { //Makes the quiz disappear after 4.2 seconds
                public void run() {
                    quizView.setVisibility(View.INVISIBLE);
                }
            }, 8000);
        }
        else //What it says if they answer wrong
            if (food.getOptimalStorageSpace() == "Counter")
                quizText.setText("Sorry, that is incorrect, you should store "+quizFood.getName()+" on the "+quizFood.getOptimalStorageSpace()+".");
            else
                quizText.setText("Sorry, that is incorrect, you should store "+quizFood.getName()+" in the "+quizFood.getOptimalStorageSpace()+".");
    }

    //Legend button OnClick Listener (this defines what happens when you click on it)
    private View.OnClickListener legendButtonListener= new View.OnClickListener() {
        public void onClick(View v) {
            isLegendOn=!isLegendOn; //Toggles it being on/off
            if(isLegendOn) { //Draws it if its on
                theLegendImage.setImageDrawable(legendOpenImg);
            } else { //Doesn't if it's off
                theLegendImage.setImageDrawable(legendClosedImg);
            }
        }
    };

    //Quiz functionality (just calls the other quiz functions
    private View.OnClickListener quizButtonListener = new View.OnClickListener(){
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v){
            quiz(v, quizFood);
        }
    };
}

