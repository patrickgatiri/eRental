package com.patrickgatirigmail.finale;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ViewModel_Tenant_Clicked extends ViewModel {
    private MutableLiveData<Entity_TENANTS> tenant_clicked = new MutableLiveData<>();

    public void setTenant_clicked(Entity_TENANTS entity_tenants) {
        this.tenant_clicked.setValue(entity_tenants);
    }

    public LiveData<Entity_TENANTS> getTenant_clicked() {
        return this.tenant_clicked;
    }
}
