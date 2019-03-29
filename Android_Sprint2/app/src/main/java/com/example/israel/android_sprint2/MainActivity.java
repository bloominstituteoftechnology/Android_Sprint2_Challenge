package com.example.israel.android_sprint2;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ShoppingItem> shoppingItems;
    private ShoppingListAdapter shoppingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize shopping items
        shoppingItems = new ArrayList<>(ShoppingItemConstants.ICON_IDS.length);
        for (int i = 0; i < ShoppingItemConstants.ICON_IDS.length; ++i) {
            Drawable drawable = getResources().getDrawable(ShoppingItemConstants.ICON_IDS[i]);
            String name = ShoppingItemConstants.ITEM_NAMES_RAW[i];
            ShoppingItem newShoppingItem = new ShoppingItem(drawable, name, i);
            shoppingItems.add(newShoppingItem);
        }

        // TODO: IRRELEVANT: sort shoppingItems

        // TODO: select saved selected shopping items

        // set up recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_shopping_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        shoppingListAdapter = new ShoppingListAdapter(this, shoppingItems);
        recyclerView.setAdapter(shoppingListAdapter);

    }
}
