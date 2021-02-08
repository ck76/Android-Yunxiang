package com.neuqer.android.sample.home;


import com.neuqer.android.runtime.base.activity.AbsActivity;


public class ItemModel {

    private String mTitle;
    private Class<? extends AbsActivity> mActivity;

    public ItemModel(String title, Class<? extends AbsActivity> activity) {
        mTitle = title;
        mActivity = activity;
    }

    public String getTitle() {
        return mTitle;
    }

    public Class<? extends AbsActivity> getActivity() {
        return mActivity;
    }
}
