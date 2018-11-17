package com.lambdaschool.sprint2_challenge;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.ArrayList;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private ImageView imageView;
        private Switch isSwitched;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.parent_view);
            imageView = itemView.findViewById(R.id.image_item);
            isSwitched = itemView.findViewById(R.id.image_name);
        }
    }

    private ArrayList<Grocery> checkedGroceries;
    private ArrayList<Grocery> uncheckedGroceries = new ArrayList<>();
    private Context context;
    private Activity activity;


    public GroceryListAdapter(ArrayList<Grocery> groceries, Activity activity) {
        this.checkedGroceries = groceries;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.grocery_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Grocery thing = checkedGroceries.get(position);
        holder.isSwitched.setText(thing.getName());
        holder.isSwitched.setTextSize(24);
        holder.imageView.setImageDrawable(context.getDrawable(thing.getIcon()));
        holder.isSwitched.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return checkedGroceries.size();
    }
}
