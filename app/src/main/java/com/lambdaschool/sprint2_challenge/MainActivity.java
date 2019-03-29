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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Context context;
    public static SharedPreferences prefs;
    public static int REQUEST_CODE = 42;
    public static SharedPreferences.Editor editor;
    private static final String TAG = "MainActivity";
    Button sendButton;
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendButton = findViewById(R.id.send_button);
        prefs = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        editor = prefs.edit();
        context = this;

        notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        ItemDao dao = new ItemDao();
        initRecyclerView();


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDao.updateSelected();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(MainActivity.TAG, ItemDao.getHumanReadableStringFromItemArrList(ItemDao.getSelectedItems()));
            }
        });

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(ItemDao.getAllItems(),ItemDao.getSelectedItems(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ItemDao.updateSelected();
    }

    //notification code
/*
    String channelId = getPackageName() + ".button";
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        CharSequence name = "Channel Name";
        String description = "Shopping List";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel channel = new NotificationChannel(channelId, name, importance);
        channel.setDescription(description);
        notificationManager.createNotificationChannel(channel);
    }

    NotificationCompat.Builder builder = new NotificationCompat.Builder(v.getContext(), channelId)
            .setPriority(NotificationManager.IMPORTANCE_DEFAULT)//importance only affects versions 24 -> 26
            .setContentTitle("Button")
            .setContentText("The Button Was Pressed")

            .setColor(context.getResources().getColor(R.color.colorPrimary))  //icon color in notificaton bar

            .setSmallIcon(android.R.drawable.ic_dialog_alert);

                notificationManager.notify(1, builder.build());   //extract notificatonID (1) as a constant (usually in a constant class)

    //extract all strings as string resources*/
}
