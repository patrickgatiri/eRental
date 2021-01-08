package com.patrickgatirigmail.finale;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

@Entity(foreignKeys = {@ForeignKey(childColumns = {"HOUSE_NUMBER"}, entity = Entity_TENANTS.class, onDelete = 2, onUpdate = 2, parentColumns = {"HOUSE_NUMBER"})}, indices = {@Index({"HOUSE_NUMBER"})}, tableName = "RENT")
public class Entity_RENT implements Parcelable {
    public static final Creator<Entity_RENT> CREATOR = new Creator<Entity_RENT>() {
        public Entity_RENT createFromParcel(Parcel in) {
            return new Entity_RENT(in);
        }

        public Entity_RENT[] newArray(int size) {
            return new Entity_RENT[size];
        }
    };
    public static final String DELETE_RENT_BILLS_FOR_HOUSE_EXTRA_NAME = "DELETE_RENT_BILLS_FOR_HOUSE_EXTRA_NAME";
    public static final String DELETE_RENT_PARCELABLE_NAME = "DELETE_RENT_PARCELABLE_NAME";
    public static final String NEW_RENT_PARCELABLE_NAME = "NEW_RENT_PARCELABLE_NAME";
    public static final String UPDATE_RENT_PARCELABLE_NAME = "UPDATE_RENT_PARCELABLE_NAME";
    @ColumnInfo(name = "HOUSE_NUMBER")
    private int HOUSE_NUMBER;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "RENT_BILL_ID")
    private int RENT_BILL_ID;
    @ColumnInfo(name = "RENT_PAID")
    private Double RENT_PAID;
    @Embedded
    private Time time;

    public Entity_RENT(int HOUSE_NUMBER2, Time time2, Double RENT_PAID2) {
        this.HOUSE_NUMBER = HOUSE_NUMBER2;
        this.time = time2;
        this.RENT_PAID = RENT_PAID2;
    }

    protected Entity_RENT(Parcel in) {
        this.RENT_BILL_ID = in.readInt();
        this.HOUSE_NUMBER = in.readInt();
        this.time = (Time) in.readParcelable(Time.class.getClassLoader());
        if (in.readByte() == 0) {
            this.RENT_PAID = null;
        } else {
            this.RENT_PAID = Double.valueOf(in.readDouble());
        }
    }

    public int getRENT_BILL_ID() {
        return this.RENT_BILL_ID;
    }

    public void setRENT_BILL_ID(int RENT_BILL_ID2) {
        this.RENT_BILL_ID = RENT_BILL_ID2;
    }

    public int getHOUSE_NUMBER() {
        return this.HOUSE_NUMBER;
    }

    public Time getTime() {
        return this.time;
    }

    public Double getRENT_PAID() {
        return this.RENT_PAID;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.RENT_BILL_ID);
        parcel.writeInt(this.HOUSE_NUMBER);
        parcel.writeParcelable(this.time, i);
        if (this.RENT_PAID == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeDouble(this.RENT_PAID.doubleValue());
    }
}
