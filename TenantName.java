package com.patrickgatirigmail.finale;

import android.arch.persistence.room.ColumnInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: Entity_TENANTS */
class TenantName implements Parcelable {
    public static final Creator<TenantName> CREATOR = new Creator<TenantName>() {
        public TenantName createFromParcel(Parcel in) {
            return new TenantName(in);
        }

        public TenantName[] newArray(int size) {
            return new TenantName[size];
        }
    };
    @ColumnInfo(name = "FIRST_NAME")
    private String FIRST_NAME;
    @ColumnInfo(name = "LAST_NAME")
    private String LAST_NAME;

    public TenantName(String FIRST_NAME2, String LAST_NAME2) {
        this.FIRST_NAME = FIRST_NAME2;
        this.LAST_NAME = LAST_NAME2;
    }

    protected TenantName(Parcel in) {
        this.FIRST_NAME = in.readString();
        this.LAST_NAME = in.readString();
    }

    public String getFIRST_NAME() {
        return this.FIRST_NAME;
    }

    public String getLAST_NAME() {
        return this.LAST_NAME;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.FIRST_NAME);
        parcel.writeString(this.LAST_NAME);
    }
}
