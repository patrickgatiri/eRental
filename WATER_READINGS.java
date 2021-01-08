package com.patrickgatirigmail.finale;

import android.arch.persistence.room.ColumnInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: Entity_WATER */
class WATER_READINGS implements Parcelable {
    public static final Creator<WATER_READINGS> CREATOR = new Creator<WATER_READINGS>() {
        public WATER_READINGS createFromParcel(Parcel in) {
            return new WATER_READINGS(in);
        }

        public WATER_READINGS[] newArray(int size) {
            return new WATER_READINGS[size];
        }
    };
    public static final int GARBAGE = 200;
    public static final int UNIT_PRICE = 130;
    @ColumnInfo(name = "CURRENT_READING")
    private double CURRENT_READING;
    @ColumnInfo(name = "PREVIOUS_READING")
    private double PREVIOUS_READING;

    public WATER_READINGS(double CURRENT_READING2, double PREVIOUS_READING2) {
        this.CURRENT_READING = CURRENT_READING2;
        this.PREVIOUS_READING = PREVIOUS_READING2;
    }

    protected WATER_READINGS(Parcel in) {
        this.CURRENT_READING = in.readDouble();
        this.PREVIOUS_READING = in.readDouble();
    }

    public double getCURRENT_READING() {
        return this.CURRENT_READING;
    }

    public double getPREVIOUS_READING() {
        return this.PREVIOUS_READING;
    }

    public double getUNITS_SPENT() {
        return this.CURRENT_READING - this.PREVIOUS_READING;
    }

    public double getAMOUNT_DUE() {
        return (getUNITS_SPENT() * 130.0d) + 200.0d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.CURRENT_READING);
        parcel.writeDouble(this.PREVIOUS_READING);
    }
}
