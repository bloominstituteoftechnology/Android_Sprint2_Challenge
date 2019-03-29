package com.lambdaschool.sprint2_challenge;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Context context;
    public static SharedPreferences prefs;
    public static int REQUEST_CODE = 42;
    public static SharedPreferences.Editor editor;
    private static final String TAG = "MainActivity";
    Button sendButton;
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendButton = findViewById(R.id.send_button);
        prefs = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        editor = prefs.edit();
        context = this;

        clearPrefs();

        ItemDao dao = new ItemDao();
        ItemDao.updateSelected();
        initRecyclerView();


                sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDao.updateSelected();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, ItemDao.getHumanReadableStringFromItemArrList(ItemDao.getSelectedItems()));
                startActivity(Intent.createChooser(intent,"send your list"));
            }
        });

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(ItemDao.getAllItems(),ItemDao.getSelectedItems(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ItemDao.updateSelected();
    }

    public static void clearPrefs(){
        editor.putString("selected_items",  "");
        editor.putInt("selected_items_size", 0);
        editor.apply();
    }

}
