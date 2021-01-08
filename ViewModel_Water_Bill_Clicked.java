package com.patrickgatirigmail.finale;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ViewModel_Water_Bill_Clicked extends ViewModel {
    private MutableLiveData<Entity_WATER> waterbillClicked = new MutableLiveData<>();

    public void setWaterbillClicked(Entity_WATER entity_water) {
        this.waterbillClicked.setValue(entity_water);
    }

    public LiveData<Entity_WATER> getWaterbillClicked() {
        return this.waterbillClicked;
    }
}
