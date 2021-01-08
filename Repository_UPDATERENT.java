package com.patrickgatirigmail.finale;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class Repository_UPDATERENT extends JobIntentService {
    private static final String TAG = "Repository_UPDATERENT";
    private static final int UPDATE_RENT_JOB_ID = 4;
    private static THE_DATABASE the_database;
    private Handler handler = new Handler();

    public static void update_rent(Application application, Intent intent) {
        the_database = THE_DATABASE.getTHE_DATABASE(application);
        enqueueWork((Context) application, Repository_UPDATERENT.class, 4, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        if (!isStopped()) {
            Entity_RENT rent = (Entity_RENT) intent.getParcelableExtra(Entity_RENT.UPDATE_RENT_PARCELABLE_NAME);
            if (rent != null) {
                the_database.dao_rent().update_rent(rent);
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onHandleWork: ");
                sb.append(getResources().getString(R.string.RentBill_updated));
                Log.d(str, sb.toString());
            }
        }
    }

    private void show_Update_Toast() {
        this.handler.post(new Runnable() {
            public void run() {
                Toast.makeText(Repository_UPDATERENT.this, R.string.RentBill_updated, 0).show();
            }
        });
    }
}
