package com.lambdaschool.sprint2_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;

    private RecyclerView listRecyclerView;
    private ShoppingListAdapter listAdapter;
    private LinearLayoutManager layoutManager;
    private String[] list;

    private Context context;
    private Activity activity;

    private Button sendListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences("prefs", Context.MODE_PRIVATE);

        context = this;
        activity = this;

        listRecyclerView = findViewById(R.id.list_view);
        listRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        listRecyclerView.setLayoutManager(layoutManager);
        listAdapter = new ShoppingListAdapter(ShoppingListModel.getAllItems(), activity);
        listRecyclerView.setAdapter(listAdapter);

        sendListButton = findViewById(R.id.send_list_button);
        sendListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, ShoppingListModel.getItemsSelectedName());
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "sending"));
            }
        });
    }
}
