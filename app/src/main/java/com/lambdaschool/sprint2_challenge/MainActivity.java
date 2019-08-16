package com.lambdaschool.sprint2_challenge;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int INTENT_REQUEST_CODE = 14;
    public static final int NOTIFICATION_ID = 923;
    public static Context context;
    public static ArrayList<String> itemsSelected;
    static ArrayList<ShoppingItem> itemList;
    ShoppingItemListAdapter listAdapter;
    public static ShoppingListRepo repo;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        buttonSubmit = findViewById(R.id.button_submit);
        repo = new ShoppingListRepo(context);
        itemList = ShoppingItemConstants.createItemList();

        itemsSelected = repo.getShoppingList();

/*        if(repo.getShoppingList() != null) {
            itemsSelected = repo.getShoppingList();
        }else{
            itemsSelected = new ArrayList<>();
        }*/
        for(ShoppingItem item: itemList){
            if(itemsSelected.contains(item.getName())){
                item.setSelected(true);
            }
        }

        listAdapter = new ShoppingItemListAdapter(itemList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(layoutManager);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareList = new Intent(Intent.ACTION_SEND);
                shareList.putExtra("Shopping List", repo.toCSV(itemsSelected));
                shareList.setType("text/plain");
                startActivityForResult(shareList, INTENT_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        repo.updateShoppingList(itemsSelected);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == 0 && requestCode == INTENT_REQUEST_CODE){
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            String channelId = getPackageName() + "button";
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                CharSequence name = "Notifications";
                String description = "These are the notifications we send you";
                int importance = NotificationManager.IMPORTANCE_HIGH;

                NotificationChannel channel = new NotificationChannel(channelId, name, importance);
                channel.setDescription(description);

                manager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                    .setContentTitle("Shopping List")
                    .setContentText("You have submitted your shopping list!")
                    .setSmallIcon(R.drawable.apple_red)
                    .setPriority(NotificationManager.IMPORTANCE_HIGH);

            manager.notify(NOTIFICATION_ID, builder.build());
        }

    }
}
