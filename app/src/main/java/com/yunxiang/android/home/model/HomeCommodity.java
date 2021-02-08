
package com.yunxiang.android.home.model;


public class HomeCommodity {

    private long mId;
    private String mImg;
    private String mTitle;
    private double mPrice;

    public HomeCommodity(long id, String img, String title, double price) {
        mId = id;
        mImg = img;
        mTitle = title;
        mPrice = price;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getImg() {
        return mImg;
    }

    public void setImg(String img) {
        mImg = img;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }
}
