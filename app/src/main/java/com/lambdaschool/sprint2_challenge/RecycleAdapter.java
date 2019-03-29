package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


import java.util.ArrayList;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context context;

    ArrayList<ShoppingItem> items = MainActivity.preferences.

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public ImageView imageView;
        public ConstraintLayout linearLayout;
        public Switch aSwitch;


        public MyViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.shopping_list_item_name);
            imageView = view.findViewById(R.id.shopping_list_item_image);
            linearLayout = view.findViewById(R.id.list_parent);
            aSwitch = view.findViewById(R.id.buy_switch);

        }
    }

    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleAdapter.MyViewHolder myViewHolder, final int i) {

        final ShoppingItem currentItem = items.get(i);

        myViewHolder.txtTitle.setText(currentItem.getName());
        myViewHolder.imageView.setImageResource(currentItem.getImage());
        if(currentItem.getSelected() != null){myViewHolder.aSwitch.setChecked(currentItem.getSelected());}
        myViewHolder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ShoppingListRepo.setCheckedStatus(currentItem.getId(), isChecked);
            }
        });
        myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bool = ShoppingListRepo.getCheckedStatus(currentItem.getId());
                if (bool){
                    myViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                    myViewHolder.aSwitch.setChecked(false);
                    ShoppingListRepo.setCheckedStatus(currentItem.getId(), false);


                }else {
                    myViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                    myViewHolder.aSwitch.setChecked(true);
                    ShoppingListRepo.setCheckedStatus(currentItem.getId(), true);



                }
            }
        });
    }

    public RecycleAdapter(ArrayList<ShoppingItem> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}