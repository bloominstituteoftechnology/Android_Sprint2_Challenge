package com.lambdaschool.sprint2_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import static com.lambdaschool.sprint2_challenge.GroceryDao.getGroceryInv;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private GroceryListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        activity = this;

        recyclerView = findViewById(R.id.shopping_list_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        listAdapter = new GroceryListAdapter(GroceryDao.getGroceryInv(), activity);
        recyclerView.setAdapter(listAdapter);

        findViewById(R.id.select_item_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);

            }
        });



    }
}
