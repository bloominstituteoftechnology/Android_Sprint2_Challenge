package com.example.israel.android_sprint2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    public ShoppingListAdapter(Context context, ArrayList<ShoppingItem> shoppingItems) {
        this.context = context;
        this.shoppingItems = shoppingItems;
    }

    Context context;
    ArrayList<ShoppingItem> shoppingItems;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // create the item view
        View itemView = LayoutInflater.from(context).inflate(R.layout.shopping_item_layout, parent, false);

        return new ShoppingListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // set the views' data from the item list
        final ShoppingItem shoppingItem = shoppingItems.get(i);
        viewHolder.iconImageView.setImageDrawable(shoppingItem.getIconDrawable());
        viewHolder.nameTextView.setText(shoppingItem.getName());

        if (SelectedShoppingItemsSPDAO.isShoppingItemSelected(shoppingItem)) { // is it selected
            int selectedColor = context.getResources().getColor(R.color.shopping_item_selected);
            viewHolder.itemView.setBackgroundColor(selectedColor);
        } else {
            int unselectedColor = context.getResources().getColor(R.color.shopping_item_unselected);
            viewHolder.itemView.setBackgroundColor(unselectedColor);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SelectedShoppingItemsSPDAO.isShoppingItemSelected(shoppingItem)) {
                    // remove
                    SelectedShoppingItemsSPDAO.removeSelectedShoppingItem(shoppingItem);
                } else {
                    // add
                    SelectedShoppingItemsSPDAO.addSelectedShoppingItem(shoppingItem);
                }

                // TODO update/sort array and notify this
                notifyDataSetChanged(); // this will also change the color of the selected item
            }

        });

    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // set the relevant views
            iconImageView = itemView.findViewById(R.id.image_view_icon);
            nameTextView = itemView.findViewById(R.id.text_view_name);
        }

        ImageView iconImageView;
        TextView nameTextView;

    }

}
