package com.patrickgatirigmail.finale;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RENTAL_APPLICATION extends Application {
    public static final String RENT_CHANNEL_GROUP_ID = "RENT_CHANNEL_GROUP_ID";
    public static final String RENT_CHANNEL_ID = "RENT_CHANNEL_ID";
    public static final String TENANTS_CHANNEL_ID = "TENANTS_CHANNEL_ID";
    public static final String TENANT_CHANNEL_GROUP_ID = "TENANT_CHANNEL_GROUP_ID";
    public static final String TENANT_SAVED_BROADCAST_ACTION = "com.patrickgatirigmail.finale.TENANT_SAVED_BROADCAST_ACTION";
    public static final String WATER_CHANNEL_GROUP_ID = "WATER_CHANNEL_GROUP_ID";
    public static final String WATER_CHANNEL_ID = "WATER_CHANNEL_ID";

    public void onCreate() {
        super.onCreate();
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadCast_Receiver_Notifications(), new IntentFilter(TENANT_SAVED_BROADCAST_ACTION));
        if (VERSION.SDK_INT >= 26) {
            init();
        }
    }

    @RequiresApi(26)
    private void init() {
        if (VERSION.SDK_INT >= 26) {
            NotificationChannelGroup TENANTS_GROUP = new NotificationChannelGroup(TENANT_CHANNEL_GROUP_ID, getResources().getString(R.string.TENANTS));
            NotificationChannelGroup RENT_GROUP = new NotificationChannelGroup(RENT_CHANNEL_GROUP_ID, getResources().getString(R.string.RENT));
            NotificationChannelGroup WATER_GROUP = new NotificationChannelGroup(WATER_CHANNEL_GROUP_ID, getResources().getString(R.string.WATER));
            NotificationChannel TENANTS = new NotificationChannel(TENANTS_CHANNEL_ID, getResources().getString(R.string.TENANTS), 4);
            TENANTS.setDescription(getResources().getString(R.string.TENANTS_Notification));
            TENANTS.setGroup(TENANT_CHANNEL_GROUP_ID);
            NotificationChannel RENT = new NotificationChannel(RENT_CHANNEL_ID, getResources().getString(R.string.RENT), 4);
            RENT.setDescription(getResources().getString(R.string.RENT_Notification));
            RENT.setGroup(RENT_CHANNEL_GROUP_ID);
            NotificationChannel WATER = new NotificationChannel(WATER_CHANNEL_ID, getResources().getString(R.string.WATER), 4);
            WATER.setDescription(getResources().getString(R.string.WATER_Notification));
            WATER.setGroup(WATER_CHANNEL_GROUP_ID);
            List<NotificationChannelGroup> notificationChannelGroups = new ArrayList<>();
            notificationChannelGroups.add(TENANTS_GROUP);
            notificationChannelGroups.add(RENT_GROUP);
            notificationChannelGroups.add(WATER_GROUP);
            ((NotificationManager) Objects.requireNonNull(getSystemService(NotificationManager.class))).createNotificationChannelGroups(notificationChannelGroups);
            List<NotificationChannel> notificationChannels = new ArrayList<>();
            notificationChannels.add(TENANTS);
            notificationChannels.add(RENT);
            notificationChannels.add(WATER);
            ((NotificationManager) Objects.requireNonNull(getSystemService(NotificationManager.class))).createNotificationChannels(notificationChannels);
        }
    }
}
