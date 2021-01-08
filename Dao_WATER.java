package com.patrickgatirigmail.finale;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface Dao_WATER {
    @Delete
    void delete_water(Entity_WATER entity_WATER);

    @Query("DELETE FROM WATER WHERE HOUSE_NUMBER IN( :house_number)")
    void delete_water_bills_for_house(int i);

    @Query("SELECT * FROM WATER WHERE WATER_BILL_ID IN ( :waterbillid) ")
    LiveData<Entity_WATER> getWaterBillById(int i);

    @Query("SELECT * FROM WATER WHERE HOUSE_NUMBER IN ( :HOUSE_NUMBER)ORDER BY WATER_BILL_ID DESC")
    LiveData<List<Entity_WATER>> getWaterBillsPerHouse(int i);

    @Insert
    void insert_water(Entity_WATER entity_WATER);

    @Query("UPDATE WATER SET PREVIOUS_READING = ( :previous_reading)WHERE WATER_BILL_ID IN ( :next_water_bill_id)")
    void update_next_water_bill(double d, int i);

    @Update
    void update_water(Entity_WATER entity_WATER);
}
