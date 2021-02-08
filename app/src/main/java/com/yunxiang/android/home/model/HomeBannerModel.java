
package com.yunxiang.android.home.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class HomeBannerModel implements Serializable {

    @SerializedName("type")
    private int mBannerType;
    @SerializedName("internal")
    private int mBannerInternal = 3000;
    @SerializedName("banner_list")
    private List<BannerModel> mHomeBannerList;

    public HomeBannerModel(int bannerType, List<BannerModel> homeBannerList) {
        mBannerType = bannerType;
        mHomeBannerList = homeBannerList;
    }

    public HomeBannerModel(int bannerType, int bannerInternal, List<BannerModel> homeBannerList) {
        mBannerType = bannerType;
        mBannerInternal = bannerInternal;
        mHomeBannerList = homeBannerList;
    }

    public int getBannerType() {
        return mBannerType;
    }

    public int getBannerInternal() {
        return mBannerInternal;
    }

    public List<BannerModel> getHomeBannerList() {
        return mHomeBannerList;
    }

    public static class BannerModel implements Serializable {
        @SerializedName("title")
        private String mTitle;
        @SerializedName("img_url")
        private String mUrl;
        @SerializedName("cmd")
        private String mCmd;

        public BannerModel(String title, String url, String cmd) {
            mTitle = title;
            mUrl = url;
            mCmd = cmd;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getUrl() {
            return mUrl;
        }

        public String getCmd() {
            return mCmd;
        }
    }
}
