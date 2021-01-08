package com.patrickgatirigmail.finale;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import java.util.List;

public class Repository_LIVEDATA {
    private THE_DATABASE the_database;

    Repository_LIVEDATA(Application application) {
        this.the_database = THE_DATABASE.getTHE_DATABASE(application);
    }

    public LiveData<List<Entity_TENANTS>> getAllTenants() {
        return this.the_database.dao_tenants().getAllTenants();
    }

    public LiveData<List<POJO_RentBills_per_House>> getRentBillsPerHouse(int house_number) {
        return this.the_database.dao_rent().getRentBillsPerHouse(house_number);
    }

    public LiveData<Entity_RENT> getRentBillById(int rent_bill_id) {
        return this.the_database.dao_rent().getRentBillById(rent_bill_id);
    }

    public LiveData<List<Entity_WATER>> getWaterBillsPerHouse(int house_number) {
        return this.the_database.dao_water().getWaterBillsPerHouse(house_number);
    }

    public LiveData<Entity_WATER> getWaterBillById(int water_bill_id) {
        return this.the_database.dao_water().getWaterBillById(water_bill_id);
    }
}
