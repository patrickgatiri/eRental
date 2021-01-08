package com.patrickgatirigmail.finale;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class Repository_DELETE_RENT extends JobIntentService {
    private static final int DELETE_RENT_JOB_ID = 8;
    private static final String TAG = "Repository_DELETE_RENT";
    private static THE_DATABASE the_database;
    private Handler handler = new Handler();

    public static void delete_rent(Application application, Intent intent) {
        the_database = THE_DATABASE.getTHE_DATABASE(application);
        enqueueWork((Context) application, Repository_DELETE_RENT.class, 8, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        if (!isStopped()) {
            Entity_RENT entity_rent = (Entity_RENT) intent.getParcelableExtra(Entity_RENT.DELETE_RENT_PARCELABLE_NAME);
            if (entity_rent != null) {
                the_database.dao_rent().delete_rent(entity_rent);
                Log.d(TAG, "onHandleWork: Rent bill deleted. ");
                show_Deletion_Toast();
            }
        }
    }

    private void show_Deletion_Toast() {
        this.handler.post(new Runnable() {
            public void run() {
                Toast.makeText(Repository_DELETE_RENT.this, R.string.RentBill_deleted, 0).show();
            }
        });
    }
}
