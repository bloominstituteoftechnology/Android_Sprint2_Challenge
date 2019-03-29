package com.lambdaschool.sprint2_challenge;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import static com.lambdaschool.sprint2_challenge.ShoppingItemConstants.ICON_IDS;
import static com.lambdaschool.sprint2_challenge.ShoppingItemConstants.ITEM_NAMES_RAW;

public class MainActivity extends AppCompatActivity {
    static final String CHANNEL_ID = "15154578";
    ArrayList<ShoppingItem> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;
    CartRepo cartRepo;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        cartRepo = new CartRepo(this);
        Button exportButton = findViewById(R.id.button_export_list);

        for (int i = 0; i < ICON_IDS.length; ++i) { // generate itemList, ready to be displayed
            itemList.add (new ShoppingItem(ITEM_NAMES_RAW[i],ICON_IDS[i],i));
        }

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(itemList);
        recyclerView.setAdapter(listAdapter);


        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = cartRepo.getCart(itemList);
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Shopping List:");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, temp);;

                //NOTIFICATION START
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.broccoli)
                        .setContentTitle("Shopping List")
                        .setContentText("Shopping List Successfully Exported!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                createNotificationChannel();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(1234, builder.build());
                //NOTIFICATION END

                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
            }
        });

    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notify Channel";
            String description = "Shopping App notify Stream";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
