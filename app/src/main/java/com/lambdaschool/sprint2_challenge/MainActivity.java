package com.lambdaschool.sprint2_challenge;

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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int SHARE_NOTIFICATION_ID = 1;
    public static final int REQUEST_CODE = 5;
    private Context context;
    private ArrayList<ShoppingItem> itemArrayList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ShoppingListAdapter listAdapter;
    public static SharedPreferences preferences;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        context = this;
        preferences = this.getPreferences(Context.MODE_PRIVATE);
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        itemArrayList = ShoppingListDao.getAllItems();
        recyclerView = findViewById(R.id.items_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ShoppingListAdapter(itemArrayList);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_share) {
            ArrayList<String> checkedList = ShoppingListDao.getCheckedBookNames();
            String sendContent = "Shopping List: ";
            for (int i = 0; i < checkedList.size(); ++i) {
                if (i < checkedList.size() - 1) {
                    sendContent += (checkedList.get(i) + ", ");
                } else {
                    sendContent += (checkedList.get(i) + ".");
                }
            }
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, sendContent);
            intent.createChooser(intent, "Share via");
            startActivityForResult(intent, REQUEST_CODE);

            return true;
        }

        if (id == R.id.action_reset) {
            ShoppingListDao.resetAll();
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            String channelId = getPackageName() + ".share";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "Share Channel";
                String description = "Notifications triggered sharing.";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel = new NotificationChannel(channelId, name, importance);
                channel.setDescription(description);

                notificationManager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
                    .setContentTitle("Shopping List")
                    .setContentText("Shopping list shared!")
                    .setColor(context.getColor(R.color.colorPrimary))
                    .setSmallIcon(android.R.drawable.ic_dialog_alert);
            notificationManager.notify(SHARE_NOTIFICATION_ID, builder.build());
        }
    }
}
