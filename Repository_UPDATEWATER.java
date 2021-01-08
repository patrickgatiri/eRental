package com.patrickgatirigmail.finale;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class Repository_UPDATEWATER extends JobIntentService {
    private static final String TAG = "Repository_UPDATEWATER";
    private static final int UPDATE_WATER_JOB_ID = 6;
    private static THE_DATABASE the_database;
    private Handler handler = new Handler();

    public static void update_water(Application application, Intent intent) {
        the_database = THE_DATABASE.getTHE_DATABASE(application);
        enqueueWork((Context) application, Repository_UPDATEWATER.class, 6, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        if (!isStopped()) {
            Entity_WATER water = (Entity_WATER) intent.getParcelableExtra(Entity_WATER.UPDATE_WATER_PARCELABLE_NAME);
            int next_water_bill_id = intent.getIntExtra(Entity_WATER.AFFECTED_WATER_BILL_ID, 0);
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onHandleWork: ");
            sb.append(String.valueOf(next_water_bill_id));
            Log.d(str, sb.toString());
            if (water != null) {
                the_database.dao_water().update_water(water);
                String str2 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("onHandleWork: ");
                sb2.append(getResources().getString(R.string.WaterBill_updated));
                Log.d(str2, sb2.toString());
                show_Update_Toast();
                if (next_water_bill_id != 0) {
                    the_database.dao_water().update_next_water_bill(water.getWater_readings().getCURRENT_READING(), next_water_bill_id);
                }
            }
        }
    }

    private void show_Update_Toast() {
        this.handler.post(new Runnable() {
            public void run() {
                Toast.makeText(Repository_UPDATEWATER.this, R.string.WaterBill_updated, 0).show();
            }
        });
    }
}
