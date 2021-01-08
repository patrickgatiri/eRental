package com.patrickgatirigmail.finale;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class Repository_DELETE_WATER extends JobIntentService {
    private static final int DELETE_WATER_JOB_ID = 9;
    private static final String TAG = "Repository_DELETE_WATER";
    private static THE_DATABASE the_database;
    private Handler handler = new Handler();

    public static void delete_water(Application application, Intent intent) {
        the_database = THE_DATABASE.getTHE_DATABASE(application);
        enqueueWork((Context) application, Repository_DELETE_WATER.class, 9, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        if (!isStopped()) {
            Entity_WATER entity_water = (Entity_WATER) intent.getParcelableExtra(Entity_WATER.DELETE_WATER_PARCELABLE_NAME);
            double previous_water_reading = intent.getDoubleExtra(Entity_WATER.PREVIOUS_WATER_READING, 0.0d);
            int next_water_bill_ID = intent.getIntExtra(Entity_WATER.AFFECTED_WATER_BILL_ID, 0);
            if (entity_water != null) {
                the_database.dao_water().delete_water(entity_water);
                Log.d(TAG, "onHandleWork: Water bill deleted. ");
                show_Deletion_Toast();
                if (next_water_bill_ID != 0) {
                    if (previous_water_reading != 0.0d) {
                        the_database.dao_water().update_next_water_bill(previous_water_reading, next_water_bill_ID);
                    } else {
                        the_database.dao_water().update_next_water_bill(0.0d, next_water_bill_ID);
                    }
                }
            }
        }
    }

    private void show_Deletion_Toast() {
        this.handler.post(new Runnable() {
            public void run() {
                Toast.makeText(Repository_DELETE_WATER.this, R.string.WaterBill_deleted, 0).show();
            }
        });
    }
}
