package com.patrickgatirigmail.finale;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ViewModel_Water_House_Number_Clicked extends ViewModel {
    private MutableLiveData<Integer> house_number_clicked = new MutableLiveData<>();

    public void setHouse_number_clicked(int house_number_clicked2) {
        this.house_number_clicked.setValue(Integer.valueOf(house_number_clicked2));
    }

    public LiveData<Integer> getHouseNumberClicked() {
        return this.house_number_clicked;
    }
}
