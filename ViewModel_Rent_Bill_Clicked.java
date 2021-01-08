package com.patrickgatirigmail.finale;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ViewModel_Rent_Bill_Clicked extends ViewModel {
    private MutableLiveData<POJO_RentBills_per_House> rent_bill_clicked = new MutableLiveData<>();

    public void setRent_bill_clicked(POJO_RentBills_per_House rent_bill_clicked2) {
        this.rent_bill_clicked.setValue(rent_bill_clicked2);
    }

    public LiveData<POJO_RentBills_per_House> getRentBillClicked() {
        return this.rent_bill_clicked;
    }
}
