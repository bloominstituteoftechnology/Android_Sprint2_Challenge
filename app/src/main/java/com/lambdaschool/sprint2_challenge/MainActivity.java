package com.lambdaschool.sprint2_challenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ShoppingItem> shoppingItemArrayList = ShoppingItemFactory.getShoppingItems();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ShoppingListAdapter shoppingListAdapter = new ShoppingListAdapter(shoppingItemArrayList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shoppingListAdapter);
        recyclerView.setHasFixedSize(true);
        //shoppingListAdapter.notifyDataSetChanged();
    }
}
