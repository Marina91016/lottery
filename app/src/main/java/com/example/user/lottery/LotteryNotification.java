package com.example.user.lottery;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Marina Bronfman on 1/23/16.
 */
public class LotteryNotification {
    public NotificationCompat.Builder notification;

    ArrayList<Integer> oldUniqueNotificationIDs = new ArrayList<>();
    public Integer uniqueID;
    MainActivity mainActivity;

    public LotteryNotification(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        notification = new NotificationCompat.Builder(mainActivity);
        notification.setAutoCancel(true);
        addNewUniqueID();
    }

    /*
    Add new Unique ID to list of ID's.
     */
    private void addNewUniqueID()
    {
        Random rand = new Random();
        int index = rand.nextInt();

        while (oldUniqueNotificationIDs.contains(index)) {
            index = rand.nextInt();
            Log.i(MainActivity.TAG, String.valueOf(index));
        }
        oldUniqueNotificationIDs.add(index);
        this.uniqueID = index;
    }

    //Builed notification.
    public void buildNotification(){
        //Build the notification.
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("This is a ticker.");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Here is the title.");
        notification.setContentText("I am the body text of your notification.");
        //Puts notification on home screen.
        Intent intent = new Intent(mainActivity,LotteryNotification.class);
        //Get access for device to Intent in our app.
        PendingIntent pendingIntent = PendingIntent.getActivity(mainActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        //Build notification and issue it. Send notification out from your device.
        //Build and sent out the notifications.
        NotificationManager nm = (NotificationManager) mainActivity.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());
    }
}
