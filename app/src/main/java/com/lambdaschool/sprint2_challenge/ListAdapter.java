package com.lambdaschool.sprint2_challenge;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter <ListAdapter.ViewHolder> {
    private ArrayList<ShoppingItem> itemList;

    ListAdapter(ArrayList<ShoppingItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_single_element, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAdapter.ViewHolder vh, int i) {
        final CartRepo cartRepo = new CartRepo(vh.context);
        vh.aSwitch.setChecked(cartRepo.isInCart(itemList.get(i)));
        vh.imageView.setImageResource(itemList.get(i).image);
        vh.textView.setText(itemList.get(i).name);
        vh.parent.setOnClickListener(new View.OnClickListener() { //Toggles switch and add/removes from CartRepo
            @Override
            public void onClick(View view) {
                if (cartRepo.isInCart(itemList.get(vh.getAdapterPosition()))) {
                    cartRepo.removeItemFromCart(itemList.get(vh.getAdapterPosition()));
                    vh.aSwitch.setChecked(false);
                } else {
                    cartRepo.addItemToCart(itemList.get(vh.getAdapterPosition()));
                    vh.aSwitch.setChecked(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        Switch aSwitch;
        View parent;
        Context context;
        ViewHolder(@NonNull View v) {
            super(v);
            textView = v.findViewById(R.id.element_textview);
            imageView = v.findViewById(R.id.element_image);
            aSwitch = v.findViewById(R.id.element_switch);
            parent = v.findViewById(R.id.element_parent);
            context = v.getContext();
        }
    }
}
