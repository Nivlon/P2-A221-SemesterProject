package com.example.p2semesterproject;

import android.graphics.drawable.Drawable;

public class FoodObject {

    //Variables specific to class
    private static String[] storageSpaces={"Fridge","Freezer","Counter"};
    private static Drawable[] storageSpaceIcons;
    private static Drawable[] storageSpaceInfScreenIcons;
    private static Drawable[] pinsIcons;

    //Variables specific to each food item
    private String name;
    private Drawable foodIcon;
    private int optimalStorageIndex;
    private String storageTime="";
    private boolean isPinned=false;
    private String Description="";
    private boolean isStarred=false;
    private String fridgePosition="";

    //Constructors
    FoodObject(String _name, Drawable _foodIcon, String _optimalStorageString, String _storageTime) {
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

    FoodObject(String _name, Drawable _foodIcon, String _optimalStorageString, String _storageTime, String _description) {
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
        if(Description!=""){isStarred=true;}

    }

    FoodObject(String _name, Drawable _foodIcon, String _optimalStorageString, String _storageTime, String _description, String _fridgePosition) {
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
        fridgePosition=_fridgePosition;
        if(Description!="") {isStarred=true;}
    }

    //Setters, getters and additional functions
    public String getFridgePosition() {
        return fridgePosition;
    }

    public boolean isStarred() {
        return isStarred;
    }

    public static void setPinsIcons(Drawable[] _pinsIcons) {
        pinsIcons = _pinsIcons;
    }

    public static Drawable[] getPinsIcons() {
        return pinsIcons;
    }

    public String getName() { return name; }

    public Drawable getFoodIcon() { return foodIcon; }

    public String getStorageTime() { return storageTime; }

    public static void setStorageSpaceIcons(Drawable[] storageSpaceIcons) {
        FoodObject.storageSpaceIcons = storageSpaceIcons;
    }

    public static void setStorageInfoScreenIcons(Drawable[] _storageSpaceInfScreenIcons) {
        FoodObject.storageSpaceInfScreenIcons = _storageSpaceInfScreenIcons;
    }

    public String getOptimalStorageSpace() {return storageSpaces[optimalStorageIndex];}

    public Drawable getOptimalStorageIcon() { return storageSpaceIcons[optimalStorageIndex]; }

    public Drawable getOptimalInfoScreenStorageIcon() { return storageSpaceInfScreenIcons[optimalStorageIndex]; }

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
