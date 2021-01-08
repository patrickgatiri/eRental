package com.patrickgatirigmail.finale;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class Repository_INSERTWATER extends JobIntentService {
    private static final int INSERT_WATER_JOB_ID = 5;
    private static final String TAG = "Repository_INSERTWATER";
    private static THE_DATABASE the_database;
    private Handler handler = new Handler();

    public static void insert_water(Application application, Intent intent) {
        the_database = THE_DATABASE.getTHE_DATABASE(application);
        enqueueWork((Context) application, Repository_INSERTWATER.class, 5, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        if (!isStopped()) {
            Entity_WATER water = (Entity_WATER) intent.getParcelableExtra(Entity_WATER.NEW_WATER_PARCELABLE_NAME);
            if (water != null) {
                the_database.dao_water().insert_water(water);
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onHandleWork: ");
                sb.append(getResources().getString(R.string.WaterBill_saved));
                Log.d(str, sb.toString());
                show_Insert_Toast();
            }
        }
    }

    private void show_Insert_Toast() {
        this.handler.post(new Runnable() {
            public void run() {
                Toast.makeText(Repository_INSERTWATER.this, R.string.WaterBill_saved, 0).show();
            }
        });
    }
}
