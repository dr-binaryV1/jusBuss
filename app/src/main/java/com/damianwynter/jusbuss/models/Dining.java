package com.damianwynter.jusbuss.models;


public class Dining {
    private String mName;
    private String mDescription;
    private String mAddress;
    private Double mLongitude;
    private Double mLatitude;
    private String tel;
    private int mOpenTime;
    private int mCloseTime;
    private String mIcon;
    private FoodMenu[][] mFoodMenus;

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

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public int getOpenTime() {
        return mOpenTime;
    }

    public void setOpenTime(int openTime) {
        mOpenTime = openTime;
    }

    public int getCloseTime() {
        return mCloseTime;
    }

    public void setCloseTime(int closeTime) {
        mCloseTime = closeTime;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public FoodMenu[][] getFoodMenus() {
        return mFoodMenus;
    }

    public void setFoodMenus(FoodMenu[][] foodMenus) {
        mFoodMenus = foodMenus;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
