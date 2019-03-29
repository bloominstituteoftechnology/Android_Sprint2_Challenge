package com.lambdaschool.sprint2_challenge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>{



    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder shoppingListViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ShoppingListViewHolder extends RecyclerView.ViewHolder {

        public ShoppingListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
