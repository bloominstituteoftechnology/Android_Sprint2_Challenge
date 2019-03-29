package com.lambdaschool.sprint2_challenge;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ShoppingListAdapter shoppingListAdapter;
    Button sendButton;
    Context context;
    static SharedPreferences preferences;

    static final int SHOPPING_NOTIFICATION_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        preferences = getSharedPreferences("Shopping Preferences", Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        shoppingListAdapter = new ShoppingListAdapter(ShoppingListRepository.getShoppingItems());
        recyclerView.setAdapter(shoppingListAdapter);

        sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendList();
            }
        });
    }

    public void sendList() {

        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "SL Channel";
            String description = "This channel is used to share shopping lists";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(getPackageName(), name, importance);
            channel.setDescription(description);

            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, getPackageName())
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setContentTitle("Shopping")
                .setContentText("Shopping List Sent!")
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setSmallIcon(R.drawable.notification_icon)
                .setDefaults(Notification.DEFAULT_ALL);

        notificationManager.notify(MainActivity.SHOPPING_NOTIFICATION_ID, builder.build());

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        String shoppingList = ShoppingListRepository.createShoppingList();
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shoppingList);
        startActivity(intent);

    }
}