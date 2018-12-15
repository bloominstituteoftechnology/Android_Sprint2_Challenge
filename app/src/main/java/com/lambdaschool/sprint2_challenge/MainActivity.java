package com.lambdaschool.sprint2_challenge;

import android.app.Activity;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;

    ArrayList<ShoppingList> shoppingList;
    private Context         context;
    private Activity        activity;

    private RecyclerView            listView;
    private LinearLayoutManager     layoutManager;
    private ShoppingListAdapter     listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = this.getPreferences(Context.MODE_PRIVATE);

        context = this;
        activity = this;

        shoppingList = ShoppingListFactory.getGroceryDataList();

        findViewById(R.id.share_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBodyText = "Shopping list";
                intent.putExtra(Intent.EXTRA_TEXT, ShoppingListFactory.getSelectedItems());
                startActivity(Intent.createChooser(intent, "Pick sharing method"));
            }
        });

        listView = findViewById(R.id.list_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        listAdapter = new ShoppingListAdapter(shoppingList);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(listAdapter);

    }

    private void addNotification(){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "channel_id";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name        = "new channel";
            String       description = "Notifications triggered by our button";
            int          importance  = NotificationManager.IMPORTANCE_LOW;

            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setContentTitle("Shopping List sent")
                .setContentText("shopping list content")
                .setSmallIcon(R.drawable.ic_delete_black_24dp);
        notificationManager.notify(2, builder.build());
    }
}
