package com.example.israel.android_sprint2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_ID_SEND_LIST = "id:0";
    public static final String NOTIFICATION_CHANNEL_NAME_SEND_LIST = "name:0";
    private static int sendListNotificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize shared prefs DAO
        SelectedShoppingItemsSPDAO.init(this);

        setupRecyclerView();

        // send list button
        findViewById(R.id.button_send_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
                sendMessage();
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_shopping_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ShoppingListAdapter shoppingListAdapter = new ShoppingListAdapter(
                this,
                new ArrayList<>(Arrays.asList(ShoppingItemRepository.getShoppingItems())) // COPY the repository
        );
        recyclerView.setAdapter(shoppingListAdapter);
    }

    private void sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // channel
            NotificationManager notificationManager = (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID_SEND_LIST, NOTIFICATION_CHANNEL_NAME_SEND_LIST, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Notification for sending shopping list");
            notificationManager.createNotificationChannel(channel);

            // builder
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, NOTIFICATION_CHANNEL_ID_SEND_LIST)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentTitle("Your order has been placed")
                    //.setContentText("text: Example text")
                    .setSmallIcon(R.drawable.ic_shopping_cart_green_24dp)
                    .setColor(Color.argb(255, 0, 255, 0))
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

            // increment id, so that it will show up in the heads-up notification again
            notificationManager.notify(sendListNotificationId++, builder.build());
        }
    }

    private void sendMessage() {
        ArrayList<ShoppingItem> selectedShoppingItems = SelectedShoppingItemsSPDAO.getSelectedShoppingItems();
        StringBuilder message = new StringBuilder();
        message.append("Buy these items: ");
        String delimiter = ", ";
        for (ShoppingItem shoppingItem : selectedShoppingItems) {
            message.append(shoppingItem.getName() + delimiter);
        }
        message.setLength(message.length() - delimiter.length()); // remove the last delimiter

        Intent messageIntent = new Intent(Intent.ACTION_SEND);
        messageIntent.setType("text/plain");
        messageIntent.putExtra(Intent.EXTRA_SUBJECT, "Shopping list");
        messageIntent.putExtra(Intent.EXTRA_TEXT, message.toString());
        startActivity(messageIntent);
    }
}
