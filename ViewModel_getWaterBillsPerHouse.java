package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;

public class ViewModel_getWaterBillsPerHouse extends AndroidViewModel {
    private Repository_LIVEDATA repository_livedata;

    public ViewModel_getWaterBillsPerHouse(@NonNull Application application) {
        super(application);
        this.repository_livedata = new Repository_LIVEDATA(application);
    }

    public LiveData<List<Entity_WATER>> getWaterBillsPerHouse(int house_number) {
        return this.repository_livedata.getWaterBillsPerHouse(house_number);
    }
}
