package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

public class ViewModel_getRentBillById extends AndroidViewModel {
    private Repository_LIVEDATA repository_livedata;

    public ViewModel_getRentBillById(@NonNull Application application) {
        super(application);
        this.repository_livedata = new Repository_LIVEDATA(application);
    }

    public LiveData<Entity_RENT> getRentBillById(int rent_bill_id) {
        return this.repository_livedata.getRentBillById(rent_bill_id);
    }
}
