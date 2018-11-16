package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter  extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>{

    static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.layout_items);
            imageView = itemView.findViewById(R.id.image_item);
            textView = itemView.findViewById(R.id.text_item);
        }
    }

    private ArrayList<ShoppingItem> dataList;
    private Context context;

    public ShoppingListAdapter(ArrayList<ShoppingItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoppingListAdapter.ViewHolder holder, int position){
        final ShoppingItem data = dataList.get(position);
        holder.textView.setText(data.getName());
        holder.imageView.setImageResource(data.getImageId());
//        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                Intent clickIntent = new Intent(context, MainActivity.class);
                clickIntent.putExtra("DISPLAY_IMAGE",data);
                context.startActivity(clickIntent);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

//TODO replace _ in names of items and captitalize.