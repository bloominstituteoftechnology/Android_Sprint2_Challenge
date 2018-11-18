package com.lambdaschool.sprint2_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;

    ArrayList<ShoppingList> shoppingList;
    private Context         context;
    private Activity        activity;

    private RecyclerView            listView;
    private LinearLayoutManager     layoutManager;
    private ShoppingListAdapter     listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = this.getPreferences(Context.MODE_PRIVATE);

        context = this;
        activity = this;

        shoppingList = ShoppingListFactory.getGroceryDataList();

        listView = findViewById(R.id.list_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        listAdapter = new ShoppingListAdapter(shoppingList);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(listAdapter);

    }
}
