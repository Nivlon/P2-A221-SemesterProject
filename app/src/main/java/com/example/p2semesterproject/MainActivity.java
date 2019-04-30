package com.example.p2semesterproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Capture button from layout
        Button fruitVegBut = findViewById(R.id.fruitVegetable_category);
        Button meatBut = findViewById(R.id.meat_category);
        Button DairyBut = findViewById(R.id.dairy_category);
        Button BakedBut = findViewById(R.id.baked_category);
        // Register the onClick listener with the implementation above
        fruitVegBut.setOnClickListener(categoryButtonListener);
        meatBut.setOnClickListener(categoryButtonListener);
        DairyBut.setOnClickListener(categoryButtonListener);
        BakedBut.setOnClickListener(categoryButtonListener);


    }

    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener categoryButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            // Which button clicked?
            Intent intent=new Intent(getApplicationContext(),CategorizedList.class);
            startActivity(intent);

            switch (v.getId() /*to get clicked view id**/) {
                case R.id.fruitVegetable_category:
                    CategorizedList.setCategoryIndex(1);

                    // do something when the vegetable is clicked

                    break;
                case R.id.meat_category:
                    CategorizedList.setCategoryIndex(2);

                    // do something when the meat is clicked

                    break;
                case R.id.dairy_category:
                    CategorizedList.setCategoryIndex(3);

                    // do something when the dairy is clicked

                    break;
                case R.id.baked_category:
                    CategorizedList.setCategoryIndex(3);

                    // do something when the baked is clicked

                    break;
                default:
                    break;
            }
        }
    };

}


