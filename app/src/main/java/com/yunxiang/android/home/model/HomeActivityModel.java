
package com.yunxiang.android.home.model;


import com.google.gson.annotations.SerializedName;
import com.neuqer.android.ui.DynamicNumImgView;

import java.io.Serializable;
import java.util.List;

public class HomeActivityModel implements Serializable {

    @SerializedName("activity_list")
    private List<Activity> mActivityList;

    public HomeActivityModel(List<Activity> activityList) {
        mActivityList = activityList;
    }

    public List<Activity> getActivityList() {
        return mActivityList;
    }

    public static class Activity implements DynamicNumImgView.Model {

        @SerializedName("img_url")
        private String mImgUrl;
        @SerializedName("cmd")
        private String mCmd;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("start_time")
        private long mStartTime;
        @SerializedName("end_time")
        private long mEndTime;

        public Activity(String imgUrl, String cmd) {
            mImgUrl = imgUrl;
            mCmd = cmd;
        }

        @Override
        public String getImgUrl() {
            return mImgUrl;
        }

        public String getCmd() {
            return mCmd;
        }

        public String getTitle() {
            return mTitle;
        }

        public long getStartTime() {
            return mStartTime;
        }

        public long getEndTime() {
            return mEndTime;
        }
    }
}
