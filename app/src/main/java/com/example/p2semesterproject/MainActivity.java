package com.example.p2semesterproject;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

    private View.OnClickListener quizButtonListener = new View.OnClickListener(){
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v){

            setContentView(R.layout.activity_main);
            TextView newText;

            switch(v.getId()){
                case R.id.button:
                    newText = (TextView)findViewById(R.id.textView2);
                    newText.setText(Integer.toString(R.id.button));
                    break;

                case R.id.button2:
                    newText = (TextView)findViewById(R.id.textView2);
                    newText.setText(Integer.toString(R.id.button2));
                    break;

                case R.id.button3:
                    newText = (TextView)findViewById(R.id.textView2);
                    newText.setText(Integer.toString(R.id.button3));
                    break;
                default:
                    break;
            }
        }
    };

    private void quizGenerator(FoodObject food, TextView quiz, View see){
        quiz.setText("Where do you store " + food.getName() + " ?");

        switch(see.getId()) {


        }
    }

    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener categoryButtonListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v) {
            // do something when the button is clicked

            //Go to list activity (screen)
            startActivity(new Intent(getApplicationContext(),CategorizedList.class));

            // Which button clicked?
            switch (v.getId() /*to get clicked view id**/) {
                case R.id.fruit_category:
                    CategorizedList.setCategoryIndex(0);
                    // do something when the vegetable is clicked
                    break;
                case R.id.meat_category:
                    CategorizedList.setCategoryIndex(3);
                    // do something when the meat is clicked
                    break;
                case R.id.dairy_category:
                    CategorizedList.setCategoryIndex(1);
                    // do something when the dairy is clicked
                    break;
                case R.id.baked_category:
                    CategorizedList.setCategoryIndex(2);
                    // do something when the baked is clicked
                    break;
                case R.id.veg_category:
                    CategorizedList.setCategoryIndex(4);
                    // do something when the baked is clicked
                    break;
                default:
                    break;
            }
        }
    };

}


