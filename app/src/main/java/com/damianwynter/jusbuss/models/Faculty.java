package com.damianwynter.jusbuss.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Faculty implements Parcelable{
    private String mName;
    private String mDescription;
    private double mLongitude;
    private double mLatitude;
    private String mIcon;
    private String mId;

    public Faculty() {
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mDescription);
        parcel.writeDouble(mLongitude);
        parcel.writeDouble(mLatitude);
        parcel.writeString(mIcon);
        parcel.writeString(mId);
    }

    public Faculty(Parcel in){
        mName = in.readString();
        mDescription = in.readString();
        mLongitude = in.readDouble();
        mLatitude = in.readDouble();
        mIcon = in.readString();
        mId = in.readString();
    }

    public static final Creator<Faculty> CREATOR = new Creator<Faculty>() {
        @Override
        public Faculty createFromParcel(Parcel parcel) {
            return new Faculty(parcel);
        }

        @Override
        public Faculty[] newArray(int i) {
            return new Faculty[i];
        }
    };
}
