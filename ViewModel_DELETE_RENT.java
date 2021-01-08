package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_DELETE_RENT extends AndroidViewModel {
    private Application application;

    public ViewModel_DELETE_RENT(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void delete_rent(Context context, Entity_RENT entity_rent) {
        Intent intent = new Intent(context, Repository_DELETE_RENT.class);
        intent.putExtra(Entity_RENT.DELETE_RENT_PARCELABLE_NAME, entity_rent);
        Repository_DELETE_RENT.delete_rent(this.application, intent);
    }
}
