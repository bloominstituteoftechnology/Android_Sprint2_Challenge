package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewGroup parentLayout;
        ImageView imageView;
        TextView textView;
        //Switch switchToggle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.constraint_layout_parent);
            imageView = itemView.findViewById(R.id.image_view_icon);
            textView = itemView.findViewById(R.id.text_view_name);
            //switchToggle=itemView.findViewById(R.id.switch_added);
        }
    }

    private ArrayList<ShoppingItem> itemList;
    private Context context;

    public ShoppingListAdapter(ArrayList<ShoppingItem> itemList) {
        this.itemList = itemList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_list_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final ShoppingItem item = itemList.get(i);
        viewHolder.imageView.setImageResource(item.getShoppingItemResource());
        viewHolder.textView.setText(item.getShoppingItemName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (item.isShoppingItemInCart()) {
                viewHolder.parentLayout.setBackgroundColor(context.getColor(R.color.colorAccent));
            }
            else {
                viewHolder.parentLayout.setBackgroundColor(context.getColor(R.color.cardview_light_background));
            }
        }
        else {
            if (item.isShoppingItemInCart()) {
                viewHolder.parentLayout.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            }
            else {
                viewHolder.parentLayout.setBackgroundColor(context.getResources().getColor(R.color.cardview_light_background));
            }
        }
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color;
                if (ShoppingCart.isItemInTheShoppingCart(Integer.toString(item.getShoppingItemId()))) {
                    ShoppingCart.removeItemFromShoppingCart(Integer.toString(item.getShoppingItemId()));
                    color = R.color.cardview_light_background;
                } else {
                    ShoppingCart.addItemToShoppingCart(Integer.toString(item.getShoppingItemId()));
                    color = R.color.colorAccent;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    viewHolder.parentLayout.setBackgroundColor(context.getColor(color));
                else
                    viewHolder.parentLayout.setBackgroundColor(context.getResources().getColor(color));

                ShoppingCart.setSharedPreferences();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
