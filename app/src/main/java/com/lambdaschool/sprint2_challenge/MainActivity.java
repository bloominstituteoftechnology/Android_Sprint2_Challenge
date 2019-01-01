package com.lambdaschool.sprint2_challenge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

// Charles Godoy
// AND1 - Sprint 2

public class MainActivity extends AppCompatActivity {

    ArrayList<FoodData> foodDataList;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FoodDataListAdapter listAdapter;
    Button sendListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodDataList = FoodDataFactory.getFoodDataList();
        loadList(foodDataList);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        listAdapter = new FoodDataListAdapter(foodDataList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);

        sendListButton = findViewById(R.id.send_button);

        sendListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FoodDataListAdapter.toCsvString();
                Intent foodListIntent = new Intent();
                foodListIntent.setAction(Intent.ACTION_SEND);
                foodListIntent.putExtra(Intent.EXTRA_TEXT, listAdapter.getSelectedFood().toString());
                foodListIntent.setType("text/plain");
                dumpList(FoodDataListAdapter.getSelectedIndex());
                startActivity(foodListIntent);

            }
        });
    }
    public void loadList(List<FoodData> foodDataList){

        SharedPreferences sharedPreferences = getSharedPreferences("selectedfoods", MODE_PRIVATE);
        String values = sharedPreferences.getString("selected", "404");
        List<Integer> selectedIndices = new ArrayList<>();
        if(values.equals("404")){
            return;
        }else{
            String []foods = values.split(",");
            for(int i=0; i<foods.length; ++i){
                FoodData data = null;
                try{
                    data = foodDataList.get(Integer.parseInt(foods[i]));
                }catch (NumberFormatException nfe){
                    nfe.printStackTrace();
                    foodDataList = FoodDataFactory.getFoodDataList();
                    return;
                }

                data.setCheckBox(true);
                selectedIndices.add(Integer.parseInt(foods[i]));
            }
            FoodDataListAdapter.setSelectedIndex(selectedIndices);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
    }
    private void dumpList(List<Integer> indices){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indices.size(); i++) {
            sb.append(indices.get(i)).append(",");
        }
        SharedPreferences sharedPreferences = getSharedPreferences("selectedfoods", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selected", sb.toString());
        Log.d("SB", sb.toString());
        editor.commit();
    }
}










//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.Button;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//
//    ArrayList<FoodData> foodDataList;
//
//    RecyclerView recyclerView;
//    LinearLayoutManager layoutManager;
//    FoodDataListAdapter listAdapter;
//
//    Button sendListButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        foodDataList = FoodDataFactory.getFoodDataList();
//
//
//        recyclerView = findViewById(R.id.recycler_view);
//        layoutManager = new LinearLayoutManager(this);
//        listAdapter = new FoodDataListAdapter(foodDataList);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(listAdapter);
//
//        sendListButton = findViewById(R.id.send_button);
//
//        sendListButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                FoodDataListAdapter.toCsvString();
//
//                Intent foodListIntent = new Intent();
//                foodListIntent.setAction(Intent.ACTION_SEND);
//                foodListIntent.putExtra(Intent.EXTRA_TEXT, FoodDataListAdapter.toCsvString());
//                foodListIntent.setType("text/plain");
//                startActivity(foodListIntent);
//
//            }
//        });
//    }
//}
