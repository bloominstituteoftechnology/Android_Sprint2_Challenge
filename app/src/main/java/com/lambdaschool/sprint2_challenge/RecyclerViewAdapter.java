package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> selectedItems = new ArrayList<>();
    private Context context;
    private static boolean needsChecking;

    public RecyclerViewAdapter(ArrayList<Item> items, ArrayList<Item> selectedItems, Context context) {
        this.items = items;
        this.selectedItems = selectedItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int pos) {
        holder.itemIcon.setImageResource(items.get(pos).getImageID());
        holder.itemName.setText(items.get(pos).getName());

/*        if(items.get(pos).isSelected()){
            holder.backgroundImage.setImageResource(R.color.colorPrimary);
            items.get(pos).setSelected(true);
        } else{
            holder.backgroundImage.setImageResource(R.color.colorAccent);
        }*/

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(items.get(pos).isSelected() == true){
                    items.get(pos).setSelected(false);
                }else {
                    items.get(pos).setSelected(true);
                }
                ItemDao.updateSelected();
                ItemDao.saveSelectedItems();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemIcon;
        TextView itemName;
        CardView parentLayout;
        ImageView backgroundImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name_view);
            itemIcon = itemView.findViewById(R.id.item_icon_view);
            parentLayout = itemView.findViewById(R.id.list_parent);
        }
    }
}

