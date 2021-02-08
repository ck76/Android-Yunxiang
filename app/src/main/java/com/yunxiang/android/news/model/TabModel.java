
package com.yunxiang.android.news.model;

import java.io.Serializable;


public class TabModel implements Serializable {

    private int mId;
    private String mTitle;

    public TabModel(int id, String title) {
        mId = id;
        mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

}
