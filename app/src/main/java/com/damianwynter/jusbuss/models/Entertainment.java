package com.damianwynter.jusbuss.models;


public class Entertainment {
    private String mName;
    private String mCategory;
    private String mTime;
    private String mDate;
    private String mAddress;
    private String mTel;
    private String mPoster;
    private String ticket_sold_at;

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    private String admission;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getTel() {
        return mTel;
    }

    public void setTel(String tel) {
        mTel = tel;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public String getTicket_sold_at() {
        return ticket_sold_at;
    }

    public void setTicket_sold_at(String ticket_sold_at) {
        this.ticket_sold_at = ticket_sold_at;
    }
}
