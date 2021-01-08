package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_UPDATERENT extends AndroidViewModel {
    private Application application;

    public ViewModel_UPDATERENT(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void update_rent(Context context, Entity_RENT rent) {
        Intent intent = new Intent(context, Repository_UPDATERENT.class);
        intent.putExtra(Entity_RENT.UPDATE_RENT_PARCELABLE_NAME, rent);
        Repository_UPDATERENT.update_rent(this.application, intent);
    }
}
