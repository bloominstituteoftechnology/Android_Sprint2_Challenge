package com.lambdaschool.sprint2_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GroceryListAdapter listAdapter;

    private ArrayList<Grocery> groceryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;


        findViewById(R.id.select_item_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerView = findViewById(R.id.shopping_list_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        groceryList = GroceryDao.getGroceryInv();

        listAdapter = new GroceryListAdapter(groceryList);
        recyclerView.setAdapter(listAdapter);



    }
}
