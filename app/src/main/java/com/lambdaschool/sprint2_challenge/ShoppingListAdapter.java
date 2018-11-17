package com.lambdaschool.sprint2_challenge;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
        holder.itemName.setBackgroundColor(Color.WHITE);

        if(ShoppingListModel.isInSelected(Integer.toString(items.get(position).getId()))){
            holder.itemName.setBackgroundColor(Color.GREEN);
        }else {
            holder.itemName.setBackgroundColor(Color.WHITE);
        }

        holder.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ShoppingListModel.isInSelected(Integer.toString(items.get(position).getId()))){
                    ShoppingListModel.addToSelectedList(items.get(position).getId());
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