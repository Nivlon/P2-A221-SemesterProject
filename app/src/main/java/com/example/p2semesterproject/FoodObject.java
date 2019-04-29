package com.example.p2semesterproject;



public class FoodObject {

    private String name;
    static String[] storageSpaces={"Fridge","Freezer","Cabinet"};
    private int[] storageTime=new int[storageSpaces.length];

    FoodObject(String _name) {
        name=_name;
    }

    FoodObject(String _name,int[] _storageTime) {
        name=_name;
        storageTime=_storageTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(int[] storageTime) {
        this.storageTime = storageTime;
    }

}
