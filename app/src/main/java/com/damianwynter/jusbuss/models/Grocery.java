package com.damianwynter.jusbuss.models;

import java.io.Serializable;

/**
 * Created by infinity on 5/10/2017.
 */

public class Grocery implements Serializable {
    private String name,description,address, icon;
    private int openHours, closeHours;
    private double longitude, latitude;

    public Grocery() {
        this("Dude nuff","We have grocery","cross di road from bar",
                ".../grocery_icon",5,3,15.25454d,-4.2545d);
    }

    public Grocery(String name, String description, String address, String icon, int openHours, int closeHours, double longitude, double latitude) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.icon = icon;
        this.openHours = openHours;
        this.closeHours = closeHours;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOpenHours() {
        return openHours;
    }

    public void setOpenHours(int openHours) {
        this.openHours = openHours;
    }

    public int getCloseHours() {
        return closeHours;
    }

    public void setCloseHours(int closeHours) {
        this.closeHours = closeHours;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
