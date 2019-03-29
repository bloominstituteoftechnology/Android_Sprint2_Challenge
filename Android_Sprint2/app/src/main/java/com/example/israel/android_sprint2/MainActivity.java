package com.example.israel.android_sprint2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

        // send list button
        findViewById(R.id.button_send_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: REQUIRED: send notification

                // send message
                ArrayList<ShoppingItem> selectedShoppingItems = SelectedShoppingItemsSPDAO.getSelectedShoppingItems();
                StringBuilder message = new StringBuilder();
                message.append("Buy these items: ");
                String delimiter = ", ";
                for (ShoppingItem shoppingItem : selectedShoppingItems) {
                    message.append(shoppingItem.getName() + delimiter);
                }
                message.setLength(message.length() - delimiter.length()); // remove the last delimiter

                Intent messageIntent = new Intent(Intent.ACTION_SEND);
                messageIntent.setType("text/plain");
                messageIntent.putExtra(Intent.EXTRA_SUBJECT, "Shopping list");
                messageIntent.putExtra(Intent.EXTRA_TEXT, message.toString());
                startActivity(messageIntent);
            }
        });

    }
}
