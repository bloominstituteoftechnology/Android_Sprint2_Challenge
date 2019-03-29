package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ShoppingListAdapter shoppingListAdapter;
    Context context;
    static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        preferences = getSharedPreferences("Shopping Preferences", Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        shoppingListAdapter = new ShoppingListAdapter(ShoppingListRepository.getShoppingItems());
        recyclerView.setAdapter(shoppingListAdapter);

        System.out.println(ShoppingListRepository.createShoppingList());
    }

    public void sendList() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        String shoppingList = ShoppingListRepository.createShoppingList();
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shoppingList);
        startActivityForResult(intent, 1);
    }


}
