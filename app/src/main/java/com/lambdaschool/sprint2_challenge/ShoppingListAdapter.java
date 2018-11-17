package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        ImageView imageView;
        TextView textView;
        Switch addSwitch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.layout_items);
            imageView = itemView.findViewById(R.id.image_item);
            textView = itemView.findViewById(R.id.text_item);
            addSwitch = itemView.findViewById(R.id.item_switch);

        }
    }

    private ArrayList<ShoppingItem> dataList;
    private Context context;

    public ShoppingListAdapter(ArrayList<ShoppingItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoppingListAdapter.ViewHolder holder, int position) {
        final ShoppingItem data = dataList.get(position);
        holder.textView.setText(data.getName());
        holder.imageView.setImageResource(data.getImageId());
        holder.addSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ShoppingListDao.setCheckedStatus(data, isChecked);
            }
        });
        holder.addSwitch.setChecked(ShoppingListDao.getCheckedStatus(data));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

//TODO replace _ in names of items and captitalize.