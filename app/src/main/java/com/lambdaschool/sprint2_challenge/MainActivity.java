package com.lambdaschool.sprint2_challenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

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
                foodListIntent.putExtra(Intent.EXTRA_TEXT, FoodDataListAdapter.toCsvString());
                foodListIntent.setType("text/plain");
                startActivity(foodListIntent);

            }
        });
    }
}
