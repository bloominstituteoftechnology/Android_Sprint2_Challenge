package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private static final String TAG = MainActivity.class.getSimpleName();
    ArrayList<ShoppingItem> items;
    RecyclerView recyclerView;
    RecycleAdapter adapter;
    public static SharedPreferences preferences;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(Constants.SHARED_FILE, Context.MODE_PRIVATE);

        findViewById(R.id.buttonShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_VIEW);
            items = new ArrayList<>(ShoppingList.getSelectedItems());
                Intent intent = new Intent(Intent.ACTION_SEND);
                for (ShoppingItem item:items) {
                    intent.putExtra("item_name" + String.valueOf(item.getId()), item.getName());
                }
                intent.setType("text/plain");
                startActivity(intent);

                Log.i(TAG, "CHRLES " + items.size());
            }
        });


        recyclerView = findViewById(R.id.recycleViewer);
        adapter = new RecycleAdapter(ShoppingList.getAllItems());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
        recyclerView.setAdapter(adapter);


    }
}
