package com.example.p2semesterproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CategorizedList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorized_list);
    }

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public static void setCategoryIndex(int _categoryIndex) {
        categoryIndex = _categoryIndex;
    }

   static int categoryIndex;
}
