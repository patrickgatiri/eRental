package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_UPDATETENANT extends AndroidViewModel {
    private Application application;

    public ViewModel_UPDATETENANT(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void update_tenant(Context context, Entity_TENANTS tenants) {
        Intent intent = new Intent(context, Repository_UPDATETENANT.class);
        intent.putExtra(Entity_TENANTS.UPDATE_TENANT_PARCELABLE_NAME, tenants);
        Repository_UPDATETENANT.update_tenant(this.application, intent);
    }
}
