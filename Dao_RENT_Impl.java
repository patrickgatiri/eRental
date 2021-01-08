package com.patrickgatirigmail.finale;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Dao_RENT_Impl implements Dao_RENT {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfEntity_RENT;
    private final EntityInsertionAdapter __insertionAdapterOfEntity_RENT;
    private final SharedSQLiteStatement __preparedStmtOfDelete_rent_bills_per_house;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfEntity_RENT;

    public Dao_RENT_Impl(RoomDatabase __db2) {
        this.__db = __db2;
        this.__insertionAdapterOfEntity_RENT = new EntityInsertionAdapter<Entity_RENT>(__db2) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `RENT`(`RENT_BILL_ID`,`HOUSE_NUMBER`,`RENT_PAID`,`MONTH`,`YEAR`) VALUES (nullif(?, 0),?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement stmt, Entity_RENT value) {
                stmt.bindLong(1, (long) value.getRENT_BILL_ID());
                stmt.bindLong(2, (long) value.getHOUSE_NUMBER());
                if (value.getRENT_PAID() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindDouble(3, value.getRENT_PAID().doubleValue());
                }
                Time _tmpTime = value.getTime();
                if (_tmpTime != null) {
                    if (_tmpTime.getMONTH() == null) {
                        stmt.bindNull(4);
                    } else {
                        stmt.bindString(4, _tmpTime.getMONTH());
                    }
                    stmt.bindLong(5, (long) _tmpTime.getYEAR());
                    return;
                }
                stmt.bindNull(4);
                stmt.bindNull(5);
            }
        };
        this.__deletionAdapterOfEntity_RENT = new EntityDeletionOrUpdateAdapter<Entity_RENT>(__db2) {
            public String createQuery() {
                return "DELETE FROM `RENT` WHERE `RENT_BILL_ID` = ?";
            }

            public void bind(SupportSQLiteStatement stmt, Entity_RENT value) {
                stmt.bindLong(1, (long) value.getRENT_BILL_ID());
            }
        };
        this.__updateAdapterOfEntity_RENT = new EntityDeletionOrUpdateAdapter<Entity_RENT>(__db2) {
            public String createQuery() {
                return "UPDATE OR ABORT `RENT` SET `RENT_BILL_ID` = ?,`HOUSE_NUMBER` = ?,`RENT_PAID` = ?,`MONTH` = ?,`YEAR` = ? WHERE `RENT_BILL_ID` = ?";
            }

            public void bind(SupportSQLiteStatement stmt, Entity_RENT value) {
                stmt.bindLong(1, (long) value.getRENT_BILL_ID());
                stmt.bindLong(2, (long) value.getHOUSE_NUMBER());
                if (value.getRENT_PAID() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindDouble(3, value.getRENT_PAID().doubleValue());
                }
                Time _tmpTime = value.getTime();
                if (_tmpTime != null) {
                    if (_tmpTime.getMONTH() == null) {
                        stmt.bindNull(4);
                    } else {
                        stmt.bindString(4, _tmpTime.getMONTH());
                    }
                    stmt.bindLong(5, (long) _tmpTime.getYEAR());
                } else {
                    stmt.bindNull(4);
                    stmt.bindNull(5);
                }
                stmt.bindLong(6, (long) value.getRENT_BILL_ID());
            }
        };
        this.__preparedStmtOfDelete_rent_bills_per_house = new SharedSQLiteStatement(__db2) {
            public String createQuery() {
                String str = "DELETE FROM RENT WHERE HOUSE_NUMBER IN ( ?)";
                return "DELETE FROM RENT WHERE HOUSE_NUMBER IN ( ?)";
            }
        };
    }

    public void insert_rent(Entity_RENT rent) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfEntity_RENT.insert(rent);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete_rent(Entity_RENT rent) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfEntity_RENT.handle(rent);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update_rent(Entity_RENT rent) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfEntity_RENT.handle(rent);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete_rent_bills_per_house(int house_number) {
        SupportSQLiteStatement _stmt = this.__preparedStmtOfDelete_rent_bills_per_house.acquire();
        this.__db.beginTransaction();
        try {
            _stmt.bindLong(1, (long) house_number);
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete_rent_bills_per_house.release(_stmt);
        }
    }

    public LiveData<List<POJO_RentBills_per_House>> getRentBillsPerHouse(int HOUSE_NUMBER) {
        String str = "SELECT RENT_BILL_ID, RENT.HOUSE_NUMBER, RENT_PER_MONTH, RENT_PAID, MONTH, YEAR FROM RENT INNER JOIN TENANTS ON RENT.HOUSE_NUMBER = TENANTS.HOUSE_NUMBER WHERE RENT.HOUSE_NUMBER IN ( ?)ORDER BY RENT_BILL_ID DESC";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT RENT_BILL_ID, RENT.HOUSE_NUMBER, RENT_PER_MONTH, RENT_PAID, MONTH, YEAR FROM RENT INNER JOIN TENANTS ON RENT.HOUSE_NUMBER = TENANTS.HOUSE_NUMBER WHERE RENT.HOUSE_NUMBER IN ( ?)ORDER BY RENT_BILL_ID DESC", 1);
        _statement.bindLong(1, (long) HOUSE_NUMBER);
        return new ComputableLiveData<List<POJO_RentBills_per_House>>() {
            private Observer _observer;

            /* access modifiers changed from: protected */
            public List<POJO_RentBills_per_House> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("RENT", "TENANTS") {
                        public void onInvalidated(@NonNull Set<String> set) {
                            AnonymousClass5.this.invalidate();
                        }
                    };
                    Dao_RENT_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = Dao_RENT_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfRENTBILLID = _cursor.getColumnIndexOrThrow("RENT_BILL_ID");
                    int _cursorIndexOfHOUSENUMBER = _cursor.getColumnIndexOrThrow("HOUSE_NUMBER");
                    int _cursorIndexOfRENTPERMONTH = _cursor.getColumnIndexOrThrow("RENT_PER_MONTH");
                    int _cursorIndexOfRENTPAID = _cursor.getColumnIndexOrThrow("RENT_PAID");
                    int _cursorIndexOfMONTH = _cursor.getColumnIndexOrThrow("MONTH");
                    int _cursorIndexOfYEAR = _cursor.getColumnIndexOrThrow("YEAR");
                    List<POJO_RentBills_per_House> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        POJO_RentBills_per_House pOJO_RentBills_per_House = new POJO_RentBills_per_House(_cursor.getInt(_cursorIndexOfRENTBILLID), _cursor.getInt(_cursorIndexOfHOUSENUMBER), _cursor.getDouble(_cursorIndexOfRENTPERMONTH), _cursor.getDouble(_cursorIndexOfRENTPAID), _cursor.getString(_cursorIndexOfMONTH), _cursor.getInt(_cursorIndexOfYEAR));
                        _result.add(pOJO_RentBills_per_House);
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

    public LiveData<Entity_RENT> getRentBillById(int rent_bill_id) {
        String str = "SELECT * FROM RENT WHERE RENT_BILL_ID IN ( ?)";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM RENT WHERE RENT_BILL_ID IN ( ?)", 1);
        _statement.bindLong(1, (long) rent_bill_id);
        return new ComputableLiveData<Entity_RENT>() {
            private Observer _observer;

            /* JADX WARNING: type inference failed for: r7v1 */
            /* access modifiers changed from: protected */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.patrickgatirigmail.finale.Entity_RENT compute() {
                /*
                    r11 = this;
                    android.arch.persistence.room.InvalidationTracker$Observer r0 = r11._observer
                    if (r0 != 0) goto L_0x001f
                    com.patrickgatirigmail.finale.Dao_RENT_Impl$6$1 r0 = new com.patrickgatirigmail.finale.Dao_RENT_Impl$6$1
                    java.lang.String r1 = "RENT"
                    r2 = 0
                    java.lang.String[] r2 = new java.lang.String[r2]
                    r0.<init>(r1, r2)
                    r11._observer = r0
                    com.patrickgatirigmail.finale.Dao_RENT_Impl r0 = com.patrickgatirigmail.finale.Dao_RENT_Impl.this
                    android.arch.persistence.room.RoomDatabase r0 = r0.__db
                    android.arch.persistence.room.InvalidationTracker r0 = r0.getInvalidationTracker()
                    android.arch.persistence.room.InvalidationTracker$Observer r1 = r11._observer
                    r0.addWeakObserver(r1)
                L_0x001f:
                    com.patrickgatirigmail.finale.Dao_RENT_Impl r0 = com.patrickgatirigmail.finale.Dao_RENT_Impl.this
                    android.arch.persistence.room.RoomDatabase r0 = r0.__db
                    android.arch.persistence.room.RoomSQLiteQuery r1 = r1
                    android.database.Cursor r0 = r0.query(r1)
                    java.lang.String r1 = "RENT_BILL_ID"
                    int r1 = r0.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x0097 }
                    java.lang.String r2 = "HOUSE_NUMBER"
                    int r2 = r0.getColumnIndexOrThrow(r2)     // Catch:{ all -> 0x0097 }
                    java.lang.String r3 = "RENT_PAID"
                    int r3 = r0.getColumnIndexOrThrow(r3)     // Catch:{ all -> 0x0097 }
                    java.lang.String r4 = "MONTH"
                    int r4 = r0.getColumnIndexOrThrow(r4)     // Catch:{ all -> 0x0097 }
                    java.lang.String r5 = "YEAR"
                    int r5 = r0.getColumnIndexOrThrow(r5)     // Catch:{ all -> 0x0097 }
                    boolean r6 = r0.moveToFirst()     // Catch:{ all -> 0x0097 }
                    r7 = 0
                    if (r6 == 0) goto L_0x0091
                    int r6 = r0.getInt(r2)     // Catch:{ all -> 0x0097 }
                    boolean r8 = r0.isNull(r3)     // Catch:{ all -> 0x0097 }
                    if (r8 == 0) goto L_0x005c
                    r8 = 0
                    goto L_0x0064
                L_0x005c:
                    double r8 = r0.getDouble(r3)     // Catch:{ all -> 0x0097 }
                    java.lang.Double r8 = java.lang.Double.valueOf(r8)     // Catch:{ all -> 0x0097 }
                L_0x0064:
                    boolean r9 = r0.isNull(r4)     // Catch:{ all -> 0x0097 }
                    if (r9 == 0) goto L_0x0072
                    boolean r9 = r0.isNull(r5)     // Catch:{ all -> 0x0097 }
                    if (r9 != 0) goto L_0x0071
                    goto L_0x0072
                L_0x0071:
                    goto L_0x0081
                L_0x0072:
                    java.lang.String r7 = r0.getString(r4)     // Catch:{ all -> 0x0097 }
                    int r9 = r0.getInt(r5)     // Catch:{ all -> 0x0097 }
                    com.patrickgatirigmail.finale.Time r10 = new com.patrickgatirigmail.finale.Time     // Catch:{ all -> 0x0097 }
                    r10.<init>(r7, r9)     // Catch:{ all -> 0x0097 }
                    r7 = r10
                L_0x0081:
                    com.patrickgatirigmail.finale.Entity_RENT r9 = new com.patrickgatirigmail.finale.Entity_RENT     // Catch:{ all -> 0x0097 }
                    r9.<init>(r6, r7, r8)     // Catch:{ all -> 0x0097 }
                    int r10 = r0.getInt(r1)     // Catch:{ all -> 0x0097 }
                    r9.setRENT_BILL_ID(r10)     // Catch:{ all -> 0x0097 }
                    r7 = r9
                L_0x0091:
                    r6 = r7
                    r0.close()
                    return r6
                L_0x0097:
                    r1 = move-exception
                    r0.close()
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.patrickgatirigmail.finale.Dao_RENT_Impl.AnonymousClass6.compute():com.patrickgatirigmail.finale.Entity_RENT");
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }
}
