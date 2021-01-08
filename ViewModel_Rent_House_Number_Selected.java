package com.patrickgatirigmail.finale;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ViewModel_Rent_House_Number_Selected extends ViewModel {
    private MutableLiveData<Integer> house_number_selected = new MutableLiveData<>();

    public void setHouse_number_selected(int house_number_selected2) {
        this.house_number_selected.setValue(Integer.valueOf(house_number_selected2));
    }

    public LiveData<Integer> getHouseNumber_Selected() {
        return this.house_number_selected;
    }
}
