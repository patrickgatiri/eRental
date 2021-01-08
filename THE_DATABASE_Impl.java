package com.patrickgatirigmail.finale;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.arch.persistence.room.RoomMasterTable;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import android.os.Build.VERSION;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class THE_DATABASE_Impl extends THE_DATABASE {
    private volatile Dao_RENT _daoRENT;
    private volatile Dao_TENANTS _daoTENANTS;
    private volatile Dao_WATER _daoWATER;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new Delegate(1) {
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `TENANTS` (`TENANT_ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `HOUSE_NUMBER` INTEGER NOT NULL, `PROFILE_PICTURE` TEXT, `PHONE_NUMBER` INTEGER NOT NULL, `RENT_PER_MONTH` REAL NOT NULL, `FIRST_NAME` TEXT, `LAST_NAME` TEXT)");
                _db.execSQL("CREATE UNIQUE INDEX `index_TENANTS_HOUSE_NUMBER` ON `TENANTS` (`HOUSE_NUMBER`)");
                _db.execSQL("CREATE UNIQUE INDEX `index_TENANTS_PHONE_NUMBER` ON `TENANTS` (`PHONE_NUMBER`)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `RENT` (`RENT_BILL_ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `HOUSE_NUMBER` INTEGER NOT NULL, `RENT_PAID` REAL, `MONTH` TEXT, `YEAR` INTEGER, FOREIGN KEY(`HOUSE_NUMBER`) REFERENCES `TENANTS`(`HOUSE_NUMBER`) ON UPDATE RESTRICT ON DELETE RESTRICT )");
                _db.execSQL("CREATE  INDEX `index_RENT_HOUSE_NUMBER` ON `RENT` (`HOUSE_NUMBER`)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `WATER` (`WATER_BILL_ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `HOUSE_NUMBER` INTEGER NOT NULL, `AMOUNT_PAID` REAL NOT NULL, `MONTH` TEXT, `YEAR` INTEGER, `CURRENT_READING` REAL, `PREVIOUS_READING` REAL, FOREIGN KEY(`HOUSE_NUMBER`) REFERENCES `TENANTS`(`HOUSE_NUMBER`) ON UPDATE RESTRICT ON DELETE RESTRICT )");
                _db.execSQL("CREATE  INDEX `index_WATER_HOUSE_NUMBER` ON `WATER` (`HOUSE_NUMBER`)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"5cd1c18b06d54c9343d17edbe98075d3\")");
            }

            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `TENANTS`");
                _db.execSQL("DROP TABLE IF EXISTS `RENT`");
                _db.execSQL("DROP TABLE IF EXISTS `WATER`");
            }

            /* access modifiers changed from: protected */
            public void onCreate(SupportSQLiteDatabase _db) {
                if (THE_DATABASE_Impl.this.mCallbacks != null) {
                    int _size = THE_DATABASE_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((Callback) THE_DATABASE_Impl.this.mCallbacks.get(_i)).onCreate(_db);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase _db) {
                THE_DATABASE_Impl.this.mDatabase = _db;
                _db.execSQL("PRAGMA foreign_keys = ON");
                THE_DATABASE_Impl.this.internalInitInvalidationTracker(_db);
                if (THE_DATABASE_Impl.this.mCallbacks != null) {
                    int _size = THE_DATABASE_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((Callback) THE_DATABASE_Impl.this.mCallbacks.get(_i)).onOpen(_db);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void validateMigration(SupportSQLiteDatabase _db) {
                SupportSQLiteDatabase supportSQLiteDatabase = _db;
                HashMap<String, Column> _columnsTENANTS = new HashMap<>(7);
                _columnsTENANTS.put("TENANT_ID", new Column("TENANT_ID", "INTEGER", true, 1));
                _columnsTENANTS.put("HOUSE_NUMBER", new Column("HOUSE_NUMBER", "INTEGER", true, 0));
                _columnsTENANTS.put("PROFILE_PICTURE", new Column("PROFILE_PICTURE", "TEXT", false, 0));
                _columnsTENANTS.put("PHONE_NUMBER", new Column("PHONE_NUMBER", "INTEGER", true, 0));
                _columnsTENANTS.put("RENT_PER_MONTH", new Column("RENT_PER_MONTH", "REAL", true, 0));
                _columnsTENANTS.put("FIRST_NAME", new Column("FIRST_NAME", "TEXT", false, 0));
                _columnsTENANTS.put("LAST_NAME", new Column("LAST_NAME", "TEXT", false, 0));
                HashSet<ForeignKey> _foreignKeysTENANTS = new HashSet<>(0);
                HashSet<Index> _indicesTENANTS = new HashSet<>(2);
                _indicesTENANTS.add(new Index("index_TENANTS_HOUSE_NUMBER", true, Arrays.asList(new String[]{"HOUSE_NUMBER"})));
                _indicesTENANTS.add(new Index("index_TENANTS_PHONE_NUMBER", true, Arrays.asList(new String[]{"PHONE_NUMBER"})));
                TableInfo _infoTENANTS = new TableInfo("TENANTS", _columnsTENANTS, _foreignKeysTENANTS, _indicesTENANTS);
                TableInfo _existingTENANTS = TableInfo.read(supportSQLiteDatabase, "TENANTS");
                if (!_infoTENANTS.equals(_existingTENANTS)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Migration didn't properly handle TENANTS(com.patrickgatirigmail.finale.Entity_TENANTS).\n Expected:\n");
                    sb.append(_infoTENANTS);
                    sb.append("\n Found:\n");
                    sb.append(_existingTENANTS);
                    throw new IllegalStateException(sb.toString());
                }
                HashMap<String, Column> _columnsRENT = new HashMap<>(5);
                _columnsRENT.put("RENT_BILL_ID", new Column("RENT_BILL_ID", "INTEGER", true, 1));
                _columnsRENT.put("HOUSE_NUMBER", new Column("HOUSE_NUMBER", "INTEGER", true, 0));
                _columnsRENT.put("RENT_PAID", new Column("RENT_PAID", "REAL", false, 0));
                _columnsRENT.put("MONTH", new Column("MONTH", "TEXT", false, 0));
                _columnsRENT.put("YEAR", new Column("YEAR", "INTEGER", false, 0));
                HashSet<ForeignKey> _foreignKeysRENT = new HashSet<>(1);
                ForeignKey foreignKey = r11;
                ForeignKey foreignKey2 = new ForeignKey("TENANTS", "RESTRICT", "RESTRICT", Arrays.asList(new String[]{"HOUSE_NUMBER"}), Arrays.asList(new String[]{"HOUSE_NUMBER"}));
                _foreignKeysRENT.add(foreignKey);
                HashSet<Index> _indicesRENT = new HashSet<>(1);
                _indicesRENT.add(new Index("index_RENT_HOUSE_NUMBER", false, Arrays.asList(new String[]{"HOUSE_NUMBER"})));
                TableInfo _infoRENT = new TableInfo("RENT", _columnsRENT, _foreignKeysRENT, _indicesRENT);
                TableInfo _existingRENT = TableInfo.read(supportSQLiteDatabase, "RENT");
                if (!_infoRENT.equals(_existingRENT)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Migration didn't properly handle RENT(com.patrickgatirigmail.finale.Entity_RENT).\n Expected:\n");
                    sb2.append(_infoRENT);
                    sb2.append("\n Found:\n");
                    sb2.append(_existingRENT);
                    throw new IllegalStateException(sb2.toString());
                }
                HashMap<String, Column> _columnsWATER = new HashMap<>(7);
                HashMap hashMap = _columnsTENANTS;
                _columnsWATER.put("WATER_BILL_ID", new Column("WATER_BILL_ID", "INTEGER", true, 1));
                HashSet hashSet = _indicesRENT;
                _columnsWATER.put("HOUSE_NUMBER", new Column("HOUSE_NUMBER", "INTEGER", true, 0));
                _columnsWATER.put("AMOUNT_PAID", new Column("AMOUNT_PAID", "REAL", true, 0));
                _columnsWATER.put("MONTH", new Column("MONTH", "TEXT", false, 0));
                _columnsWATER.put("YEAR", new Column("YEAR", "INTEGER", false, 0));
                _columnsWATER.put("CURRENT_READING", new Column("CURRENT_READING", "REAL", false, 0));
                _columnsWATER.put("PREVIOUS_READING", new Column("PREVIOUS_READING", "REAL", false, 0));
                HashSet<ForeignKey> _foreignKeysWATER = new HashSet<>(1);
                ForeignKey foreignKey3 = new ForeignKey("TENANTS", "RESTRICT", "RESTRICT", Arrays.asList(new String[]{"HOUSE_NUMBER"}), Arrays.asList(new String[]{"HOUSE_NUMBER"}));
                _foreignKeysWATER.add(foreignKey3);
                HashSet<Index> _indicesWATER = new HashSet<>(1);
                _indicesWATER.add(new Index("index_WATER_HOUSE_NUMBER", false, Arrays.asList(new String[]{"HOUSE_NUMBER"})));
                TableInfo _infoWATER = new TableInfo("WATER", _columnsWATER, _foreignKeysWATER, _indicesWATER);
                TableInfo _existingWATER = TableInfo.read(supportSQLiteDatabase, "WATER");
                if (!_infoWATER.equals(_existingWATER)) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Migration didn't properly handle WATER(com.patrickgatirigmail.finale.Entity_WATER).\n Expected:\n");
                    sb3.append(_infoWATER);
                    sb3.append("\n Found:\n");
                    sb3.append(_existingWATER);
                    throw new IllegalStateException(sb3.toString());
                }
            }
        }, "5cd1c18b06d54c9343d17edbe98075d3", "23194ab63eeed8e92d0d5381408d6950")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, "TENANTS", "RENT", "WATER");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
        boolean _supportsDeferForeignKeys = VERSION.SDK_INT >= 21;
        if (!_supportsDeferForeignKeys) {
            try {
                _db.execSQL("PRAGMA foreign_keys = FALSE");
            } catch (Throwable th) {
                super.endTransaction();
                if (!_supportsDeferForeignKeys) {
                    _db.execSQL("PRAGMA foreign_keys = TRUE");
                }
                _db.query("PRAGMA wal_checkpoint(FULL)").close();
                if (!_db.inTransaction()) {
                    _db.execSQL("VACUUM");
                }
                throw th;
            }
        }
        super.beginTransaction();
        if (_supportsDeferForeignKeys) {
            _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
        }
        _db.execSQL("DELETE FROM `RENT`");
        _db.execSQL("DELETE FROM `WATER`");
        _db.execSQL("DELETE FROM `TENANTS`");
        super.setTransactionSuccessful();
        super.endTransaction();
        if (!_supportsDeferForeignKeys) {
            _db.execSQL("PRAGMA foreign_keys = TRUE");
        }
        _db.query("PRAGMA wal_checkpoint(FULL)").close();
        if (!_db.inTransaction()) {
            _db.execSQL("VACUUM");
        }
    }

    public Dao_TENANTS dao_tenants() {
        Dao_TENANTS dao_TENANTS;
        if (this._daoTENANTS != null) {
            return this._daoTENANTS;
        }
        synchronized (this) {
            if (this._daoTENANTS == null) {
                this._daoTENANTS = new Dao_TENANTS_Impl(this);
            }
            dao_TENANTS = this._daoTENANTS;
        }
        return dao_TENANTS;
    }

    public Dao_RENT dao_rent() {
        Dao_RENT dao_RENT;
        if (this._daoRENT != null) {
            return this._daoRENT;
        }
        synchronized (this) {
            if (this._daoRENT == null) {
                this._daoRENT = new Dao_RENT_Impl(this);
            }
            dao_RENT = this._daoRENT;
        }
        return dao_RENT;
    }

    public Dao_WATER dao_water() {
        Dao_WATER dao_WATER;
        if (this._daoWATER != null) {
            return this._daoWATER;
        }
        synchronized (this) {
            if (this._daoWATER == null) {
                this._daoWATER = new Dao_WATER_Impl(this);
            }
            dao_WATER = this._daoWATER;
        }
        return dao_WATER;
    }
}
