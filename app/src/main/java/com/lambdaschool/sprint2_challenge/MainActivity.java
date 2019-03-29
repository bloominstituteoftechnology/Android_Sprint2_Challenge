package com.lambdaschool.sprint2_challenge;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ShoppingItem> shoppingItemArrayList;
    private ViewGroup parentLayout;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLayout = findViewById(R.id.constraint_layout_parent);
        imageView = findViewById(R.id.image_view_icon);
        textView = findViewById(R.id.text_view_name);
        ShoppingCart.initializeSharedPreferences(this);
        shoppingItemArrayList = ShoppingItemFactory.getShoppingItems();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ShoppingListAdapter shoppingListAdapter = new ShoppingListAdapter(shoppingItemArrayList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shoppingListAdapter);
        recyclerView.setHasFixedSize(true);
        //recyclerView.findViewHolderForAdapterPosition(8).itemView.performClick();
        toggleBulkItems(ShoppingCart.getSharedPreferences());

        final NotificationManager notifMgr = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Button buttonShare = findViewById(R.id.button_share_list);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, ShoppingCart.getShoppingCart());
                intent.setType("text/plain");
                startActivity(intent);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Intent intent2 = new Intent(v.getContext(), MainActivity.class);
                    intent2.putExtra("notification", "You've been shopping");
                    PendingIntent pendingIntent = PendingIntent.getActivity(v.getContext(), 0, intent2, PendingIntent.FLAG_ONE_SHOT);

                    NotificationChannel notifChannel = new NotificationChannel(getPackageName() + ".shopping", "Basil's Channel", NotificationManager.IMPORTANCE_HIGH);
                    notifChannel.setDescription("This is the shopping channel");
                    notifMgr.createNotificationChannel(notifChannel);
                    NotificationCompat.Builder notifCompatBuilder = new NotificationCompat.Builder(v.getContext(), getPackageName() + ".shopping");
                    notifCompatBuilder.setPriority(NotificationManager.IMPORTANCE_HIGH);
                    notifCompatBuilder.setContentIntent(pendingIntent);
                    notifCompatBuilder.setContentTitle("Basil's Channel");
                    notifCompatBuilder.setContentText("You've shared your shopping cart!");
                    notifCompatBuilder.setSmallIcon(R.drawable.ic_cake_black_24dp);
                    notifCompatBuilder.setColor(Color.BLUE);
                    notifCompatBuilder.setDefaults(Notification.DEFAULT_SOUND);
                    notifMgr.notify(7896575, notifCompatBuilder.build());
                }
            }
        });
    }


    public static void toggleBulkItems(String shoppingItemsAddedToCart) {

        if (shoppingItemsAddedToCart != null && !shoppingItemsAddedToCart.equals("")) {
            String[] shoppingItemIds = shoppingItemsAddedToCart.split(",");
            ArrayList<String> convertedStringArray = new ArrayList<>(Arrays.asList(shoppingItemIds));
            for (int i = 0; i < shoppingItemArrayList.size(); i++) {
                ShoppingItem itemToCheck = shoppingItemArrayList.get(i);
                if (convertedStringArray.contains(Integer.toString(itemToCheck.getShoppingItemId()))) {
                    shoppingItemArrayList.get(i).setShoppingItemInCart(true);
                }
            }
        }
    }

/*    private static void toggleIndividualItems(view) {
        int color;
        if (ShoppingCart.isItemInTheShoppingCart(Integer.toString(item.getShoppingItemId()))) {
            ShoppingCart.removeItemFromShoppingCart(Integer.toString(item.getShoppingItemId()));
            //viewHolder.switchToggle.setChecked(false);
            color = R.color.cardview_light_background;
        } else {
            ShoppingCart.addItemToShoppingCart(Integer.toString(item.getShoppingItemId()));
            //viewHolder.switchToggle.setChecked(true);
            color = R.color.colorAccent;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            viewHolder.parentLayout.setBackgroundColor(context.getColor(color));
        else
            viewHolder.parentLayout.setBackgroundColor(context.getResources().getColor(color));
    }*/
}
