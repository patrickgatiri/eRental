package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

public class ViewModel_getWaterBillById extends AndroidViewModel {
    private Repository_LIVEDATA repository_livedata;

    public ViewModel_getWaterBillById(@NonNull Application application) {
        super(application);
        this.repository_livedata = new Repository_LIVEDATA(application);
    }

    public LiveData<Entity_WATER> getWaterBillById(int water_bill_id) {
        return this.repository_livedata.getWaterBillById(water_bill_id);
    }
}
