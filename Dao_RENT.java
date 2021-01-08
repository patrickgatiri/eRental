package com.patrickgatirigmail.finale;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface Dao_RENT {
    @Delete
    void delete_rent(Entity_RENT entity_RENT);

    @Query("DELETE FROM RENT WHERE HOUSE_NUMBER IN ( :house_number)")
    void delete_rent_bills_per_house(int i);

    @Query("SELECT * FROM RENT WHERE RENT_BILL_ID IN ( :rent_bill_id)")
    LiveData<Entity_RENT> getRentBillById(int i);

    @Query("SELECT RENT_BILL_ID, RENT.HOUSE_NUMBER, RENT_PER_MONTH, RENT_PAID, MONTH, YEAR FROM RENT INNER JOIN TENANTS ON RENT.HOUSE_NUMBER = TENANTS.HOUSE_NUMBER WHERE RENT.HOUSE_NUMBER IN ( :HOUSE_NUMBER)ORDER BY RENT_BILL_ID DESC")
    LiveData<List<POJO_RentBills_per_House>> getRentBillsPerHouse(int i);

    @Insert
    void insert_rent(Entity_RENT entity_RENT);

    @Update
    void update_rent(Entity_RENT entity_RENT);
}
