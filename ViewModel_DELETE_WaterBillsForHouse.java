package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_DELETE_WaterBillsForHouse extends AndroidViewModel {
    private Application application;

    public ViewModel_DELETE_WaterBillsForHouse(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void delete_water_bills_for_house(Context context, int house_number) {
        Intent intent = new Intent(context, Repository_DELETE_WaterBillsForHouse.class);
        intent.putExtra(Entity_WATER.DELETE_WATER_BILLS_FOR_HOUSE_EXTRA_NAME, house_number);
        Repository_DELETE_WaterBillsForHouse.delete_water_bills_for_house(this.application, intent);
    }
}
