package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    Context context;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ItemDao dao = new ItemDao();
        prefs = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        editor = prefs.edit();

    }
}
