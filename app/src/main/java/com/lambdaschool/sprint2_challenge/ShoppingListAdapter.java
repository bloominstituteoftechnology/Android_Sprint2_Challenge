package com.lambdaschool.sprint2_challenge;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

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
