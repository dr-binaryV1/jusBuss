package com.damianwynter.jusbuss.models;

import java.io.Serializable;

/**
 * Created by infinity on 5/12/2017.
 */

public class GeneralBAF implements Serializable {
    private String aName;
    private String aDescription;
    private int aOpenTime;
    private int aCloseTime;
    private double aLongitude;
    private double aLatitude;
    private String Image;

    public GeneralBAF(){
        this("Name",
                "Descroption",
                0,0,
                0.00000000d,
                0.00000000d);
    }
    public GeneralBAF(String aName, String aDescription,
                      int aOpenTime, int aCloseTime,
                      double aLongitude, double aLatitude) {
        this.aName = aName;
        this.aDescription = aDescription;
        this.aOpenTime = aOpenTime;
        this.aCloseTime = aCloseTime;
        this.aLongitude = aLongitude;
        this.aLatitude = aLatitude;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaDescription() {
        return aDescription;
    }

    public void setaDescription(String aDescription) {
        this.aDescription = aDescription;
    }

    public int getaOpenTime() {
        return aOpenTime;
    }

    public void setaOpenTime(int aOpenTime) {
        this.aOpenTime = aOpenTime;
    }

    public int getaCloseTime() {
        return aCloseTime;
    }

    public void setaCloseTime(int aCloseTime) {
        this.aCloseTime = aCloseTime;
    }

    public double getLongitude() {
        return aLongitude;
    }

    public void setaLongitude(double aLongitude) {
        this.aLongitude = aLongitude;
    }

    public double getLatitude() {
        return aLatitude;
    }

    public void setaLatitude(double aLatitude) {
        this.aLatitude = aLatitude;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
