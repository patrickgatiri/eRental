package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_UPDATEWATER extends AndroidViewModel {
    private Application application;

    public ViewModel_UPDATEWATER(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void update_water(Context context, Entity_WATER water, int next_water_bill_id) {
        Intent intent = new Intent(context, Repository_UPDATEWATER.class);
        intent.putExtra(Entity_WATER.UPDATE_WATER_PARCELABLE_NAME, water);
        intent.putExtra(Entity_WATER.AFFECTED_WATER_BILL_ID, next_water_bill_id);
        Repository_UPDATEWATER.update_water(this.application, intent);
    }
}
