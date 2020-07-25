package com.lambdaschool.sprint2_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ShoppingListViewHolder> {


    Context context= this;

    public RecyclerViewAdapter (ArrayList<ShoppingList> entryData) {this.entryData = entryData;}

    @NonNull
    @Override
    // S02M02-7 create an instance of our viewholder which is our connection to the layout
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new ShoppingListViewHolder(itemView);
    }


}
