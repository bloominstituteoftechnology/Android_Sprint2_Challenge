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

        private TextView itemName;
        private ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.list_item_name);
            itemImage = itemView.findViewById(R.id.item_image);
        }
    }

    private ArrayList<ShoppingItem> items;

    public ShoppingListAdapter(ArrayList<ShoppingItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_element_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ShoppingItem item = items.get(position);
        holder.itemName.setText(item.getName());
        holder.itemImage.setImageDrawable(GlobalClass.context.getDrawable(item.getImage()));
        //Log.i("onbind", "oamnda");
        Log.i("position", Integer.toString(position));
        if(ShoppingListModel.isInSelected(Integer.toString(items.get(position).getId()))){
            holder.itemName.setBackgroundColor(Color.GREEN);
        }
        holder.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ShoppingListModel.isInSelected(Integer.toString(items.get(position).getId()))){
                    ShoppingListModel.addToSelectedList(items.get(position).getId());
                    Log.i("position", Integer.toString(position));
                }else {
                    ShoppingListModel.removeFromSelected(items.get(position).getId());
                }
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}