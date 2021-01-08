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

@Entity(foreignKeys = {@ForeignKey(childColumns = {"HOUSE_NUMBER"}, entity = Entity_TENANTS.class, onDelete = 2, onUpdate = 2, parentColumns = {"HOUSE_NUMBER"})}, indices = {@Index({"HOUSE_NUMBER"})}, tableName = "WATER")
public class Entity_WATER implements Parcelable {
    public static final String AFFECTED_WATER_BILL_ID = "AFFECTED_WATER_BILL_ID";
    public static final Creator<Entity_WATER> CREATOR = new Creator<Entity_WATER>() {
        public Entity_WATER createFromParcel(Parcel in) {
            return new Entity_WATER(in);
        }

        public Entity_WATER[] newArray(int size) {
            return new Entity_WATER[size];
        }
    };
    public static final String DELETE_WATER_BILLS_FOR_HOUSE_EXTRA_NAME = "DELETE_WATER_BILLS_FOR_HOUSE_EXTRA_NAME";
    public static final String DELETE_WATER_PARCELABLE_NAME = "DELETE_WATER_PARCELABLE_NAME";
    public static final String NEW_WATER_PARCELABLE_NAME = "NEW_WATER_PARCELABLE_NAME";
    public static final String PREVIOUS_WATER_READING = "PREVIOUS_WATER_READING";
    public static final String UPDATE_WATER_PARCELABLE_NAME = "UPDATE_WATER_PARCELABLE_NAME";
    @ColumnInfo(name = "AMOUNT_PAID")
    private double AMOUNT_PAID;
    @ColumnInfo(name = "HOUSE_NUMBER")
    private int HOUSE_NUMBER;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "WATER_BILL_ID")
    private int WATER_BILL_ID;
    @Embedded
    private Time_Of_Payment time_of_payment;
    @Embedded
    private WATER_READINGS water_readings;

    public Entity_WATER(int HOUSE_NUMBER2, Time_Of_Payment time_of_payment2, WATER_READINGS water_readings2, double AMOUNT_PAID2) {
        this.HOUSE_NUMBER = HOUSE_NUMBER2;
        this.time_of_payment = time_of_payment2;
        this.water_readings = water_readings2;
        this.AMOUNT_PAID = AMOUNT_PAID2;
    }

    protected Entity_WATER(Parcel in) {
        this.WATER_BILL_ID = in.readInt();
        this.HOUSE_NUMBER = in.readInt();
        this.time_of_payment = (Time_Of_Payment) in.readParcelable(Time_Of_Payment.class.getClassLoader());
        this.water_readings = (WATER_READINGS) in.readParcelable(WATER_READINGS.class.getClassLoader());
        this.AMOUNT_PAID = in.readDouble();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.WATER_BILL_ID);
        dest.writeInt(this.HOUSE_NUMBER);
        dest.writeParcelable(this.time_of_payment, flags);
        dest.writeParcelable(this.water_readings, flags);
        dest.writeDouble(this.AMOUNT_PAID);
    }

    public int describeContents() {
        return 0;
    }

    public int getWATER_BILL_ID() {
        return this.WATER_BILL_ID;
    }

    public void setWATER_BILL_ID(int WATER_BILL_ID2) {
        this.WATER_BILL_ID = WATER_BILL_ID2;
    }

    public int getHOUSE_NUMBER() {
        return this.HOUSE_NUMBER;
    }

    public Time_Of_Payment getTime_of_payment() {
        return this.time_of_payment;
    }

    public WATER_READINGS getWater_readings() {
        return this.water_readings;
    }

    public double getAMOUNT_PAID() {
        return this.AMOUNT_PAID;
    }

    public double getBALANCE() {
        return this.water_readings.getAMOUNT_DUE() - this.AMOUNT_PAID;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(" Month: ");
        sb.append(getTime_of_payment().getMONTH());
        sb.append(". \n\n");
        String entity_water = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(entity_water);
        sb2.append(" Year: ");
        sb2.append(getTime_of_payment().getYEAR());
        sb2.append(". \n\n");
        String entity_water2 = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(entity_water2);
        sb3.append(" Current reading: ");
        sb3.append(getWater_readings().getCURRENT_READING());
        sb3.append(" units. \n\n");
        String entity_water3 = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(entity_water3);
        sb4.append(" Previous reading: ");
        sb4.append(getWater_readings().getPREVIOUS_READING());
        sb4.append(" units. \n\n");
        String entity_water4 = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(entity_water4);
        sb5.append(" Units spent: ");
        sb5.append(String.format("%.2f", new Object[]{Double.valueOf(getWater_readings().getUNITS_SPENT())}));
        sb5.append(" units. \n\n");
        String entity_water5 = sb5.toString();
        StringBuilder sb6 = new StringBuilder();
        sb6.append(entity_water5);
        sb6.append(" Price per unit: Ksh. 130. \n\n");
        String entity_water6 = sb6.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(entity_water6);
        sb7.append(" Garbage cost: Ksh: 200. \n\n");
        String entity_water7 = sb7.toString();
        StringBuilder sb8 = new StringBuilder();
        sb8.append(entity_water7);
        sb8.append(" Total amount due: Ksh: ");
        sb8.append(String.format("%.2f", new Object[]{Double.valueOf(getWater_readings().getAMOUNT_DUE())}));
        sb8.append(". \n\n");
        String entity_water8 = sb8.toString();
        StringBuilder sb9 = new StringBuilder();
        sb9.append(entity_water8);
        sb9.append(" Amount paid: Ksh: ");
        sb9.append(getAMOUNT_PAID());
        sb9.append(". \n\n");
        String entity_water9 = sb9.toString();
        StringBuilder sb10 = new StringBuilder();
        sb10.append(entity_water9);
        sb10.append(" Balance: Ksh: ");
        sb10.append(String.format("%.2f", new Object[]{Double.valueOf(getBALANCE())}));
        sb10.append(". \n\n");
        return sb10.toString();
    }
}
