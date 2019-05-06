package com.example.p2semesterproject;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class FoodObject {

    private String name;
    //static String[] storageSpaces={"Fridge","Freezer","Cabinet"};
    //private int[] storageTime=new int[storageSpaces.length];
    private Drawable icon;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    FoodObject(String _name) {
        name=_name;
        icon=CategorizedList.lemonPic;
    }

    FoodObject(String _name, Drawable _icon) {
        name=_name;
        icon=_icon;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Drawable getIcon() { return icon; }

    public void setIcon(Drawable icon) { this.icon = icon; }

}
