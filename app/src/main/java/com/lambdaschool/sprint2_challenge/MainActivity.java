package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ArrayList<ShoppingItem> itemArrayList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ShoppingListAdapter listAdapter;
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        context = this;

        preferences = this.getPreferences(Context.MODE_PRIVATE);


        context = this;
        itemArrayList = ShoppingListDao.getAllItems();

        recyclerView = findViewById(R.id.items_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ShoppingListAdapter(itemArrayList);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*        if (id == R.id.action_settings) {
            return true;
        }*/

        if (id == R.id.action_share) {
            ArrayList<String> checkedList = ShoppingListDao.getCheckedBookNames();
            String sendContent = "Shopping List: ";
            for (int i=0;i<checkedList.size();++i) {
                if (i<checkedList.size()-1) {
                    sendContent +=(checkedList.get(i) + ", ");
                } else {
                    sendContent +=(checkedList.get(i) + ".");
                }
            }
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, sendContent );
            startActivity(Intent.createChooser(intent, "Share via"));
            return true;
        }


        if (id == R.id.action_reset) {
           ShoppingListDao.resetAll();
            Intent intent = new Intent(context,MainActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
