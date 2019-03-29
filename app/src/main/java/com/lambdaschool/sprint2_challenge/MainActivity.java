package com.lambdaschool.sprint2_challenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import static com.lambdaschool.sprint2_challenge.ShoppingItemConstants.ICON_IDS;
import static com.lambdaschool.sprint2_challenge.ShoppingItemConstants.ITEM_NAMES_RAW;

public class MainActivity extends AppCompatActivity {
    ArrayList<ShoppingItem> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;
    CartRepo cartRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cartRepo = new CartRepo(this);
        Button exportButton = findViewById(R.id.button_export_list);

        for (int i = 0; i < ICON_IDS.length; ++i) { // generate itemList, ready to be displayed
            itemList.add (new ShoppingItem(ITEM_NAMES_RAW[i],ICON_IDS[i],i));
        }

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(itemList);
        recyclerView.setAdapter(listAdapter);


        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = cartRepo.getCart(itemList);
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Shopping List:");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, temp);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
            }
        });

    }
}
