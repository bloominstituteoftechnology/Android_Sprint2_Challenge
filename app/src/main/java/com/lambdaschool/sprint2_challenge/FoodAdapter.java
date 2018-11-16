package com.lambdaschool.sprint2_challenge;


import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewGroup parentLayout;
        TextView nameView, idView;

        ViewHolder(View view) {
            super(view);

            parentLayout = view.findViewById(R.id.food_parent_layout);
            nameView = view.findViewById(R.id.food_name_text);
            idView = view.findViewById(R.id.food_name_id);
        }
    }

    private ArrayList<FoodData> dataList;
    private Context              context;

    FoodAdapter(ArrayList<FoodData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_data_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FoodData data = dataList.get(i);

        viewHolder.nameView.setText(data.getFoodName());
        viewHolder.idView.setText(Integer.toString(data.getFoodId()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            viewHolder.parentLayout.setBackgroundColor(context.getColor(data.getFoodId()));
        } else {
            viewHolder.parentLayout.setBackgroundColor(context.getResources().getColor(data.getFoodId()));
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
