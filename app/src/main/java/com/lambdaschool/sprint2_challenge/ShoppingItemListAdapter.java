package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ShoppingItemListAdapter extends RecyclerView.Adapter<ShoppingItemListAdapter.ShoppingItemViewHolder> {

    private ArrayList<ShoppingItem> dataList;
    Context context;

    public ShoppingItemListAdapter(ArrayList<ShoppingItem> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ShoppingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout, parent, false);
        return new ShoppingItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingItemViewHolder shoppingItemViewHolder, int i) {
        final ShoppingItem data = dataList.get(i);
        context = shoppingItemViewHolder.parentView.getContext();

        shoppingItemViewHolder.shoppingItemTextView.setText(data.getName());
        shoppingItemViewHolder.shoppingItemImageView.setImageDrawable(context.getDrawable(data.getImage()));

            if(MainActivity.itemsSelected.contains(data.getName())){
                shoppingItemViewHolder.shoppingItemTextView.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }else{
                shoppingItemViewHolder.shoppingItemTextView.setTextColor(context.getResources().getColor(R.color.colorBlack));
            }

        shoppingItemViewHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.itemsSelected.contains(data.getName())){
                    shoppingItemViewHolder.shoppingItemTextView.setTextColor(context.getResources().getColor(R.color.colorBlack));
                    MainActivity.itemsSelected.remove(data.getName());
                    data.setSelected(false);
                }else {
                    MainActivity.itemsSelected.add(shoppingItemViewHolder.shoppingItemTextView.getText().toString());
                    data.setSelected(true);
                    shoppingItemViewHolder.shoppingItemTextView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                }
                MainActivity.repo.updateShoppingList(MainActivity.itemsSelected);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    static class ShoppingItemViewHolder extends RecyclerView.ViewHolder{

        View parentView;
        ImageView shoppingItemImageView;
        TextView shoppingItemTextView;

        public ShoppingItemViewHolder(@NonNull View itemView) {
            super(itemView);

            parentView = itemView.findViewById(R.id.layout_parent);
            shoppingItemImageView = itemView.findViewById(R.id.item_image_view);
            shoppingItemTextView = itemView.findViewById(R.id.item_text_view);
        }
    }
}
