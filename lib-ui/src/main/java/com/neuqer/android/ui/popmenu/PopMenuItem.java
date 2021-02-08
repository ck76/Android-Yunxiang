package com.neuqer.android.ui.popmenu;

import android.graphics.drawable.Drawable;


public class PopMenuItem {

    private int mId;
    private String mTitle;
    private Drawable mDrawable;

    public PopMenuItem(int id, String title, Drawable drawable) {
        mId = id;
        mTitle = title;
        mDrawable = drawable;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }
}
