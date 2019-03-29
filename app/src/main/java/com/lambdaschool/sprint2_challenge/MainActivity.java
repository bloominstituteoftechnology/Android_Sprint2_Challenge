package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

public static final String TAG = "ShoppingActivity";
Context context;

    ShoppingListSharedPrefsRepo repo;
    RecyclerViewAdapter listAdapter;

    // string value for channel id
    public static String channelId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        repo = new ShoppingListSharedPrefsRepo(context);
        channelId = getPackageName() + ".shareList";

    }
}
