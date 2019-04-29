package com.example.p2semesterproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Capture our button from layout
        Button fruitVegBut = findViewById(R.id.corky);
        Button meatBut = findViewById(R.id.corky2);
        Button DairyBut = findViewById(R.id.corky3);
        // Register the onClick listener with the implementation above
        fruitVegBut.setOnClickListener(mCorkyListener);
        meatBut.setOnClickListener(mCorkyListener);
        DairyBut.setOnClickListener(mCorkyListener);

    }

    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            // Yes we will handle click here but which button clicked??? We don't know

            // So we will make
            switch (v.getId() /*to get clicked view id**/) {
                case R.id.corky:

                    // do something when the corky is clicked

                    break;
                case R.id.corky2:

                    // do something when the corky2 is clicked

                    break;
                case R.id.corky3:

                    // do something when the corky3 is clicked

                    break;
                default:
                    break;
            }
        }
    };

}


