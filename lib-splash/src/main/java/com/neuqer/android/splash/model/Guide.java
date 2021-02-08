

package com.neuqer.android.splash.model;


public class Guide implements Splash {

    private int mImgRes;
    private boolean mShowInter;

    public Guide(int imgRes) {
        mImgRes = imgRes;
    }

    public int getImgRes() {
        return mImgRes;
    }

    public boolean isShowInter() {
        return mShowInter;
    }

    public void setShowInter(boolean showInter) {
        this.mShowInter = showInter;
    }
}
