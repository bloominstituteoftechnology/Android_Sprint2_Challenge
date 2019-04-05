package com.lambdaschool.sprint2_challenge;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ImageListView extends AppCompatActivity {
    private ItemsList itemsList;
    private RecyclerView entryRecyclerView;
    private ImageListAdapter ilaAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_image_list_view );

        context = getApplicationContext();
        ReceiveData();

        ilaAdapter=new ImageListAdapter( itemsList );


        entryRecyclerView = findViewById(R.id.recycler_view);
        entryRecyclerView.setAdapter( ilaAdapter );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        entryRecyclerView.setLayoutManager(linearLayoutManager);


         findViewById( R.id.button_send ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotifiction();

            }
        } );

    }

    private void sendNotifiction(){


        String channelId = "CHANNEL_ID";
        String name = "CHANNEL_NAME";

        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );


            String description ="Notification for Shopping List";
            String list =itemsList.getListOfItemsToShop();


            NotificationChannel notificationChannel = new NotificationChannel( channelId, name, importance );

            notificationChannel.setDescription( description );

            notificationManager.createNotificationChannel( notificationChannel );

            NotificationCompat.Builder notification = new NotificationCompat.Builder(

                    context, channelId ).setPriority( 4 ).setContentTitle( "Shopping List" ).setContentText( list ).setColor( 3 ).setSmallIcon( android.R.drawable.ic_btn_speak_now ).setDefaults( 1 );

            notificationManager.notify( 1, notification.build() );
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendData();

    }

    private void sendData() {

        Context context= getApplicationContext();

        Intent intent = new Intent(context, ImageListView.class);
        intent.putExtra("DATA_I_NEED",itemsList);

        startActivity(intent);

    }
    public void ReceiveData(){
        itemsList=(ItemsList)getIntent().getSerializableExtra("DATA_I_NEED");
        ImageListAdapter ila=new ImageListAdapter( itemsList );

    }


}
