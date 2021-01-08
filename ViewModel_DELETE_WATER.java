package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_DELETE_WATER extends AndroidViewModel {
    private Application application;

    public ViewModel_DELETE_WATER(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void delete_water_bill(Context context, Entity_WATER water, double previous_water_reading, int next_water_bill_ID) {
        Intent intent = new Intent(context, Repository_DELETE_WATER.class);
        intent.putExtra(Entity_WATER.DELETE_WATER_PARCELABLE_NAME, water);
        intent.putExtra(Entity_WATER.PREVIOUS_WATER_READING, previous_water_reading);
        intent.putExtra(Entity_WATER.AFFECTED_WATER_BILL_ID, next_water_bill_ID);
        Repository_DELETE_WATER.delete_water(this.application, intent);
    }
}
