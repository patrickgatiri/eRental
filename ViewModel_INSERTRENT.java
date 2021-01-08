package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_INSERTRENT extends AndroidViewModel {
    private Application application;

    public ViewModel_INSERTRENT(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void insert_rent(Context context, Entity_RENT rent) {
        Intent intent = new Intent(context, Repository_INSERTRENT.class);
        intent.putExtra(Entity_RENT.NEW_RENT_PARCELABLE_NAME, rent);
        Repository_INSERTRENT.insert_rent(this.application, intent);
    }
}
