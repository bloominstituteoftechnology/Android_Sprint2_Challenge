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

import java.util.ArrayList;

public class FoodDataListAdapter extends RecyclerView.Adapter<FoodDataListAdapter.ViewHolder> {
    private static ArrayList<String> checkArray = new ArrayList<String>();

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ViewGroup parentLayout;

        ImageView idView;
        TextView nameView;





        ViewHolder(View view) {
            super(view);



            parentLayout = view.findViewById(R.id.food_parent_layout);
            nameView = view.findViewById(R.id.food_name_text);
            idView = view.findViewById(R.id.food_name_id);


        }

        public void setListeners() {
            nameView.setOnClickListener(ViewHolder.this);
    }

        @Override
        public void onClick(View v) {


            if (foodHighlighted) {

                nameView.setBackgroundColor(Color.TRANSPARENT);
                foodHighlighted = false;

                removeItemToArray();

            } else {
                nameView.setBackgroundColor(Color.MAGENTA);
                foodHighlighted = true;
                addItemToArray();
            }

        }
    }

    public static void addItemToArray() {

        checkArray.add(title);
        Log.d("caz", "Items added to array: " + checkArray.toString());
    }

    public static void removeItemToArray() {

        if(checkArray != null) {
            checkArray.remove(title);
            Log.d("caz", "Current array after item removal: " + checkArray.toString());
        }
    }




    private ArrayList<FoodData> dataList;
    private Context context;


    private int position;
    private static String title;

    private static boolean foodHighlighted;


    private FoodData currentData;



    FoodDataListAdapter(ArrayList<FoodData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_data_list_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        FoodData data = dataList.get(position);

        this.position = position;
        currentData = data;

        viewHolder.nameView.setText(data.getFoodName());
        viewHolder.idView.setImageResource(data.getFoodId());

        title = data.getFoodName();
        foodHighlighted = data.isCheckBox();


        viewHolder.setListeners();

    }

    @Override
    public int getItemCount() {
        return dataList.size();


        //-------------------
    }

    public static String toCsvString() {

        String csvString = String.join(",", checkArray);
        Log.d("caz", "Array to CSV String " + csvString);

        return csvString;
    }

}





//
//
//import android.content.Context;
//import android.graphics.Color;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class FoodDataListAdapter extends RecyclerView.Adapter<FoodDataListAdapter.ViewHolder> {
//    private static ArrayList<String> checkArray = new ArrayList<String>();
//
//    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//
//        ViewGroup parentLayout;
//
//        ImageView idView;
//        TextView nameView;
//
//
//
//
//
//        ViewHolder(View view) {
//            super(view);
//
//
//
//            parentLayout = view.findViewById(R.id.food_parent_layout);
//            nameView = view.findViewById(R.id.food_name_text);
//            idView = view.findViewById(R.id.food_name_id);
//
////            nameView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    nameView.setBackgroundColor(0);
////                    Log.d("caz", "Name clicked : " + getAdapterPosition());
////                }
////            });
//        }
//
//        public void setListeners() {
//            nameView.setOnClickListener(ViewHolder.this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.food_name_text:
//
//                    nameView.setBackgroundColor(Color.MAGENTA);
//
//                    addItemToArray();
//
//                    // stop
//                    break;
//
//                //don't forget next case for switch statement here
//            }
//
//        }
//    }
//
//    public static void addItemToArray() {
//
//        checkArray.add(title);
//        Log.d("caz", "Items added to array: " + checkArray.toString());
//    }
//
//
//
//
//    private ArrayList<FoodData> dataList;
//    private Context context;
//
//
//    private int position;
//    private static String title;
//
//
//    private FoodData currentData;
//
//
//
//    FoodDataListAdapter(ArrayList<FoodData> dataList) {
//        this.dataList = dataList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        context = viewGroup.getContext();
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_data_list_layout, viewGroup, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
//        FoodData data = dataList.get(position);
//
//        this.position = position;
//        currentData = data;
//
//        viewHolder.nameView.setText(data.getFoodName());
//        viewHolder.idView.setImageResource(data.getFoodId());
//
//        title = data.getFoodName();
//
//
//        viewHolder.setListeners();
//
//
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////            viewHolder.parentLayout.setBackgroundColor(context.getColor(data.getColorId()));
////        } else {
////            viewHolder.parentLayout.setBackgroundColor(context.getResources().getColor(data.getColorId()));
////        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//
//
//        //-------------------
//    }
//
//    public static String toCsvString() {
//
//        String csvString = String.join(",", checkArray);
//        Log.d("caz", "Array to CSV String " + csvString);
//
//        return csvString;
//    }
//
//}
