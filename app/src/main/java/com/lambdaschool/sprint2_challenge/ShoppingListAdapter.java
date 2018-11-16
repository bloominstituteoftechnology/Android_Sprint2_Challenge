package com.lambdaschool.sprint2_challenge;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout listParent;
        private Switch itemName;
        private ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listParent = itemView.findViewById(R.id.list_parent);
            itemName = itemView.findViewById(R.id.list_item_name);
            itemImage = itemView.findViewById(R.id.item_image);
        }
    }

    private ArrayList<ShoppingItem> items;
    private String[] list;
    private Context context;
    private Activity activity;


    public ShoppingListAdapter(ArrayList<ShoppingItem> items, Activity activity) {
        this.items = items;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_element_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ShoppingItem item = items.get(position);
        holder.itemName.setText(item.getName());
        holder.itemImage.setImageDrawable(context.getDrawable(item.getImage()));

        holder.itemName.setOnCheckedChangeListener(null);
        holder.itemName.setChecked(item.getSelected());
        holder.itemName.setBackgroundColor(item.getColor());

        String ids = ShoppingListModel.getSelectedItemsString();
        String[] idsArray = ids.split(",");
        Log.i("idsarray", idsArray[0]);

        holder.itemName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    item.setSelected(isChecked);
                    ShoppingListModel.addToSelectedList(items.get(position).getId());
                }else{
                    item.setSelected(!isChecked);
                    ShoppingListModel.removeFromSelected(items.get(position).getId());
                }
                Log.i("shoplist", ShoppingListModel.getSelectedItemsString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}