package com.patrickgatirigmail.finale;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class Repository_DELETE_RentBillsForHouse extends JobIntentService {
    private static final int DELETE_RENT_BILLS_JOB_ID = 10;
    private static final String TAG = "Repository_DELETE_RentBillsForHouse";
    private static THE_DATABASE the_database;
    private Handler handler = new Handler();

    public static void delete_rent_bills_for_house(Application application, Intent intent) {
        the_database = THE_DATABASE.getTHE_DATABASE(application);
        enqueueWork((Context) application, Repository_DELETE_RentBillsForHouse.class, 10, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        if (!isStopped()) {
            the_database.dao_rent().delete_rent_bills_per_house(intent.getIntExtra(Entity_RENT.DELETE_RENT_BILLS_FOR_HOUSE_EXTRA_NAME, 0));
            Log.d(TAG, "onHandleWork: Successfully deleted. ");
            show_Deletion_Toast();
        }
    }

    private void show_Deletion_Toast() {
        this.handler.post(new Runnable() {
            public void run() {
                Toast.makeText(Repository_DELETE_RentBillsForHouse.this, R.string.successfully_deleted, 0).show();
            }
        });
    }
}
