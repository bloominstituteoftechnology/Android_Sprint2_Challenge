package com.lambdaschool.sprint2_challenge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    ArrayList<ShoppingItem> items;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.textViewItemName);
            imageView = view.findViewById(R.id.imageViewItem);

        }
    }
    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.MyViewHolder myViewHolder, int i) {

        ShoppingItem currentItem = items.get(i);

        myViewHolder.txtTitle.setText(currentItem.getName());
        myViewHolder.imageView.setImageResource(currentItem.getImage());
    }

    public RecycleAdapter(ArrayList<ShoppingItem> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
