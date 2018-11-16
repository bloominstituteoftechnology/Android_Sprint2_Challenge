package com.lambdaschool.sprint2_challenge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    ArrayList<ShoppingItem> items;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;

        public MyViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.textViewItemName);
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
        ShoppingItem item = items.get(i);
        String getName = item.getName();
        myViewHolder.txtTitle.setText(getName);
    }

    public RecycleAdapter(ArrayList<ShoppingItem> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
