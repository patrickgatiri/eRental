package com.patrickgatirigmail.finale;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class View_Model_Latest_Water_Bill extends ViewModel {
    private MutableLiveData<Entity_WATER> most_recent_water_bill = new MutableLiveData<>();

    public void setMost_recent_water_bill(Entity_WATER most_recent_water_bill2) {
        this.most_recent_water_bill.setValue(most_recent_water_bill2);
    }

    public LiveData<Entity_WATER> getMost_recent_water_bill() {
        return this.most_recent_water_bill;
    }
}
