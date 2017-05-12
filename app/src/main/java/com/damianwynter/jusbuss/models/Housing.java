package com.damianwynter.jusbuss.models;

import java.io.Serializable;

/**
 * Created by infinity on 5/9/2017.
 */

public class Housing implements Serializable {
    private String fname, lname,phone,address, tenantGender, utilises, description, status, icon;
    private double price, longitude, latitude;
    private int numOccupancy, vacancy;

    public Housing() {
        this("john","doe","555-5420","58 down the road",
                "It have one gate and some rooms","male","candle light, river baths and free air",
                "Available",".../housing_icon.png",18750.00d,15.25454d,-4.2545d,2,4);
    }

    public Housing(String fname, String lname, String phone,
                   String address,String description ,String tenantGender,
                   String utilises, String status, String icon,
                   double price, double longitude, double latitude,
                   int numOccupancy, int vacancy) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.address = address;
        this.tenantGender = tenantGender;
        this.utilises = utilises;
        this.description = description;
        this.status = status;
        this.icon = icon;
        this.price = price;
        this.longitude = longitude;
        this.latitude = latitude;
        this.numOccupancy = numOccupancy;
        this.vacancy = vacancy;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTenantGender() {
        return tenantGender;
    }

    public void setTenantGender(String tenantGender) {
        this.tenantGender = tenantGender;
    }

    public String getUtilises() {
        return utilises;
    }

    public void setUtilises(String utilises) {
        this.utilises = utilises;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getNumOccupancy() {
        return numOccupancy;
    }

    public void setNumOccupancy(int numOccupancy) {
        this.numOccupancy = numOccupancy;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
