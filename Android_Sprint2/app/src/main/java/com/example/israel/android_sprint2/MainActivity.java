package com.example.israel.android_sprint2;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ShoppingListAdapter shoppingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize shared prefs DAO
        SelectedShoppingItemsSPDAO.init(this);

        // set up recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_shopping_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        shoppingListAdapter = new ShoppingListAdapter(
                this,
                new ArrayList<>(Arrays.asList(ShoppingItemRepository.getShoppingItems())) // COPY the repository
        );
        recyclerView.setAdapter(shoppingListAdapter);

    }
}
