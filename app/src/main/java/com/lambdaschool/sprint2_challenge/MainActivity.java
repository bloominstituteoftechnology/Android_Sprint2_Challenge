package com.lambdaschool.sprint2_challenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<ShoppingItem> items;
    RecyclerView recyclerView;
    RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ShoppingItem> items = new ArrayList<>(ShoppingItemConstants.ITEM_NAMES_RAW.length);
        String name;
        int id, imageId;
        for (int i = 0; i < ShoppingItemConstants.ITEM_NAMES_RAW.length; i++) {
            id = i;
            name = ShoppingItemConstants.ITEM_NAMES_RAW[i];
            imageId = ShoppingItemConstants.ICON_IDS[i];
            items.add(new ShoppingItem(name, imageId, id));

            recyclerView = findViewById(R.id.recycleViewer);
            adapter = new RecycleAdapter(items);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecor);
            recyclerView.setAdapter(adapter);


        }
    }
}
