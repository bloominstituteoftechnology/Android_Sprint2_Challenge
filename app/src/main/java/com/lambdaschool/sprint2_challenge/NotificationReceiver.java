package com.lambdaschool.sprint2_challenge;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

public class NotificationReceiver extends BroadcastReceiver {
Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
       this.context = context;
            displayNotification();
    }


    public void displayNotification() {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);


        String channelId = context.getApplicationContext().getPackageName() + ".button";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Shopping List";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }

        RemoteInput remoteInput = new RemoteInput.Builder(Constants.NEW_ORDER_ACTION_KEY)
                .setLabel("Enter your Entry Text")
                .build();

        Intent inputIntent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingInputIntent = PendingIntent.getBroadcast(
                context,
                Constants.LIST_INTENT_RESPONSE_REQUEST_CODE,
                inputIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action inputAction = new NotificationCompat.Action.Builder(
                android.R.drawable.ic_menu_edit, "Order", pendingInputIntent)
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true)
                .build();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), channelId)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)//importance only affects versions 24 -> 26
                .setContentTitle("Order Sent")
                .setContentText("Your order has been received")
                .setAutoCancel(true)
                .addAction(inputAction)
                .setColor(context.getResources().getColor(R.color.colorPrimary))  //icon color in notificaton bar
                .setSmallIcon(android.R.drawable.ic_dialog_alert);
        notificationManager.notify(1, builder.build());   //extract notificatonID (1) as a constant (usually in a constant class)
    }


}
