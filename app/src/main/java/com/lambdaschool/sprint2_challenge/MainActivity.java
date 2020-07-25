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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShoppingListRepo repo = new ShoppingListRepo(this);

        preferences = getSharedPreferences(Constants.SHARED_FILE, Context.MODE_PRIVATE);
        items = ShoppingListRepo.getAllItems();
        context = this;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, ShoppingListRepo.sendSelectedItems());
                intent.setType("text/plain");
                startActivity(intent);

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
        });


            recyclerView = findViewById(R.id.shopping_recycler_view);
            adapter = new RecycleAdapter(ShoppingListRepo.getAllItems());

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecor);
            recyclerView.setAdapter(adapter);
            storeSelectedItems();


    }
    //TODO
    public static void storeSelectedItems(){
        ArrayList<ShoppingItem> storedItemList = ShoppingListRepo.getSelectedItems();
        SharedPreferences.Editor editor = preferences.edit();
        for(ShoppingItem item: storedItemList) {
            String itemIdString = String.valueOf(item.getId());
            editor.putString(itemIdString,item.toCsvString());
        } editor.apply();

   }




}