
package com.neuqer.android.splash.model;


public class Ad implements Splash {

    private long mStartTime;
    private long mEndTime;
    private String mImgPath;

    public Ad(long startTime, long endTime, String imgPath) {
        mStartTime = startTime;
        mEndTime = endTime;
        mImgPath = imgPath;
    }

    public long getStartTime() {
        return mStartTime;
    }

    public long getEndTime() {
        return mEndTime;
    }

    public String getImgPath() {
        return mImgPath;
    }
}
