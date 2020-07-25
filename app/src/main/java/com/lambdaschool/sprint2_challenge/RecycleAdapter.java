package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context context;

    ArrayList<ShoppingItem> items;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public ImageView imageView;
        public LinearLayout linearLayout;
        public Switch aSwitch;


        public MyViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.textViewItemName);
            imageView = view.findViewById(R.id.imageViewItem);
            linearLayout = view.findViewById(R.id.parentLayout);
            aSwitch = view.findViewById(R.id.switch1);

        }
    }

    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleAdapter.MyViewHolder myViewHolder, final int i) {

        final ShoppingItem currentItem = items.get(i);

        myViewHolder.txtTitle.setText(currentItem.getName());
        myViewHolder.imageView.setImageResource(currentItem.getImage());
        myViewHolder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ShoppingList.setCheckedStatus(currentItem.getId(), isChecked);
            }
        });
        myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bool = ShoppingList.getCheckedStatus(currentItem.getId());
                if (bool){
                    myViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                    myViewHolder.aSwitch.setChecked(false);
                    ShoppingList.setCheckedStatus(currentItem.getId(), false);

                    
                }else {
                    myViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                    myViewHolder.aSwitch.setChecked(true);
                    ShoppingList.setCheckedStatus(currentItem.getId(), true);



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
