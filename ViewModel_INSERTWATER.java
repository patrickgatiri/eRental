package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_INSERTWATER extends AndroidViewModel {
    private Application application;

    public ViewModel_INSERTWATER(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void insert_water(Context context, Entity_WATER water) {
        Intent intent = new Intent(context, Repository_INSERTWATER.class);
        intent.putExtra(Entity_WATER.NEW_WATER_PARCELABLE_NAME, water);
        Repository_INSERTWATER.insert_water(this.application, intent);
    }
}
