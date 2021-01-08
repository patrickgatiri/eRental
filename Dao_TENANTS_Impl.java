package com.patrickgatirigmail.finale;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Dao_TENANTS_Impl implements Dao_TENANTS {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfEntity_TENANTS;
    private final EntityInsertionAdapter __insertionAdapterOfEntity_TENANTS;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfEntity_TENANTS;

    public Dao_TENANTS_Impl(RoomDatabase __db2) {
        this.__db = __db2;
        this.__insertionAdapterOfEntity_TENANTS = new EntityInsertionAdapter<Entity_TENANTS>(__db2) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `TENANTS`(`TENANT_ID`,`HOUSE_NUMBER`,`PROFILE_PICTURE`,`PHONE_NUMBER`,`RENT_PER_MONTH`,`FIRST_NAME`,`LAST_NAME`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement stmt, Entity_TENANTS value) {
                stmt.bindLong(1, (long) value.getTENANT_ID());
                stmt.bindLong(2, (long) value.getHOUSE_NUMBER());
                if (value.getPROFILE_PICTURE_URI() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getPROFILE_PICTURE_URI());
                }
                stmt.bindLong(4, value.getPHONE_NUMBER());
                stmt.bindDouble(5, value.getRENT_PER_MONTH());
                TenantName _tmpTenantName = value.getTenantName();
                if (_tmpTenantName != null) {
                    if (_tmpTenantName.getFIRST_NAME() == null) {
                        stmt.bindNull(6);
                    } else {
                        stmt.bindString(6, _tmpTenantName.getFIRST_NAME());
                    }
                    if (_tmpTenantName.getLAST_NAME() == null) {
                        stmt.bindNull(7);
                    } else {
                        stmt.bindString(7, _tmpTenantName.getLAST_NAME());
                    }
                } else {
                    stmt.bindNull(6);
                    stmt.bindNull(7);
                }
            }
        };
        this.__deletionAdapterOfEntity_TENANTS = new EntityDeletionOrUpdateAdapter<Entity_TENANTS>(__db2) {
            public String createQuery() {
                return "DELETE FROM `TENANTS` WHERE `TENANT_ID` = ?";
            }

            public void bind(SupportSQLiteStatement stmt, Entity_TENANTS value) {
                stmt.bindLong(1, (long) value.getTENANT_ID());
            }
        };
        this.__updateAdapterOfEntity_TENANTS = new EntityDeletionOrUpdateAdapter<Entity_TENANTS>(__db2) {
            public String createQuery() {
                return "UPDATE OR ABORT `TENANTS` SET `TENANT_ID` = ?,`HOUSE_NUMBER` = ?,`PROFILE_PICTURE` = ?,`PHONE_NUMBER` = ?,`RENT_PER_MONTH` = ?,`FIRST_NAME` = ?,`LAST_NAME` = ? WHERE `TENANT_ID` = ?";
            }

            public void bind(SupportSQLiteStatement stmt, Entity_TENANTS value) {
                stmt.bindLong(1, (long) value.getTENANT_ID());
                stmt.bindLong(2, (long) value.getHOUSE_NUMBER());
                if (value.getPROFILE_PICTURE_URI() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getPROFILE_PICTURE_URI());
                }
                stmt.bindLong(4, value.getPHONE_NUMBER());
                stmt.bindDouble(5, value.getRENT_PER_MONTH());
                TenantName _tmpTenantName = value.getTenantName();
                if (_tmpTenantName != null) {
                    if (_tmpTenantName.getFIRST_NAME() == null) {
                        stmt.bindNull(6);
                    } else {
                        stmt.bindString(6, _tmpTenantName.getFIRST_NAME());
                    }
                    if (_tmpTenantName.getLAST_NAME() == null) {
                        stmt.bindNull(7);
                    } else {
                        stmt.bindString(7, _tmpTenantName.getLAST_NAME());
                    }
                } else {
                    stmt.bindNull(6);
                    stmt.bindNull(7);
                }
                stmt.bindLong(8, (long) value.getTENANT_ID());
            }
        };
    }

    public void insert_tenant(Entity_TENANTS tenants) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfEntity_TENANTS.insert(tenants);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete_tenant(Entity_TENANTS tenants) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfEntity_TENANTS.handle(tenants);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update_tenant(Entity_TENANTS tenants) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfEntity_TENANTS.handle(tenants);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public LiveData<List<Entity_TENANTS>> getAllTenants() {
        String str = "SELECT * FROM TENANTS ORDER BY HOUSE_NUMBER ASC";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM TENANTS ORDER BY HOUSE_NUMBER ASC", 0);
        return new ComputableLiveData<List<Entity_TENANTS>>() {
            private Observer _observer;

            /* access modifiers changed from: protected */
            public List<Entity_TENANTS> compute() {
                TenantName _tmpTenantName;
                if (this._observer == null) {
                    this._observer = new Observer("TENANTS", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            AnonymousClass4.this.invalidate();
                        }
                    };
                    Dao_TENANTS_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = Dao_TENANTS_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfTENANTID = _cursor.getColumnIndexOrThrow("TENANT_ID");
                    int _cursorIndexOfHOUSENUMBER = _cursor.getColumnIndexOrThrow("HOUSE_NUMBER");
                    int _cursorIndexOfPROFILEPICTUREURI = _cursor.getColumnIndexOrThrow("PROFILE_PICTURE");
                    int _cursorIndexOfPHONENUMBER = _cursor.getColumnIndexOrThrow("PHONE_NUMBER");
                    int _cursorIndexOfRENTPERMONTH = _cursor.getColumnIndexOrThrow("RENT_PER_MONTH");
                    int _cursorIndexOfFIRSTNAME = _cursor.getColumnIndexOrThrow("FIRST_NAME");
                    int _cursorIndexOfLASTNAME = _cursor.getColumnIndexOrThrow("LAST_NAME");
                    List<Entity_TENANTS> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        int _tmpHOUSE_NUMBER = _cursor.getInt(_cursorIndexOfHOUSENUMBER);
                        String _tmpPROFILE_PICTURE_URI = _cursor.getString(_cursorIndexOfPROFILEPICTUREURI);
                        long _tmpPHONE_NUMBER = _cursor.getLong(_cursorIndexOfPHONENUMBER);
                        double _tmpRENT_PER_MONTH = _cursor.getDouble(_cursorIndexOfRENTPERMONTH);
                        if (_cursor.isNull(_cursorIndexOfFIRSTNAME)) {
                            if (_cursor.isNull(_cursorIndexOfLASTNAME)) {
                                _tmpTenantName = null;
                                Entity_TENANTS entity_TENANTS = new Entity_TENANTS(_tmpHOUSE_NUMBER, _tmpTenantName, _tmpPROFILE_PICTURE_URI, _tmpPHONE_NUMBER, _tmpRENT_PER_MONTH);
                                entity_TENANTS.setTENANT_ID(_cursor.getInt(_cursorIndexOfTENANTID));
                                _result.add(entity_TENANTS);
                            }
                        }
                        _tmpTenantName = new TenantName(_cursor.getString(_cursorIndexOfFIRSTNAME), _cursor.getString(_cursorIndexOfLASTNAME));
                        TenantName tenantName = _tmpTenantName;
                        Entity_TENANTS entity_TENANTS2 = new Entity_TENANTS(_tmpHOUSE_NUMBER, _tmpTenantName, _tmpPROFILE_PICTURE_URI, _tmpPHONE_NUMBER, _tmpRENT_PER_MONTH);
                        entity_TENANTS2.setTENANT_ID(_cursor.getInt(_cursorIndexOfTENANTID));
                        _result.add(entity_TENANTS2);
                    }
                    _cursor.close();
                    return _result;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    _cursor.close();
                    throw th2;
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }
}
