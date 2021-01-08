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
import java.util.List;

public class Dao_WATER_Impl implements Dao_WATER {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfEntity_WATER;
    private final EntityInsertionAdapter __insertionAdapterOfEntity_WATER;
    private final SharedSQLiteStatement __preparedStmtOfDelete_water_bills_for_house;
    private final SharedSQLiteStatement __preparedStmtOfUpdate_next_water_bill;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfEntity_WATER;

    public Dao_WATER_Impl(RoomDatabase __db2) {
        this.__db = __db2;
        this.__insertionAdapterOfEntity_WATER = new EntityInsertionAdapter<Entity_WATER>(__db2) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `WATER`(`WATER_BILL_ID`,`HOUSE_NUMBER`,`AMOUNT_PAID`,`MONTH`,`YEAR`,`CURRENT_READING`,`PREVIOUS_READING`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement stmt, Entity_WATER value) {
                stmt.bindLong(1, (long) value.getWATER_BILL_ID());
                stmt.bindLong(2, (long) value.getHOUSE_NUMBER());
                stmt.bindDouble(3, value.getAMOUNT_PAID());
                Time_Of_Payment _tmpTime_of_payment = value.getTime_of_payment();
                if (_tmpTime_of_payment != null) {
                    if (_tmpTime_of_payment.getMONTH() == null) {
                        stmt.bindNull(4);
                    } else {
                        stmt.bindString(4, _tmpTime_of_payment.getMONTH());
                    }
                    stmt.bindLong(5, (long) _tmpTime_of_payment.getYEAR());
                } else {
                    stmt.bindNull(4);
                    stmt.bindNull(5);
                }
                WATER_READINGS _tmpWater_readings = value.getWater_readings();
                if (_tmpWater_readings != null) {
                    stmt.bindDouble(6, _tmpWater_readings.getCURRENT_READING());
                    stmt.bindDouble(7, _tmpWater_readings.getPREVIOUS_READING());
                    return;
                }
                stmt.bindNull(6);
                stmt.bindNull(7);
            }
        };
        this.__deletionAdapterOfEntity_WATER = new EntityDeletionOrUpdateAdapter<Entity_WATER>(__db2) {
            public String createQuery() {
                return "DELETE FROM `WATER` WHERE `WATER_BILL_ID` = ?";
            }

            public void bind(SupportSQLiteStatement stmt, Entity_WATER value) {
                stmt.bindLong(1, (long) value.getWATER_BILL_ID());
            }
        };
        this.__updateAdapterOfEntity_WATER = new EntityDeletionOrUpdateAdapter<Entity_WATER>(__db2) {
            public String createQuery() {
                return "UPDATE OR ABORT `WATER` SET `WATER_BILL_ID` = ?,`HOUSE_NUMBER` = ?,`AMOUNT_PAID` = ?,`MONTH` = ?,`YEAR` = ?,`CURRENT_READING` = ?,`PREVIOUS_READING` = ? WHERE `WATER_BILL_ID` = ?";
            }

            public void bind(SupportSQLiteStatement stmt, Entity_WATER value) {
                stmt.bindLong(1, (long) value.getWATER_BILL_ID());
                stmt.bindLong(2, (long) value.getHOUSE_NUMBER());
                stmt.bindDouble(3, value.getAMOUNT_PAID());
                Time_Of_Payment _tmpTime_of_payment = value.getTime_of_payment();
                if (_tmpTime_of_payment != null) {
                    if (_tmpTime_of_payment.getMONTH() == null) {
                        stmt.bindNull(4);
                    } else {
                        stmt.bindString(4, _tmpTime_of_payment.getMONTH());
                    }
                    stmt.bindLong(5, (long) _tmpTime_of_payment.getYEAR());
                } else {
                    stmt.bindNull(4);
                    stmt.bindNull(5);
                }
                WATER_READINGS _tmpWater_readings = value.getWater_readings();
                if (_tmpWater_readings != null) {
                    stmt.bindDouble(6, _tmpWater_readings.getCURRENT_READING());
                    stmt.bindDouble(7, _tmpWater_readings.getPREVIOUS_READING());
                } else {
                    stmt.bindNull(6);
                    stmt.bindNull(7);
                }
                stmt.bindLong(8, (long) value.getWATER_BILL_ID());
            }
        };
        this.__preparedStmtOfDelete_water_bills_for_house = new SharedSQLiteStatement(__db2) {
            public String createQuery() {
                String str = "DELETE FROM WATER WHERE HOUSE_NUMBER IN( ?)";
                return "DELETE FROM WATER WHERE HOUSE_NUMBER IN( ?)";
            }
        };
        this.__preparedStmtOfUpdate_next_water_bill = new SharedSQLiteStatement(__db2) {
            public String createQuery() {
                String str = "UPDATE WATER SET PREVIOUS_READING = ( ?)WHERE WATER_BILL_ID IN ( ?)";
                return "UPDATE WATER SET PREVIOUS_READING = ( ?)WHERE WATER_BILL_ID IN ( ?)";
            }
        };
    }

    public void insert_water(Entity_WATER water) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfEntity_WATER.insert(water);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete_water(Entity_WATER water) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfEntity_WATER.handle(water);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update_water(Entity_WATER water) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfEntity_WATER.handle(water);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete_water_bills_for_house(int house_number) {
        SupportSQLiteStatement _stmt = this.__preparedStmtOfDelete_water_bills_for_house.acquire();
        this.__db.beginTransaction();
        try {
            _stmt.bindLong(1, (long) house_number);
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete_water_bills_for_house.release(_stmt);
        }
    }

    public void update_next_water_bill(double previous_reading, int next_water_bill_id) {
        SupportSQLiteStatement _stmt = this.__preparedStmtOfUpdate_next_water_bill.acquire();
        this.__db.beginTransaction();
        try {
            _stmt.bindDouble(1, previous_reading);
            _stmt.bindLong(2, (long) next_water_bill_id);
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdate_next_water_bill.release(_stmt);
        }
    }

    public LiveData<List<Entity_WATER>> getWaterBillsPerHouse(int HOUSE_NUMBER) {
        String str = "SELECT * FROM WATER WHERE HOUSE_NUMBER IN ( ?)ORDER BY WATER_BILL_ID DESC";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM WATER WHERE HOUSE_NUMBER IN ( ?)ORDER BY WATER_BILL_ID DESC", 1);
        _statement.bindLong(1, (long) HOUSE_NUMBER);
        return new ComputableLiveData<List<Entity_WATER>>() {
            private Observer _observer;

            /* access modifiers changed from: protected */
            /* JADX WARNING: Removed duplicated region for block: B:17:0x0094 A[Catch:{ all -> 0x00d7 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.util.List<com.patrickgatirigmail.finale.Entity_WATER> compute() {
                /*
                    r24 = this;
                    r1 = r24
                    android.arch.persistence.room.InvalidationTracker$Observer r2 = r1._observer
                    if (r2 != 0) goto L_0x0021
                    com.patrickgatirigmail.finale.Dao_WATER_Impl$6$1 r2 = new com.patrickgatirigmail.finale.Dao_WATER_Impl$6$1
                    java.lang.String r3 = "WATER"
                    r4 = 0
                    java.lang.String[] r4 = new java.lang.String[r4]
                    r2.<init>(r3, r4)
                    r1._observer = r2
                    com.patrickgatirigmail.finale.Dao_WATER_Impl r2 = com.patrickgatirigmail.finale.Dao_WATER_Impl.this
                    android.arch.persistence.room.RoomDatabase r2 = r2.__db
                    android.arch.persistence.room.InvalidationTracker r2 = r2.getInvalidationTracker()
                    android.arch.persistence.room.InvalidationTracker$Observer r3 = r1._observer
                    r2.addWeakObserver(r3)
                L_0x0021:
                    com.patrickgatirigmail.finale.Dao_WATER_Impl r2 = com.patrickgatirigmail.finale.Dao_WATER_Impl.this
                    android.arch.persistence.room.RoomDatabase r2 = r2.__db
                    android.arch.persistence.room.RoomSQLiteQuery r3 = r1
                    android.database.Cursor r2 = r2.query(r3)
                    java.lang.String r3 = "WATER_BILL_ID"
                    int r3 = r2.getColumnIndexOrThrow(r3)     // Catch:{ all -> 0x00d7 }
                    java.lang.String r4 = "HOUSE_NUMBER"
                    int r4 = r2.getColumnIndexOrThrow(r4)     // Catch:{ all -> 0x00d7 }
                    java.lang.String r5 = "AMOUNT_PAID"
                    int r5 = r2.getColumnIndexOrThrow(r5)     // Catch:{ all -> 0x00d7 }
                    java.lang.String r6 = "MONTH"
                    int r6 = r2.getColumnIndexOrThrow(r6)     // Catch:{ all -> 0x00d7 }
                    java.lang.String r7 = "YEAR"
                    int r7 = r2.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x00d7 }
                    java.lang.String r8 = "CURRENT_READING"
                    int r8 = r2.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x00d7 }
                    java.lang.String r9 = "PREVIOUS_READING"
                    int r9 = r2.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x00d7 }
                    java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x00d7 }
                    int r11 = r2.getCount()     // Catch:{ all -> 0x00d7 }
                    r10.<init>(r11)     // Catch:{ all -> 0x00d7 }
                L_0x0060:
                    boolean r11 = r2.moveToNext()     // Catch:{ all -> 0x00d7 }
                    if (r11 == 0) goto L_0x00cf
                    int r13 = r2.getInt(r4)     // Catch:{ all -> 0x00d7 }
                    double r16 = r2.getDouble(r5)     // Catch:{ all -> 0x00d7 }
                    boolean r11 = r2.isNull(r6)     // Catch:{ all -> 0x00d7 }
                    r12 = 0
                    if (r11 == 0) goto L_0x007e
                    boolean r11 = r2.isNull(r7)     // Catch:{ all -> 0x00d7 }
                    if (r11 != 0) goto L_0x007c
                    goto L_0x007e
                L_0x007c:
                    r14 = r12
                    goto L_0x008e
                L_0x007e:
                    java.lang.String r11 = r2.getString(r6)     // Catch:{ all -> 0x00d7 }
                    int r14 = r2.getInt(r7)     // Catch:{ all -> 0x00d7 }
                    com.patrickgatirigmail.finale.Time_Of_Payment r15 = new com.patrickgatirigmail.finale.Time_Of_Payment     // Catch:{ all -> 0x00d7 }
                    r15.<init>(r11, r14)     // Catch:{ all -> 0x00d7 }
                    r11 = r15
                    r14 = r11
                L_0x008e:
                    boolean r11 = r2.isNull(r8)     // Catch:{ all -> 0x00d7 }
                    if (r11 == 0) goto L_0x00a1
                    boolean r11 = r2.isNull(r9)     // Catch:{ all -> 0x00d7 }
                    if (r11 != 0) goto L_0x009b
                    goto L_0x00a1
                L_0x009b:
                    r22 = r4
                    r23 = r5
                    r15 = r12
                    goto L_0x00b8
                L_0x00a1:
                    double r11 = r2.getDouble(r8)     // Catch:{ all -> 0x00d7 }
                    double r18 = r2.getDouble(r9)     // Catch:{ all -> 0x00d7 }
                    r20 = r18
                    com.patrickgatirigmail.finale.WATER_READINGS r15 = new com.patrickgatirigmail.finale.WATER_READINGS     // Catch:{ all -> 0x00d7 }
                    r22 = r4
                    r23 = r5
                    r4 = r20
                    r15.<init>(r11, r4)     // Catch:{ all -> 0x00d7 }
                    r4 = r15
                L_0x00b8:
                    com.patrickgatirigmail.finale.Entity_WATER r4 = new com.patrickgatirigmail.finale.Entity_WATER     // Catch:{ all -> 0x00d7 }
                    r12 = r4
                    r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x00d7 }
                    int r5 = r2.getInt(r3)     // Catch:{ all -> 0x00d7 }
                    r4.setWATER_BILL_ID(r5)     // Catch:{ all -> 0x00d7 }
                    r10.add(r4)     // Catch:{ all -> 0x00d7 }
                    r4 = r22
                    r5 = r23
                    goto L_0x0060
                L_0x00cf:
                    r22 = r4
                    r23 = r5
                    r2.close()
                    return r10
                L_0x00d7:
                    r0 = move-exception
                    r3 = r0
                    r2.close()
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.patrickgatirigmail.finale.Dao_WATER_Impl.AnonymousClass6.compute():java.util.List");
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }

    public LiveData<Entity_WATER> getWaterBillById(int waterbillid) {
        String str = "SELECT * FROM WATER WHERE WATER_BILL_ID IN ( ?) ";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM WATER WHERE WATER_BILL_ID IN ( ?) ", 1);
        _statement.bindLong(1, (long) waterbillid);
        return new ComputableLiveData<Entity_WATER>() {
            private Observer _observer;

            /* JADX WARNING: type inference failed for: r11v0 */
            /* JADX WARNING: type inference failed for: r11v1 */
            /* JADX WARNING: type inference failed for: r12v0, types: [com.patrickgatirigmail.finale.WATER_READINGS] */
            /* JADX WARNING: type inference failed for: r11v2 */
            /* JADX WARNING: type inference failed for: r11v3 */
            /* JADX WARNING: type inference failed for: r11v5 */
            /* JADX WARNING: type inference failed for: r11v6 */
            /* JADX WARNING: type inference failed for: r11v7 */
            /* access modifiers changed from: protected */
            /* JADX WARNING: Multi-variable type inference failed */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x008b A[Catch:{ all -> 0x00c9 }] */
            /* JADX WARNING: Unknown variable types count: 4 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.patrickgatirigmail.finale.Entity_WATER compute() {
                /*
                    r24 = this;
                    r1 = r24
                    android.arch.persistence.room.InvalidationTracker$Observer r2 = r1._observer
                    if (r2 != 0) goto L_0x0021
                    com.patrickgatirigmail.finale.Dao_WATER_Impl$7$1 r2 = new com.patrickgatirigmail.finale.Dao_WATER_Impl$7$1
                    java.lang.String r3 = "WATER"
                    r4 = 0
                    java.lang.String[] r4 = new java.lang.String[r4]
                    r2.<init>(r3, r4)
                    r1._observer = r2
                    com.patrickgatirigmail.finale.Dao_WATER_Impl r2 = com.patrickgatirigmail.finale.Dao_WATER_Impl.this
                    android.arch.persistence.room.RoomDatabase r2 = r2.__db
                    android.arch.persistence.room.InvalidationTracker r2 = r2.getInvalidationTracker()
                    android.arch.persistence.room.InvalidationTracker$Observer r3 = r1._observer
                    r2.addWeakObserver(r3)
                L_0x0021:
                    com.patrickgatirigmail.finale.Dao_WATER_Impl r2 = com.patrickgatirigmail.finale.Dao_WATER_Impl.this
                    android.arch.persistence.room.RoomDatabase r2 = r2.__db
                    android.arch.persistence.room.RoomSQLiteQuery r3 = r1
                    android.database.Cursor r2 = r2.query(r3)
                    java.lang.String r3 = "WATER_BILL_ID"
                    int r3 = r2.getColumnIndexOrThrow(r3)     // Catch:{ all -> 0x00c9 }
                    java.lang.String r4 = "HOUSE_NUMBER"
                    int r4 = r2.getColumnIndexOrThrow(r4)     // Catch:{ all -> 0x00c9 }
                    java.lang.String r5 = "AMOUNT_PAID"
                    int r5 = r2.getColumnIndexOrThrow(r5)     // Catch:{ all -> 0x00c9 }
                    java.lang.String r6 = "MONTH"
                    int r6 = r2.getColumnIndexOrThrow(r6)     // Catch:{ all -> 0x00c9 }
                    java.lang.String r7 = "YEAR"
                    int r7 = r2.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x00c9 }
                    java.lang.String r8 = "CURRENT_READING"
                    int r8 = r2.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x00c9 }
                    java.lang.String r9 = "PREVIOUS_READING"
                    int r9 = r2.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x00c9 }
                    boolean r10 = r2.moveToFirst()     // Catch:{ all -> 0x00c9 }
                    r11 = 0
                    if (r10 == 0) goto L_0x00bf
                    int r13 = r2.getInt(r4)     // Catch:{ all -> 0x00c9 }
                    double r16 = r2.getDouble(r5)     // Catch:{ all -> 0x00c9 }
                    boolean r10 = r2.isNull(r6)     // Catch:{ all -> 0x00c9 }
                    if (r10 == 0) goto L_0x0075
                    boolean r10 = r2.isNull(r7)     // Catch:{ all -> 0x00c9 }
                    if (r10 != 0) goto L_0x0073
                    goto L_0x0075
                L_0x0073:
                    r14 = r11
                    goto L_0x0084
                L_0x0075:
                    java.lang.String r10 = r2.getString(r6)     // Catch:{ all -> 0x00c9 }
                    int r12 = r2.getInt(r7)     // Catch:{ all -> 0x00c9 }
                    com.patrickgatirigmail.finale.Time_Of_Payment r14 = new com.patrickgatirigmail.finale.Time_Of_Payment     // Catch:{ all -> 0x00c9 }
                    r14.<init>(r10, r12)     // Catch:{ all -> 0x00c9 }
                    r10 = r14
                L_0x0084:
                    boolean r10 = r2.isNull(r8)     // Catch:{ all -> 0x00c9 }
                    if (r10 == 0) goto L_0x0098
                    boolean r10 = r2.isNull(r9)     // Catch:{ all -> 0x00c9 }
                    if (r10 != 0) goto L_0x0092
                    goto L_0x0098
                L_0x0092:
                    r22 = r4
                    r23 = r5
                L_0x0096:
                    r15 = r11
                    goto L_0x00af
                L_0x0098:
                    double r10 = r2.getDouble(r8)     // Catch:{ all -> 0x00c9 }
                    double r18 = r2.getDouble(r9)     // Catch:{ all -> 0x00c9 }
                    r20 = r18
                    com.patrickgatirigmail.finale.WATER_READINGS r12 = new com.patrickgatirigmail.finale.WATER_READINGS     // Catch:{ all -> 0x00c9 }
                    r22 = r4
                    r23 = r5
                    r4 = r20
                    r12.<init>(r10, r4)     // Catch:{ all -> 0x00c9 }
                    r11 = r12
                    goto L_0x0096
                L_0x00af:
                    com.patrickgatirigmail.finale.Entity_WATER r4 = new com.patrickgatirigmail.finale.Entity_WATER     // Catch:{ all -> 0x00c9 }
                    r12 = r4
                    r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x00c9 }
                    r11 = r4
                    int r4 = r2.getInt(r3)     // Catch:{ all -> 0x00c9 }
                    r11.setWATER_BILL_ID(r4)     // Catch:{ all -> 0x00c9 }
                    goto L_0x00c3
                L_0x00bf:
                    r22 = r4
                    r23 = r5
                L_0x00c3:
                    r4 = r11
                    r2.close()
                    return r4
                L_0x00c9:
                    r0 = move-exception
                    r3 = r0
                    r2.close()
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.patrickgatirigmail.finale.Dao_WATER_Impl.AnonymousClass7.compute():com.patrickgatirigmail.finale.Entity_WATER");
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }
}
