package com.lambdaschool.sprint2_challenge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>{

    ArrayList<ShoppingItem> itemSelected = new ArrayList<>();
    ArrayList<ShoppingItem> itemList;

    public ShoppingListAdapter(ArrayList<ShoppingItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_list_item, parent, false);

        return new ShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder shoppingListViewHolder, int i) {
        ShoppingItem item = itemList.get(i);

        shoppingListViewHolder.itemImage.setImageResource(item.getItemImageId());
        shoppingListViewHolder.itemSwitch.setText(item.getItemName());

        shoppingListViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO way to change background color when clicked
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    static class ShoppingListViewHolder extends RecyclerView.ViewHolder {

        View parentLayout;
        ImageView itemImage;
        Switch itemSwitch;

        public ShoppingListViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parent_layout);
            itemImage = itemView.findViewById(R.id.item_image);
            itemSwitch = itemView.findViewById(R.id.item_switch);
        }
    }
}
