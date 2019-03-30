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
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button sendListButton;
    private ShoppingListAdapter listAdapter;
    public static ArrayList<ShoppingItem> itemList;
    public static ArrayList<ShoppingItem> itemsSelected;
    public static SharedPreferences preferences;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        preferences = getSharedPreferences(Constants.PACKAGE, Context.MODE_PRIVATE);

        itemList = ShoppingList.getItemList();

        sendListButton = findViewById(R.id.send_list_button);
        sendListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendListIntent = new Intent(Intent.ACTION_SEND);
                sendListIntent.putExtra(Intent.EXTRA_TEXT, ShoppingList.selectedItemsToString(ShoppingList.getSelectedItems()));
                sendListIntent.setType("text/plain");
                startActivity(sendListIntent);

                String channelId = getPackageName();
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    CharSequence name = "Channel";
                    String description = "Sent shopping list";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel(channelId, name, importance);
                    channel.setDescription(description);
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                        .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                        .setSmallIcon(R.drawable.ic_android_green_24dp)
                        .setContentTitle(getString(R.string.order_placed))
                        .setContentText(getString(R.string.shopping_order))
                        .setColor(getResources().getColor(R.color.colorAccent))
                        .setDefaults(NotificationCompat.DEFAULT_ALL);
                notificationManager.notify(Constants.NOTIFICATION_ID, builder.build());
            }
        });

        listAdapter = new ShoppingListAdapter(itemList);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }
}
