package com.patrickgatirigmail.finale;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class Repository_UPDATETENANT extends JobIntentService {
    private static final String TAG = "Repository_UPDATETENANT";
    private static final int UPDATE_TENANT_JOB_ID = 2;
    private static THE_DATABASE the_database;
    private Handler handler = new Handler();

    public static void update_tenant(Application application, Intent intent) {
        the_database = THE_DATABASE.getTHE_DATABASE(application);
        enqueueWork((Context) application, Repository_UPDATETENANT.class, 2, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        if (!isStopped()) {
            Entity_TENANTS tenants = (Entity_TENANTS) intent.getParcelableExtra(Entity_TENANTS.UPDATE_TENANT_PARCELABLE_NAME);
            if (tenants != null) {
                the_database.dao_tenants().update_tenant(tenants);
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onHandleWork: ");
                sb.append(getResources().getString(R.string.Tenant_updated));
                Log.d(str, sb.toString());
            }
        }
    }

    private void show_Update_Toast() {
        this.handler.post(new Runnable() {
            public void run() {
                Toast.makeText(Repository_UPDATETENANT.this, R.string.Tenant_updated, 0).show();
            }
        });
    }
}
