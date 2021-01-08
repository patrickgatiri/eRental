package com.patrickgatirigmail.finale;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface Dao_TENANTS {
    @Delete
    void delete_tenant(Entity_TENANTS entity_TENANTS);

    @Query("SELECT * FROM TENANTS ORDER BY HOUSE_NUMBER ASC")
    LiveData<List<Entity_TENANTS>> getAllTenants();

    @Insert
    void insert_tenant(Entity_TENANTS entity_TENANTS);

    @Update
    void update_tenant(Entity_TENANTS entity_TENANTS);
}
