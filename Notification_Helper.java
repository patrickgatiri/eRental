package com.patrickgatirigmail.finale;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.app.NotificationManagerCompat;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Notification_Helper {
    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String TENANTS_NOTIFICATION_ID = "TENANTS_NOTIFICATION_ID";
    private static final int TENANT_ADDITION_ERROR_NOTIFICATION_ID = 2;
    private static final String TENANT_DELETE_ERROR_NOTIFICATION_ID = "TENANT_DELETE_ERROR_NOTIFICATION_ID";
    private Context mContext;
    private NotificationManagerCompat notificationManagerCompat;

    Notification_Helper(Context context) {
        this.notificationManagerCompat = NotificationManagerCompat.from(context);
        this.mContext = context;
    }

    private void setTENANTS_NOTIFICATION_ID(int TENANTS_NOTIFICATION_ID2) {
        Editor editor = this.mContext.getSharedPreferences(SHARED_PREFERENCES, 0).edit();
        editor.putInt(TENANTS_NOTIFICATION_ID, TENANTS_NOTIFICATION_ID2);
        editor.apply();
    }

    private void setTENANT_DELETE_ERROR_NOTIFICATION_ID(int TENANT_DELETE_ERROR_NOTIFICATION_ID2) {
        Editor editor = this.mContext.getSharedPreferences(SHARED_PREFERENCES, 0).edit();
        editor.putInt(TENANT_DELETE_ERROR_NOTIFICATION_ID, TENANT_DELETE_ERROR_NOTIFICATION_ID2);
        editor.apply();
    }

    private int getTENANTS_NOTIFICATION_ID() {
        return this.mContext.getSharedPreferences(SHARED_PREFERENCES, 0).getInt(TENANTS_NOTIFICATION_ID, 1) + 1;
    }

    private int getTENANT_DELETE_ERROR_NOTIFICATION_ID() {
        return this.mContext.getSharedPreferences(SHARED_PREFERENCES, 0).getInt(TENANT_DELETE_ERROR_NOTIFICATION_ID, 3) + 1;
    }

    public void unique_constraint_failed_notification(String title, String text) {
        this.notificationManagerCompat.notify(2, new Builder(this.mContext, RENTAL_APPLICATION.TENANTS_CHANNEL_ID).setSmallIcon(R.drawable.ic_error).setContentTitle(title).setStyle(new BigTextStyle()).setContentText(text).setContentIntent(PendingIntent.getActivity(this.mContext, 0, new Intent(this.mContext, Activity_Home.class), 0)).setAutoCancel(true).setPriority(1).setCategory(NotificationCompat.CATEGORY_ERROR).setColor(-16711936).build());
    }

    public void tenant_saved_notification(Entity_TENANTS tenants, String title, String text, String call) {
        Bitmap tenant_profile_picture = null;
        try {
            tenant_profile_picture = BitmapFactory.decodeFileDescriptor(((ParcelFileDescriptor) Objects.requireNonNull(this.mContext.getContentResolver().openFileDescriptor(Uri.parse(tenants.getPROFILE_PICTURE_URI()), "r"))).getFileDescriptor());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Style big_text_style = new BigTextStyle().bigText(tenants.toString()).setBigContentTitle(this.mContext.getString(R.string.tenant_details));
        PendingIntent pendingIntent = PendingIntent.getActivity(this.mContext, 0, new Intent(this.mContext, Activity_Home.class), 0);
        Intent intent1 = new Intent(this.mContext, Broadcast_Receiver_Call_Tenant.class);
        intent1.putExtra(Broadcast_Receiver_Call_Tenant.PHONE_NUMBER_TO_CALL, tenants.getPHONE_NUMBER());
        Notification notification = new Builder(this.mContext, RENTAL_APPLICATION.TENANTS_CHANNEL_ID).setSmallIcon(R.drawable.ic_person).setContentTitle(title).setContentText(text).setContentIntent(pendingIntent).setStyle(big_text_style).setLargeIcon(tenant_profile_picture).setPriority(1).setCategory(NotificationCompat.CATEGORY_MESSAGE).addAction(R.drawable.ic_phone, call, PendingIntent.getBroadcast(this.mContext, 0, intent1, 134217728)).setAutoCancel(true).setColor(-16711936).setOnlyAlertOnce(true).build();
        int TENANTS_NOTIFICATION_ID2 = getTENANTS_NOTIFICATION_ID();
        this.notificationManagerCompat.notify(TENANTS_NOTIFICATION_ID2, notification);
        setTENANTS_NOTIFICATION_ID(TENANTS_NOTIFICATION_ID2);
    }

    public void showHouseDeleteErrorNotification(String title, String text) {
        Notification notification = new Builder(this.mContext, RENTAL_APPLICATION.TENANTS_CHANNEL_ID).setSmallIcon(R.drawable.ic_error).setPriority(1).setCategory(NotificationCompat.CATEGORY_ERROR).setStyle(new BigTextStyle()).setContentIntent(PendingIntent.getActivity(this.mContext, 0, new Intent(this.mContext, Activity_Home.class), 0)).setOnlyAlertOnce(true).setAutoCancel(true).setContentTitle(title).setColor(-16711936).setContentText(text).build();
        int TENANT_DELETE_ERROR_NOTIFICATION_ID2 = getTENANT_DELETE_ERROR_NOTIFICATION_ID();
        this.notificationManagerCompat.notify(TENANT_DELETE_ERROR_NOTIFICATION_ID2, notification);
        setTENANT_DELETE_ERROR_NOTIFICATION_ID(TENANT_DELETE_ERROR_NOTIFICATION_ID2);
    }
}
