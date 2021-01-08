package com.patrickgatirigmail.finale;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;

public class Repository_DELETE_TENANT extends JobIntentService {
    private static final int DELETE_TENANT_JOB_ID = 7;
    private static final String TAG = "Repository_DELETE_TENANT";
    private static THE_DATABASE the_database;
    private Handler handler = new Handler();

    public static void delete_tenant(Application application, Intent intent) {
        the_database = THE_DATABASE.getTHE_DATABASE(application);
        enqueueWork((Context) application, Repository_DELETE_TENANT.class, 7, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        if (!isStopped()) {
            Entity_TENANTS tenants = (Entity_TENANTS) intent.getParcelableExtra(Entity_TENANTS.DELETE_TENANT_PARCELABLE_NAME);
            if (tenants != null) {
                try {
                    the_database.dao_tenants().delete_tenant(tenants);
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onHandleWork: ");
                    sb.append(getString(R.string.Tenant_deleted));
                    Log.d(str, sb.toString());
                    show_Deletion_Toast();
                } catch (SQLiteConstraintException e) {
                    e.printStackTrace();
                    show_Error_Toasty();
                    showErrorNotification(tenants.getHOUSE_NUMBER());
                    String str2 = TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("onHandleWork: ");
                    sb2.append(e.toString());
                    Log.d(str2, sb2.toString());
                }
            }
        }
    }

    public void show_Error_Toasty() {
        this.handler.post(new Runnable() {
            public void run() {
                Toasty.error(Repository_DELETE_TENANT.this, Repository_DELETE_TENANT.this.getString(R.string.ERROR), 5000).show();
            }
        });
    }

    private void showErrorNotification(int house_number) {
        new Notification_Helper(this).showHouseDeleteErrorNotification(getString(R.string.ERROR), getString(R.string.cant_delete_house, new Object[]{Integer.valueOf(house_number)}));
    }

    private void show_Deletion_Toast() {
        this.handler.post(new Runnable() {
            public void run() {
                Toast.makeText(Repository_DELETE_TENANT.this, R.string.Tenant_deleted, 0).show();
            }
        });
    }
}
