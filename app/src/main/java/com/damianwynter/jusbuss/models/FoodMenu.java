package com.damianwynter.jusbuss.models;


public class FoodMenu {
    private String mName;
    private String mDescription;
    private String[] mPictures;
    private SizeVariations[] mSizeVariationses;

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

    public String[] getPictures() {
        return mPictures;
    }

    public void setPictures(String[] pictures) {
        mPictures = pictures;
    }

    public SizeVariations[] getSizeVariationses() {
        return mSizeVariationses;
    }

    public void setSizeVariationses(SizeVariations[] sizeVariationses) {
        mSizeVariationses = sizeVariationses;
    }
}
