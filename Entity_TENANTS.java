package com.patrickgatirigmail.finale;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import javax.annotation.Nonnegative;

@Entity(indices = {@Index(unique = true, value = {"HOUSE_NUMBER"}), @Index(unique = true, value = {"PHONE_NUMBER"})}, tableName = "TENANTS")
public class Entity_TENANTS implements Parcelable {
    public static final Creator<Entity_TENANTS> CREATOR = new Creator<Entity_TENANTS>() {
        public Entity_TENANTS createFromParcel(Parcel in) {
            return new Entity_TENANTS(in);
        }

        public Entity_TENANTS[] newArray(int size) {
            return new Entity_TENANTS[size];
        }
    };
    public static final String DELETE_TENANT_PARCELABLE_NAME = "DELETE_TENANT_PARCELABLE_NAME";
    public static final String NEW_TENANT_PARCELABLE_NAME = "NEW_TENANT_PARCELABLE_NAME";
    public static final String UPDATE_TENANT_PARCELABLE_NAME = "UPDATE_TENANT_PARCELABLE_NAME";
    @ColumnInfo(name = "HOUSE_NUMBER")
    private int HOUSE_NUMBER;
    @ColumnInfo(name = "PHONE_NUMBER")
    @Nonnegative
    private long PHONE_NUMBER;
    @ColumnInfo(name = "PROFILE_PICTURE")
    private String PROFILE_PICTURE_URI;
    @ColumnInfo(name = "RENT_PER_MONTH")
    @Nonnegative
    private double RENT_PER_MONTH;
    @PrimaryKey(autoGenerate = true)
    private int TENANT_ID;
    @Embedded
    private TenantName tenantName;

    public Entity_TENANTS(int HOUSE_NUMBER2, TenantName tenantName2, String PROFILE_PICTURE_URI2, long PHONE_NUMBER2, double RENT_PER_MONTH2) {
        this.HOUSE_NUMBER = HOUSE_NUMBER2;
        this.tenantName = tenantName2;
        this.PROFILE_PICTURE_URI = PROFILE_PICTURE_URI2;
        this.PHONE_NUMBER = PHONE_NUMBER2;
        this.RENT_PER_MONTH = RENT_PER_MONTH2;
    }

    protected Entity_TENANTS(Parcel in) {
        this.TENANT_ID = in.readInt();
        this.HOUSE_NUMBER = in.readInt();
        this.tenantName = (TenantName) in.readParcelable(TenantName.class.getClassLoader());
        this.PROFILE_PICTURE_URI = in.readString();
        this.PHONE_NUMBER = in.readLong();
        this.RENT_PER_MONTH = in.readDouble();
    }

    public void setTENANT_ID(int TENANT_ID2) {
        this.TENANT_ID = TENANT_ID2;
    }

    public int getTENANT_ID() {
        return this.TENANT_ID;
    }

    public int getHOUSE_NUMBER() {
        return this.HOUSE_NUMBER;
    }

    public TenantName getTenantName() {
        return this.tenantName;
    }

    public String getPROFILE_PICTURE_URI() {
        return this.PROFILE_PICTURE_URI;
    }

    public long getPHONE_NUMBER() {
        return this.PHONE_NUMBER;
    }

    public double getRENT_PER_MONTH() {
        return this.RENT_PER_MONTH;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("First name : ");
        sb.append(getTenantName().getFIRST_NAME());
        sb.append(". \n\n");
        String tenant_details = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(tenant_details);
        sb2.append("Last name : ");
        sb2.append(getTenantName().getLAST_NAME());
        sb2.append(". \n\n");
        String tenant_details2 = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(tenant_details2);
        sb3.append("Phone number : ");
        sb3.append(String.valueOf(getPHONE_NUMBER()));
        sb3.append(". \n\n");
        String tenant_details3 = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(tenant_details3);
        sb4.append("Rent per month : Ksh. ");
        sb4.append(String.valueOf(getRENT_PER_MONTH()));
        return sb4.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.TENANT_ID);
        parcel.writeInt(this.HOUSE_NUMBER);
        parcel.writeParcelable(this.tenantName, i);
        parcel.writeString(this.PROFILE_PICTURE_URI);
        parcel.writeLong(this.PHONE_NUMBER);
        parcel.writeDouble(this.RENT_PER_MONTH);
    }
}
