package com.patrickgatirigmail.finale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import es.dmoral.toasty.Toasty;

public class Broadcast_Receiver_Call_Tenant extends BroadcastReceiver {
    public static final String PHONE_NUMBER_TO_CALL = "PHONE_NUMBER_TO_CALL";
    private static final String TAG = "Broadcast_Receiver_Call_Tenant";

    public void onReceive(Context context, Intent intent) {
        long phone_number_to_call = intent.getLongExtra(PHONE_NUMBER_TO_CALL, 0);
        StringBuilder sb = new StringBuilder();
        sb.append("tel:0");
        sb.append(phone_number_to_call);
        Intent intent1 = new Intent("android.intent.action.CALL", Uri.parse(sb.toString()));
        if (ContextCompat.checkSelfPermission(context, "android.permission.CALL_PHONE") == 0) {
            context.startActivity(intent1);
            Log.d(TAG, "onReceive: Permission granted. ");
            return;
        }
        Toasty.error(context, context.getString(R.string.permission_denied), 5000).show();
        Log.d(TAG, "onReceive: Permission denied. ");
    }
}
