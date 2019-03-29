package com.lambdaschool.sprint2_challenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button sendListButton;
    private ShoppingListAdapter listAdapter;
    private ArrayList<ShoppingItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();

        sendListButton = findViewById(R.id.send_list_button);
        sendListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendListIntent = new Intent(Intent.ACTION_SEND);
                sendListIntent.setType("text/plain");
                startActivity(sendListIntent);
            }
        });

        listAdapter = new ShoppingListAdapter(itemList);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(listAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

    }
}
