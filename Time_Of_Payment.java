package com.patrickgatirigmail.finale;

import android.arch.persistence.room.ColumnInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: Entity_WATER */
class Time_Of_Payment implements Parcelable {
    public static final Creator<Time_Of_Payment> CREATOR = new Creator<Time_Of_Payment>() {
        public Time_Of_Payment createFromParcel(Parcel in) {
            return new Time_Of_Payment(in);
        }

        public Time_Of_Payment[] newArray(int size) {
            return new Time_Of_Payment[size];
        }
    };
    @ColumnInfo(name = "MONTH")
    private String MONTH;
    @ColumnInfo(name = "YEAR")
    private int YEAR;

    public Time_Of_Payment(String MONTH2, int YEAR2) {
        this.MONTH = MONTH2;
        this.YEAR = YEAR2;
    }

    protected Time_Of_Payment(Parcel in) {
        this.MONTH = in.readString();
        this.YEAR = in.readInt();
    }

    public String getMONTH() {
        return this.MONTH;
    }

    public int getYEAR() {
        return this.YEAR;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.MONTH);
        parcel.writeInt(this.YEAR);
    }
}
