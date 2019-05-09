package com.example.p2semesterproject;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.MyViewHolder> {
    private FoodObject[] foodData;
    private View.OnClickListener infoButt;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;// init the item view's
        ImageView imageOne;
        Button infoButton;
        ImageView storageImage;
        TextView storageTime;
        ImageView pinButton;
        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            name = itemView.findViewById(R.id.name);
            imageOne = itemView.findViewById(R.id.foodImage);
            storageImage = itemView.findViewById(R.id.storageImage);
            infoButton = itemView.findViewById(R.id.infoButton);
            storageTime = itemView.findViewById(R.id.time);
            pinButton=itemView.findViewById(R.id.pin);

        }
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public FoodListAdapter(FoodObject[] _foodData) {
        foodData = _foodData;
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
        try {
            holder.name.setText(foodData[position].getName() + ((foodData[position].isStarred()) ? "*" : ""));
            holder.imageOne.setImageDrawable(foodData[position].getFoodIcon());
            holder.storageImage.setImageDrawable(foodData[position].getOptimalStorageIcon());
            holder.storageTime.setText(foodData[position].getStorageTime() + " day(s)");
            holder.pinButton.setImageDrawable((foodData[position].isPinned()) ? FoodObject.getPinsIcons()[0] : FoodObject.getPinsIcons()[1]);
        } catch (NullPointerException e) {

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foodData.length;
    }
}

