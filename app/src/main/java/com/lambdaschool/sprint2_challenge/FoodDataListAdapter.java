package com.lambdaschool.sprint2_challenge;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FoodDataListAdapter extends RecyclerView.Adapter<FoodDataListAdapter.ViewHolder> {

    RecyclerView recyclerV;

    public static void setSelectedIndex(List<Integer> selectedIndices) {
        FoodDataListAdapter.selectedIndex = selectedIndices;
    }

    static List<FoodData> selectedFood = new ArrayList<FoodData>();
    static List<Integer> selectedIndex = new ArrayList<Integer>();

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerV = recyclerView;
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            int itemPosition = recyclerV.getChildAdapterPosition(view);
            FoodData data = dataList.get(itemPosition);
            data.setCheckBox(!data.isCheckBox());
            Toast.makeText(context, data.getFoodName().toString(), Toast.LENGTH_SHORT).show();
            TextView nameView = view.findViewById(R.id.food_name_text);

            if(data.isCheckBox()){
                selectedFood.add(data);
                selectedIndex.add(new Integer(itemPosition));
                nameView.setBackgroundColor(Color.RED);
            }else{
                selectedIndex.remove(new Integer(itemPosition));
                selectedFood.remove(data);
                nameView.setBackgroundColor(Color.TRANSPARENT);
            }
            Log.d("Food", selectedFood.toString());
        }
    }

    public static List<Integer> getSelectedIndex(){
        return selectedIndex;
    }

    public String getSelectedFood() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< selectedIndex.size(); ++i){
            sb.append(dataList.get(selectedIndex.get(i)).getFoodName());
            sb.append(",");
        }
        return sb.toString();
    }

    private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    static class ViewHolder extends RecyclerView.ViewHolder{
        ViewGroup parentLayout;
        ImageView idView;
        TextView nameView;

        ViewHolder(View view) {
            super(view);
            parentLayout = view.findViewById(R.id.food_parent_layout);
            nameView = view.findViewById(R.id.food_name_text);
            idView = view.findViewById(R.id.food_name_id);
        }
    }
    private ArrayList<FoodData> dataList;
    private Context context;
    FoodDataListAdapter(ArrayList<FoodData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_data_list_layout, viewGroup, false);
        view.setOnClickListener(mOnClickListener);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        FoodData data = dataList.get(position);
        viewHolder.nameView.setText(data.getFoodName());
        viewHolder.idView.setImageResource(data.getFoodId());
        if(data.isCheckBox()){
            viewHolder.nameView.setBackgroundColor(Color.RED);
        }else{
            viewHolder.nameView.setBackgroundColor(Color.TRANSPARENT);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}