package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ArrayList<ShoppingItem> itemArrayList = new ArrayList<>();

    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ShoppingListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        itemArrayList = ShoppingListDao.getAllItems();

        recyclerView = findViewById(R.id.items_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ShoppingListAdapter(itemArrayList);
        recyclerView.setAdapter(listAdapter);
    }
}
