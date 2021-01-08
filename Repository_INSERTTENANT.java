package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;

public class Repository_INSERTTENANT extends JobIntentService implements LifecycleObserver {
    private static final int INSERT_TENANT_JOB_ID = 1;
    private static final String TAG = "Repository_INSERTTENANT";
    private static THE_DATABASE the_database;
    private Handler handler = new Handler();

    public static void insert_tenant(Application application, Intent intent) {
        the_database = THE_DATABASE.getTHE_DATABASE(application);
        enqueueWork((Context) application, Repository_INSERTTENANT.class, 1, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        if (!isStopped()) {
            try {
                Entity_TENANTS tenants = (Entity_TENANTS) intent.getParcelableExtra(Entity_TENANTS.NEW_TENANT_PARCELABLE_NAME);
                if (tenants != null) {
                    the_database.dao_tenants().insert_tenant(tenants);
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onHandleWork: ");
                    sb.append(getResources().getString(R.string.Tenant_saved));
                    Log.d(str, sb.toString());
                    Intent intent1 = new Intent(RENTAL_APPLICATION.TENANT_SAVED_BROADCAST_ACTION);
                    intent1.putExtra(BroadCast_Receiver_Notifications.TENANT_SAVED, tenants);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
                    show_Insert_Toast();
                }
            } catch (SQLiteConstraintException e) {
                Log.d(TAG, "onHandleWork: Unique constraint exception");
                show_Error_Notification();
                show_Error_Toasty();
            }
        }
    }

    private void show_Insert_Toast() {
        this.handler.post(new Runnable() {
            public void run() {
                Toast.makeText(Repository_INSERTTENANT.this, R.string.Tenant_saved, 0).show();
            }
        });
    }

    public void show_Error_Toasty() {
        this.handler.post(new Runnable() {
            public void run() {
                Toasty.error(Repository_INSERTTENANT.this, Repository_INSERTTENANT.this.getString(R.string.ERROR), 5000).show();
            }
        });
    }

    public void show_Error_Notification() {
        new Notification_Helper(this).unique_constraint_failed_notification(getString(R.string.ERROR), getString(R.string.unique_house_number_error));
    }
}
