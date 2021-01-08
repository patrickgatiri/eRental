package com.patrickgatirigmail.finale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadCast_Receiver_Notifications extends BroadcastReceiver {
    public static final String TENANT_SAVED = "com.patrickgatirigmail.finale.TENANT_SAVED";

    public void onReceive(Context context, Intent intent) {
        if (RENTAL_APPLICATION.TENANT_SAVED_BROADCAST_ACTION.equals(intent.getAction())) {
            Entity_TENANTS tenants = (Entity_TENANTS) intent.getParcelableExtra(TENANT_SAVED);
            new Notification_Helper(context).tenant_saved_notification(tenants, context.getString(R.string.tenant_saved_message, new Object[]{tenants.getTenantName().getFIRST_NAME(), tenants.getTenantName().getLAST_NAME()}), context.getString(R.string.saved_house_message, new Object[]{Integer.valueOf(tenants.getHOUSE_NUMBER())}), context.getString(R.string.Call));
        }
    }
}
