package com.patrickgatirigmail.finale;

import android.arch.persistence.room.ColumnInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: Entity_RENT */
class Time implements Parcelable {
    public static final Creator<Time> CREATOR = new Creator<Time>() {
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        public Time[] newArray(int size) {
            return new Time[size];
        }
    };
    @ColumnInfo(name = "MONTH")
    private String MONTH;
    @ColumnInfo(name = "YEAR")
    private int YEAR;

    public Time(String MONTH2, int YEAR2) {
        this.MONTH = MONTH2;
        this.YEAR = YEAR2;
    }

    protected Time(Parcel in) {
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
