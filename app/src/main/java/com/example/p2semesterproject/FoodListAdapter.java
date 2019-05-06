package com.example.p2semesterproject;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.MyViewHolder> {
    private String[] foodListData;
    private Drawable[] foodImage;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;// init the item view's
        ImageView imageOne;
        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            name = itemView.findViewById(R.id.name);
            imageOne = itemView.findViewById(R.id.foodImage);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FoodListAdapter(String[] _foodListData, Drawable[] _foodImage) {
        foodListData = _foodListData;
        foodImage = _foodImage;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public FoodListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card_view, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(foodListData[position]);
        holder.imageOne.setImageDrawable(foodImage[position]);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foodListData.length;
    }
}

