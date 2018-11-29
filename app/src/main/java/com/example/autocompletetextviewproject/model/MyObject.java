package com.example.autocompletetextviewproject.model;

public class MyObject {
    private String mStoreName;
    private String mLocation;

    public MyObject(String storeName, String location) {
        this.mStoreName = storeName;
        this.mLocation = location;
    }

    public String getStoreName() {
        return mStoreName;
    }

    public void setStoreName(String storeName) {
        this.mStoreName = storeName;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }
}
