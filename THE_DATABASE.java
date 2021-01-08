package com.patrickgatirigmail.finale;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {Entity_TENANTS.class, Entity_RENT.class, Entity_WATER.class}, exportSchema = false, version = 1)
public abstract class THE_DATABASE extends RoomDatabase {
    private static final String TAG = "THE_DATABASE";
    private static Callback callback = new Callback() {
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new insert_tenant_async_task().execute(new Void[0]);
        }
    };
    /* access modifiers changed from: private */
    public static THE_DATABASE the_database;

    private static class insert_tenant_async_task extends AsyncTask<Void, Void, Void> {
        private THE_DATABASE the_database;

        private insert_tenant_async_task(THE_DATABASE the_database2) {
            this.the_database = the_database2;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voids) {
            Dao_TENANTS dao_tenants = this.the_database.dao_tenants();
            Entity_TENANTS entity_TENANTS = new Entity_TENANTS(6, new TenantName("Eric", "Wanyoike"), null, 710456557, 6500.0d);
            dao_tenants.insert_tenant(entity_TENANTS);
            Dao_TENANTS dao_tenants2 = this.the_database.dao_tenants();
            Entity_TENANTS entity_TENANTS2 = new Entity_TENANTS(7, new TenantName("Patrick", "Gatiri"), null, 795080557, 6000.0d);
            dao_tenants2.insert_tenant(entity_TENANTS2);
            Dao_TENANTS dao_tenants3 = this.the_database.dao_tenants();
            Entity_TENANTS entity_TENANTS3 = new Entity_TENANTS(8, new TenantName("Lionel", "Messi"), null, 735434418, 9000.0d);
            dao_tenants3.insert_tenant(entity_TENANTS3);
            Dao_TENANTS dao_tenants4 = this.the_database.dao_tenants();
            Entity_TENANTS entity_TENANTS4 = new Entity_TENANTS(9, new TenantName("Ann-Maureen", "Wambui"), null, 720459347, 14000.0d);
            dao_tenants4.insert_tenant(entity_TENANTS4);
            Dao_TENANTS dao_tenants5 = this.the_database.dao_tenants();
            Entity_TENANTS entity_TENANTS5 = new Entity_TENANTS(10, new TenantName("Elizabeth", "Mwangi"), null, 722861351, 10500.0d);
            dao_tenants5.insert_tenant(entity_TENANTS5);
            this.the_database.dao_rent().insert_rent(new Entity_RENT(6, new Time("January", 2019), Double.valueOf(5000.0d)));
            this.the_database.dao_rent().insert_rent(new Entity_RENT(6, new Time("February", 2019), Double.valueOf(6000.0d)));
            this.the_database.dao_rent().insert_rent(new Entity_RENT(6, new Time("March", 2019), Double.valueOf(7000.0d)));
            this.the_database.dao_rent().insert_rent(new Entity_RENT(6, new Time("April", 2019), Double.valueOf(8000.0d)));
            this.the_database.dao_rent().insert_rent(new Entity_RENT(6, new Time("May", 2019), Double.valueOf(9000.0d)));
            Dao_WATER dao_water = this.the_database.dao_water();
            Entity_WATER entity_WATER = new Entity_WATER(6, new Time_Of_Payment("January", 2019), new WATER_READINGS(14.0d, 12.0d), 123.0d);
            dao_water.insert_water(entity_WATER);
            Dao_WATER dao_water2 = this.the_database.dao_water();
            Entity_WATER entity_WATER2 = new Entity_WATER(6, new Time_Of_Payment("February", 2019), new WATER_READINGS(13.45d, 12.34d), 456.0d);
            dao_water2.insert_water(entity_WATER2);
            Dao_WATER dao_water3 = this.the_database.dao_water();
            Entity_WATER entity_WATER3 = new Entity_WATER(6, new Time_Of_Payment("March", 2019), new WATER_READINGS(14.0d, 13.45d), 546.0d);
            dao_water3.insert_water(entity_WATER3);
            Dao_WATER dao_water4 = this.the_database.dao_water();
            Entity_WATER entity_WATER4 = new Entity_WATER(6, new Time_Of_Payment("April", 2019), new WATER_READINGS(16.9d, 14.0d), 321.0d);
            dao_water4.insert_water(entity_WATER4);
            Dao_WATER dao_water5 = this.the_database.dao_water();
            Entity_WATER entity_WATER5 = new Entity_WATER(6, new Time_Of_Payment("May", 2019), new WATER_READINGS(17.67d, 16.9d), 132.0d);
            dao_water5.insert_water(entity_WATER5);
            Dao_WATER dao_water6 = this.the_database.dao_water();
            Entity_WATER entity_WATER6 = new Entity_WATER(6, new Time_Of_Payment("June", 2019), new WATER_READINGS(19.32d, 17.67d), 213.0d);
            dao_water6.insert_water(entity_WATER6);
            Dao_WATER dao_water7 = this.the_database.dao_water();
            Entity_WATER entity_WATER7 = new Entity_WATER(6, new Time_Of_Payment("July", 2019), new WATER_READINGS(21.0d, 19.32d), 464.0d);
            dao_water7.insert_water(entity_WATER7);
            return null;
        }
    }

    public abstract Dao_RENT dao_rent();

    public abstract Dao_TENANTS dao_tenants();

    public abstract Dao_WATER dao_water();

    public static synchronized THE_DATABASE getTHE_DATABASE(Context context) {
        THE_DATABASE the_database2;
        synchronized (THE_DATABASE.class) {
            if (the_database == null) {
                the_database = (THE_DATABASE) Room.databaseBuilder(context.getApplicationContext(), THE_DATABASE.class, "RENTALS DATABASE").fallbackToDestructiveMigration().addCallback(callback).build();
            }
            Log.d(TAG, "getTHE_DATABASE: Accessing the instance of the database. ");
            the_database2 = the_database;
        }
        return the_database2;
    }
}
