package com.lambdaschool.sprint2_challenge;

import android.app.NotificationManager;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.util.Log;

// S02M04-10 create a receiver for our remote input
public class NotificationResponseReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String entryText = processNotificationResponse(intent, context);
    }


    // S02M04-10b move remote input processing to this receiver
    String processNotificationResponse(Intent intent, Context context) {
        Bundle input = RemoteInput.getResultsFromIntent(intent);

        if (input != null) {
            // update user response was received
            NotificationCompat.Builder successNotification = new NotificationCompat.Builder(
                    context, MainActivity.channelId)
                    .setSmallIcon(R.drawable.ic_collections_bookmark_black_24dp)
                    .setContentText("New Entry Created");

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                    Context.NOTIFICATION_SERVICE);

            notificationManager.notify(MainActivity.REMINDER_NOTIFICATION_ID, successNotification.build());
            return fooBar;
        }
        return null;
    }
}