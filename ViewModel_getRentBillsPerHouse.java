package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;

public class ViewModel_getRentBillsPerHouse extends AndroidViewModel {
    private Repository_LIVEDATA repository_livedata;

    public ViewModel_getRentBillsPerHouse(@NonNull Application application) {
        super(application);
        this.repository_livedata = new Repository_LIVEDATA(application);
    }

    public LiveData<List<POJO_RentBills_per_House>> getRentBillsPerHouse(int house_number) {
        return this.repository_livedata.getRentBillsPerHouse(house_number);
    }
}
