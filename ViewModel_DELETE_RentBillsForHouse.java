package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ViewModel_DELETE_RentBillsForHouse extends AndroidViewModel {
    private Application application;

    public ViewModel_DELETE_RentBillsForHouse(@NonNull Application application2) {
        super(application2);
        this.application = application2;
    }

    public void delete_RentBillForHouse(Context context, int house_number) {
        Intent intent = new Intent(context, Repository_DELETE_RentBillsForHouse.class);
        intent.putExtra(Entity_RENT.DELETE_RENT_BILLS_FOR_HOUSE_EXTRA_NAME, house_number);
        Repository_DELETE_RentBillsForHouse.delete_rent_bills_for_house(this.application, intent);
    }
}
