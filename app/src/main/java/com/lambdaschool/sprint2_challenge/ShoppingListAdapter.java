package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder> {

    ShoppingList list;

    public ShoppingListAdapter(ShoppingList list) {
        this.list = list;
    }

    @NonNull
    @Override

    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_item, parent, false);
        return new ShoppingListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder shoppingListViewHolder, int i) {
        final ShoppingItem data = list.get(i);

        shoppingListViewHolder.itemName.setText(data.getName());
        shoppingListViewHolder.itemImage.setImageDrawable(shoppingListViewHolder.context.getDrawable(data.getImage()));

        if(data.getSelected()) {
            shoppingListViewHolder.parentView.setBackgroundColor(0x00FF00);
        }

        shoppingListViewHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setSelected(!data.getSelected());
            }
        });
    }

    @Override
    public int getItemCount() {return this.list.size();}

    class ShoppingListViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        ImageView itemImage;
        View parentView, parentLayout;
        Context context;

        public ShoppingListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemImage = itemView.findViewById(R.id.itemImage);

            parentView = itemView.findViewById(R.id.list_parent);
            parentLayout = itemView.findViewById(R.id.container_layout);
            context = itemView.getContext();
        }
    }


}
