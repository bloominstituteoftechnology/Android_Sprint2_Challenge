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
    public static ShoppingListRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        repo = new ShoppingListRepo(context);
        itemList = ShoppingItemConstants.createItemList();

        itemsSelected = repo.getShoppingList();

/*        if(repo.getShoppingList() != null) {
            itemsSelected = repo.getShoppingList();
        }else{
            itemsSelected = new ArrayList<>();
        }*/
        for(ShoppingItem item: itemList){
            if(itemsSelected.contains(item.getName())){
                item.setSelected(true);
            }
        }

        listAdapter = new ShoppingItemListAdapter(itemList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        repo.updateShoppingList(itemsSelected);
    }
}
