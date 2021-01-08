package com.patrickgatirigmail.finale;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class Repository_INSERTRENT extends JobIntentService {
    private static final int INSERT_RENT_JOB_ID = 3;
    private static final String TAG = "Repository_INSERTRENT";
    private static THE_DATABASE the_database;
    private Handler handler = new Handler();

    public static void insert_rent(Application application, Intent intent) {
        the_database = THE_DATABASE.getTHE_DATABASE(application);
        enqueueWork((Context) application, Repository_INSERTRENT.class, 3, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        if (!isStopped()) {
            Entity_RENT rent = (Entity_RENT) intent.getParcelableExtra(Entity_RENT.NEW_RENT_PARCELABLE_NAME);
            if (rent != null) {
                the_database.dao_rent().insert_rent(rent);
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onHandleWork: ");
                sb.append(getResources().getString(R.string.RentBill_saved));
                Log.d(str, sb.toString());
                show_Insert_Toast();
            }
        }
    }

    private void show_Insert_Toast() {
        this.handler.post(new Runnable() {
            public void run() {
                Toast.makeText(Repository_INSERTRENT.this, R.string.RentBill_saved, 0).show();
            }
        });
    }
}
