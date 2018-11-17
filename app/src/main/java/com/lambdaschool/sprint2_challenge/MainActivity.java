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
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 0;
    public static SharedPreferences preferences;

    NotificationManager notificationManager;

    private RecyclerView listRecyclerView;
    private ShoppingListAdapter listAdapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    private Button sendListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences(getString(R.string.shared_prefs_name), Context.MODE_PRIVATE);
        context = this;

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        listRecyclerView = findViewById(R.id.list_view);
        listRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        listRecyclerView.setLayoutManager(layoutManager);
        listAdapter = new ShoppingListAdapter(ShoppingListModel.getAllItems());
        listRecyclerView.setAdapter(listAdapter);

        sendListButton = findViewById(R.id.send_list_button);
        sendListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, ShoppingListModel.getItemsSelectedName());
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_intent_title)));

               String channelId = getPackageName() + getString(R.string.channel_id_suffix);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    CharSequence name = getString(R.string.channel_name);
                    String description = getString(R.string.channel_description);
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel channel = new NotificationChannel(channelId, name, importance);
                    channel.setDescription(description);
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setSmallIcon(R.drawable.ic_list_black_24dp)
                        .setContentTitle(getString(R.string.notification_order_placed_title))
                        .setContentText(getString(R.string.notification_order_placed_content))
                        .setColor(getResources().getColor(R.color.colorAccent))
                        .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                        .setAutoCancel(true);
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }
        });
    }
}
