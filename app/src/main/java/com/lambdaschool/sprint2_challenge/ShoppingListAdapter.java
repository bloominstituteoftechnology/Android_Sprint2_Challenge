package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    ArrayList<ShoppingItem> shoppingItems;
    Context context;

    public ShoppingListAdapter (ArrayList<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    @NonNull
    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.shopping_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingListAdapter.ViewHolder holder, int position) {
        ShoppingItem shoppingItem = shoppingItems.get(holder.getAdapterPosition());
        final int id = shoppingItem.getItemId();
        holder.shoppingItemName.setText(shoppingItem.getItemName());
        holder.shoppingItemImage.setImageResource(shoppingItem.getImageId());
        holder.itemSwitch.setChecked(ShoppingListRepository.containsItem(id));

        holder.itemParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView shoppingItemName;
        ImageView shoppingItemImage;
        ConstraintLayout itemParentLayout;
        Switch itemSwitch;

        public ViewHolder(View itemView) {
            super(itemView);

            itemParentLayout = itemView.findViewById(R.id.item_parent_layout);
            shoppingItemName = itemView.findViewById(R.id.shopping_item_name);
            shoppingItemImage = itemView.findViewById(R.id.shopping_item_image);
            itemSwitch = itemView.findViewById(R.id.item_switch);
        }

    }
}
