package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{
    private Context context;
    ArrayList<ShoppingItem> items;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_list_item,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final ShoppingItem currentItem = items.get(i);
        myViewHolder.txtTitle.setText(currentItem.getName());
        myViewHolder.imageView.setImageResource(currentItem.getImage());
        //TODO: WORK ON SOME SORT OF LISTENER
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;
        public Switch aSwitch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.shopping_list_item_name);
            imageView = itemView.findViewById(R.id.shopping_list_item_image);
            constraintLayout = itemView.findViewById(R.id.list_parent);
            aSwitch = itemView.findViewById(R.id.buy_switch);

        }
    }


}
