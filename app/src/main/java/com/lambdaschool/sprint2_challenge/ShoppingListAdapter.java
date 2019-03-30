package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>{

    private ArrayList<ShoppingItem> itemList;
    private Context context;
    private ShoppingItem shoppingItem;

    public ShoppingListAdapter(ArrayList<ShoppingItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_list_item, parent, false);

        return new ShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingListViewHolder shoppingListViewHolder, int i) {
        final ShoppingItem item = itemList.get(i);

        shoppingListViewHolder.itemImage.setImageDrawable(context.getDrawable(item.getItemImageId()));
        shoppingListViewHolder.itemName.setText(item.getItemName());

        shoppingListViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status = ShoppingList.getCheckedStatus(item.getItemId());
                if(status) {
                    shoppingListViewHolder.parentLayout.setBackgroundColor(
                            context.getResources().getColor(R.color.white));
                    ShoppingList.setCheckedStatus(item.getItemId(), false);

                }else {
                    shoppingListViewHolder.parentLayout.setBackgroundColor(
                            context.getResources().getColor(R.color.colorAccent));
                    ShoppingList.setCheckedStatus(item.getItemId(), true);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ShoppingListViewHolder extends RecyclerView.ViewHolder {
        View parentLayout;
        ImageView itemImage;
        TextView itemName;
        Switch itemSwitch;

        public ShoppingListViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parent_layout);
            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemSwitch = itemView.findViewById(R.id.item_switch);
        }
    }
}
