package com.example.p2semesterproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.MyViewHolder> {

    //Holds the food object list that is displayed
    private FoodObject[] foodData;

    // Provide a reference to the views for each data item
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageOne;
        Button infoButton;
        ImageView storageImage;
        TextView storageTime;
        ImageView pinButton;
        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item views
            name = itemView.findViewById(R.id.name);
            imageOne = itemView.findViewById(R.id.foodImage);
            storageImage = itemView.findViewById(R.id.storageImage);
            infoButton = itemView.findViewById(R.id.infoButton);
            storageTime = itemView.findViewById(R.id.time);
            pinButton=itemView.findViewById(R.id.pin);

        }
    }



    // The constructor
    public FoodListAdapter(FoodObject[] _foodData) {
        foodData = _foodData;
    }

    // Create new views
    @NonNull
    @Override
    public FoodListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card_view, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Puts the data of the food item in their corresponding places
        holder.name.setText(foodData[position].getName() + ((foodData[position].isStarred()) ? "*" : ""));
        holder.imageOne.setImageDrawable(foodData[position].getFoodIcon());
        holder.storageImage.setImageDrawable(foodData[position].getOptimalStorageIcon());
        holder.storageTime.setText(foodData[position].getStorageTime() + " day(s)");
        holder.pinButton.setImageDrawable((foodData[position].isPinned()) ? FoodObject.getPinsIcons()[0] : FoodObject.getPinsIcons()[1]);
    }

    // Returns the size of the list
    @Override
    public int getItemCount() {
        return foodData.length;
    }
}

