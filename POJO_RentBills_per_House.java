package com.patrickgatirigmail.finale;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class POJO_RentBills_per_House implements Parcelable {
    public static final Creator<POJO_RentBills_per_House> CREATOR = new Creator<POJO_RentBills_per_House>() {
        public POJO_RentBills_per_House createFromParcel(Parcel in) {
            return new POJO_RentBills_per_House(in);
        }

        public POJO_RentBills_per_House[] newArray(int size) {
            return new POJO_RentBills_per_House[size];
        }
    };
    private int HOUSE_NUMBER;
    private String MONTH;
    private int RENT_BILL_ID;
    private double RENT_PAID;
    private double RENT_PER_MONTH;
    private int YEAR;

    POJO_RentBills_per_House(int RENT_BILL_ID2, int HOUSE_NUMBER2, double RENT_PER_MONTH2, double RENT_PAID2, String MONTH2, int YEAR2) {
        this.RENT_BILL_ID = RENT_BILL_ID2;
        this.HOUSE_NUMBER = HOUSE_NUMBER2;
        this.RENT_PER_MONTH = RENT_PER_MONTH2;
        this.RENT_PAID = RENT_PAID2;
        this.MONTH = MONTH2;
        this.YEAR = YEAR2;
    }

    protected POJO_RentBills_per_House(Parcel in) {
        this.RENT_BILL_ID = in.readInt();
        this.HOUSE_NUMBER = in.readInt();
        this.RENT_PER_MONTH = in.readDouble();
        this.RENT_PAID = in.readDouble();
        this.MONTH = in.readString();
        this.YEAR = in.readInt();
    }

    public int getRENT_BILL_ID() {
        return this.RENT_BILL_ID;
    }

    public int getHOUSE_NUMBER() {
        return this.HOUSE_NUMBER;
    }

    public double getRENT_PER_MONTH() {
        return this.RENT_PER_MONTH;
    }

    public double getRENT_PAID() {
        return this.RENT_PAID;
    }

    public double getBALANCE() {
        return this.RENT_PER_MONTH - this.RENT_PAID;
    }

    public String getMONTH() {
        return this.MONTH;
    }

    public int getYEAR() {
        return this.YEAR;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("Month: ");
        sb.append(getMONTH());
        sb.append(".\n\n");
        String pojo_rent_bills_per_house = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(pojo_rent_bills_per_house);
        sb2.append("Year: ");
        sb2.append(String.valueOf(getYEAR()));
        sb2.append(".\n\n");
        String pojo_rent_bills_per_house2 = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(pojo_rent_bills_per_house2);
        sb3.append("Rent due: Ksh. ");
        sb3.append(String.valueOf(getRENT_PER_MONTH()));
        sb3.append(".\n\n");
        String pojo_rent_bills_per_house3 = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(pojo_rent_bills_per_house3);
        sb4.append("Rent paid: Ksh. ");
        sb4.append(String.valueOf(getRENT_PAID()));
        sb4.append(".\n\n");
        String pojo_rent_bills_per_house4 = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(pojo_rent_bills_per_house4);
        sb5.append("Balance: Ksh. ");
        sb5.append(String.valueOf(getBALANCE()));
        sb5.append(".\n\n");
        return sb5.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.RENT_BILL_ID);
        parcel.writeInt(this.HOUSE_NUMBER);
        parcel.writeDouble(this.RENT_PER_MONTH);
        parcel.writeDouble(this.RENT_PAID);
        parcel.writeString(this.MONTH);
        parcel.writeInt(this.YEAR);
    }
}
