package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    static class ViewHolder extends  RecyclerView.ViewHolder {
        ViewGroup parentLayout;
        TextView  nameView;
        ImageView emojiView;




        ViewHolder(View view) {
            super(view);
            parentLayout = view.findViewById(R.id.element_parent_layer);
            nameView = view.findViewById(R.id.item_text);
            emojiView = view.findViewById(R.id.item_emoji);

        }

    }


    private ArrayList<ShoppingList> dataList;
    private Context                 context;

    ShoppingListAdapter(ArrayList<ShoppingList> dataList) {
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_element_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final ShoppingList data = dataList.get(i);

        viewHolder.nameView.setText(data.getGrocery_name()) ;
        viewHolder.emojiView.setImageResource(data.getGrocery_icon());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                 if(data.isChecked()){
                     data.setChecked(false);
                     viewHolder.parentLayout.setBackgroundColor(context.getResources().getColor(R.color.cardview_light_background));
                     Intent intent = new Intent(context, ShoppingList.class);
                 } else {
                     data.setChecked(true);
                     viewHolder.parentLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                 }
            }
        });

    }

    @Override
    public int getItemCount(){
        return dataList.size();
    }


}
