package com.example.p2semesterproject;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class FoodObject {

    private String name;
    private Drawable foodIcon;
    private static String[] storageSpaces={"Fridge","Freezer","Counter"};
    private static Drawable[] storageSpaceIcons;
    private int optimalStorageIndex;
    private int storageTime=0;
    private boolean isPinned=false;
    public String Description="";
    private static Drawable[] pinsIcons;

    public static void setPinsIcons(Drawable[] _pinsIcons) {
        pinsIcons = _pinsIcons;
    }


    public static Drawable[] getPinsIcons() {
        return pinsIcons;
    }

    FoodObject(String _name, Drawable _foodIcon, String _optimalStorageString) {
        name=_name;
        foodIcon=_foodIcon;
        for(int i=0;i<storageSpaces.length;i++) {
            if(storageSpaces[i].equals(_optimalStorageString)) {
                optimalStorageIndex=i;
                break;
            }
        }
    }

    FoodObject(String _name, Drawable _foodIcon, String _optimalStorageString, int _storageTime) {
        name=_name;
        foodIcon=_foodIcon;
        for(int i=0;i<storageSpaces.length;i++) {
            if(storageSpaces[i].equals(_optimalStorageString)) {
                optimalStorageIndex=i;
                break;
            }
        }
        storageTime=_storageTime;
    }

    FoodObject(String _name, Drawable _foodIcon, String _optimalStorageString, int _storageTime, String _description) {
        name=_name;
        foodIcon=_foodIcon;
        for(int i=0;i<storageSpaces.length;i++) {
            if(storageSpaces[i].equals(_optimalStorageString)) {
                optimalStorageIndex=i;
                break;
            }
        }
        storageTime=_storageTime;
        Description=_description;
    }

    public String getName() { return name; }

    public Drawable getFoodIcon() { return foodIcon; }

    public int getStorageTime() { return storageTime; }

    public Drawable[] getStorageSpaceIcons() { return storageSpaceIcons; }

    public static void setStorageSpaceIcons(Drawable[] storageSpaceIcons) {
        FoodObject.storageSpaceIcons = storageSpaceIcons;
    }

    public String getOptimalStorageSpace() {return storageSpaces[optimalStorageIndex];}

    public Drawable getOptimalStorageIcon() { return storageSpaceIcons[optimalStorageIndex]; }

    public void Pin() {
        isPinned = !isPinned;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public String getDescription() {
        return Description;
    }
}
