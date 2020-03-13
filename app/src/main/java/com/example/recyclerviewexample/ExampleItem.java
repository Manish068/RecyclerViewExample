package com.example.recyclerviewexample;

public class ExampleItem {
    private int mImageResource;
    private String mContactName;
    private String mPhone;

    public ExampleItem(int mImageResource, String mContactName, String mPhone) {
        this.mImageResource = mImageResource;
        this.mContactName = mContactName;
        this.mPhone = mPhone;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmContactName() {
        return mContactName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void changeText1(String text) {
        mContactName = text;
    }


}
