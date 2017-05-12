package com.damianwynter.jusbuss.models;


public class University {
    private String name;
    private String description;
    private String address;
    private String openTime;
    private String closeTime;
    private String longitude;
    private String latitude;
    private String icon;
    private GeneralBAF [] mBuildings;
    private GeneralBAF [] mBanks;
    private GeneralBAF [] mClubs;
    private GeneralBAF [] mAtm;
    private Faculty[] mFaculties;

    public Faculty[] getFaculties() {
        return mFaculties;
    }

    public GeneralBAF[] getmBuildings() {
        return mBuildings;
    }

    public void setmBuildings(GeneralBAF[] mBuildings) {
        this.mBuildings = mBuildings;
    }

    public GeneralBAF[] getmBanks() {
        return mBanks;
    }

    public void setmBanks(GeneralBAF[] mBanks) {
        this.mBanks = mBanks;
    }

    public GeneralBAF[] getmClubs() {
        return mClubs;
    }

    public void setmClubs(GeneralBAF[] mClubs) {
        this.mClubs = mClubs;
    }

    public GeneralBAF[] getmAtm() {
        return mAtm;
    }

    public void setmAtm(GeneralBAF[] mAtm) {
        this.mAtm = mAtm;
    }

    public Faculty[] getmFaculties() {
        return mFaculties;
    }

    public void setmFaculties(Faculty[] mFaculties) {
        this.mFaculties = mFaculties;
    }

    public void setFaculties(Faculty[] faculties) {
        mFaculties = faculties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
