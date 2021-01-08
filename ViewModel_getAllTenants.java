package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;

public class ViewModel_getAllTenants extends AndroidViewModel {
    private Repository_LIVEDATA repository_livedata;

    public ViewModel_getAllTenants(@NonNull Application application) {
        super(application);
        this.repository_livedata = new Repository_LIVEDATA(application);
    }

    public LiveData<List<Entity_TENANTS>> getAllTenants() {
        return this.repository_livedata.getAllTenants();
    }
}
