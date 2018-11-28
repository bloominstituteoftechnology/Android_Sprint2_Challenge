package com.lambdaschool.sprint2_challenge;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int REQUEST_CODE = 2;
    ArrayList<ShoppingItem> items;
    RecyclerView recyclerView;
    RecycleAdapter adapter;
    NotificationManager notificationManager;
    public static SharedPreferences preferences;

    Context context;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            String channelId = getPackageName();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "Channel";
                String description = "Notifications";
                int tag = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel = new NotificationChannel(channelId, name, tag);
                channel.setDescription(description);

                notificationManager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                builder = new NotificationCompat.Builder(context, channelId)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setContentTitle("Shopping List")
                        .setContentText("Shopping list.")
                        .setColor(context.getColor(R.color.colorPrimary))
                        .setSmallIcon(android.R.drawable.ic_dialog_alert);
            }
            assert builder != null;
            notificationManager.notify(1, builder.build());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(Constants.SHARED_FILE, Context.MODE_PRIVATE);
        items = ShoppingList.getAllItems();
        context = this;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        findViewById(R.id.buttonShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strList = "";
                items = ShoppingList.getSelectedItems();

                for (int i = 0; i < items.size(); i++) {
                    strList += items.get(i).toString();
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, strList);
                startActivityForResult(intent, REQUEST_CODE);

                Log.i(TAG, "CHRLES " + items.size());
            }
        });


        recyclerView = findViewById(R.id.recycleViewer);
        adapter = new RecycleAdapter(ShoppingList.getAllItems());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
        recyclerView.setAdapter(adapter);


    }
}
