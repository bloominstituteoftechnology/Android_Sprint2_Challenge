package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    public static ArrayList<String> itemsSelected;
    static ArrayList<ShoppingItem> itemList;
    ShoppingItemListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsSelected = new ArrayList<>();
        itemList = ShoppingItemConstants.createItemList();
        context = this;
        listAdapter = new ShoppingItemListAdapter(itemList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
