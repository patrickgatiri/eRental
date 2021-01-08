package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_INSERTTENANT extends AndroidViewModel {
    private Application application;

    public ViewModel_INSERTTENANT(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void insert_tenant(Context context, Entity_TENANTS tenant) {
        Intent insert_tenant_intent = new Intent(context, Repository_INSERTTENANT.class);
        insert_tenant_intent.putExtra(Entity_TENANTS.NEW_TENANT_PARCELABLE_NAME, tenant);
        Repository_INSERTTENANT.insert_tenant(this.application, insert_tenant_intent);
    }
}
