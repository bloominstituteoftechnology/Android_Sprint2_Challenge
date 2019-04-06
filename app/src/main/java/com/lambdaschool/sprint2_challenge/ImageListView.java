package com.lambdaschool.sprint2_challenge;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import java.util.ArrayList;

public class ImageListView extends AppCompatActivity {
    private static ItemsList itemsList;
    private RecyclerView entryRecyclerView;
    private ImageListAdapter ilaAdapter;
    private Context context;
    private static SharedPreferences preferences;
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
                itemsList=ilaAdapter.getItemList();

                writePreferance();

                sendData();

                sendNotifiction();
            }
        } );

         findViewById( R.id.button_reset ).setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 itemsList=itemsList.reset();
                 ilaAdapter.set( itemsList   );
                 writePreferance();
                 buttonNIghtControl();
             }
         } );



    }

    public void writePreferance(){


        String strIDs="";
        preferences = getApplicationContext().getSharedPreferences("ShoppingListRecord", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();


        for(int i=0;i<itemsList.size();i++) {
            if(i==itemsList.size()-1){
                strIDs+=itemsList.get( i ).getiID();
            }else{
                strIDs+=itemsList.get( i ).getiID()+",";
            }
            editor.putString("ITEM_FOR_SHOPPING"+Integer.toString( i ), itemsList.get( i ).toCSV());
            editor.apply();
        }

        editor.putString("IDS_FOR_SHOPPING", strIDs);
        editor.apply();
    }

    private void sendNotifiction(){
        String list =itemsList.getListOfItemsToShop();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,list);
        intent.setType("text/plain");
// Verify that the intent will resolve to an activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


        String channelId = "CHANNEL_ID";
        String name = "CHANNEL_NAME";

        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );


            String description ="Notification for Shopping List";



            NotificationChannel notificationChannel = new NotificationChannel( channelId, name, importance );

            notificationChannel.setDescription( description );

            notificationManager.createNotificationChannel( notificationChannel );

            NotificationCompat.Builder notification = new NotificationCompat.Builder(

                    context, channelId ).setPriority( 4 ).setContentTitle( "Shopping List" ).setContentText( list ).setColor( 3 ).setSmallIcon( android.R.drawable.ic_btn_speak_now ).setDefaults( 1 );

            notificationManager.notify( 1, notification.build() );
        }
    }

    void buttonNIghtControl(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Button bt=findViewById( R.id.button_reset );
            UiModeManager umm=context.getSystemService(UiModeManager.class);
            if(umm.getNightMode()==UiModeManager.MODE_NIGHT_YES ){
                umm.setNightMode( UiModeManager.MODE_NIGHT_NO );
                bt.setText( "Night mode" );//I do not know why this is not working
                TextView tv=new TextView( context ); ///I do not know why this is not working
                tv.setText( "Day" );//I do not know why this is not working


            }else{
                umm.setNightMode( UiModeManager.MODE_NIGHT_YES );
                bt.setText( "Day mode" );//I do not know why this is not working
                TextView tv=new TextView( context );//I do not know why this is not working
                tv.setText( "Night" );//I do not know why this is not working

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendData();

    }

    private void sendData() {

        Context context= getApplicationContext();

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("DATA_I_NEED",itemsList);

        startActivity(intent);

    }
    public void ReceiveData(){
        itemsList=(ItemsList)getIntent().getSerializableExtra("DATA_I_NEED");
        ImageListAdapter ila=new ImageListAdapter( itemsList );

    }


}
