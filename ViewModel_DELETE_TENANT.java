package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_DELETE_TENANT extends AndroidViewModel {
    private Application application;

    public ViewModel_DELETE_TENANT(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void delete_tenant(Context context, Entity_TENANTS entity_tenants) {
        Intent intent = new Intent(context, Repository_DELETE_TENANT.class);
        intent.putExtra(Entity_TENANTS.DELETE_TENANT_PARCELABLE_NAME, entity_tenants);
        Repository_DELETE_TENANT.delete_tenant(this.application, intent);
    }
}
