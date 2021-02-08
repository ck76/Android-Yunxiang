
package com.yunxiang.android.home.data;

import com.neuqer.android.cache.SimpleCache;
import com.yunxiang.android.home.model.HomeActivityModel;
import com.yunxiang.android.home.model.HomeBannerModel;
import com.yunxiang.android.home.model.HomeCategoryModel;

import java.util.List;

import com.neuqer.android.runtime.AppRuntime;


public class HomeDataManager {

    private static final String HOME_DATA_BANNER_CACHE_NAME = "home_data_banner_cache";
    private static final String HOME_DATA_CATEGORY_CACHE_NAME = "home_data_category_cache";
    private static final String HOME_DATA_ACTIVITY_CACHE_NAME = "home_data_activity_cache";
    private SimpleCache mCache;

    private HomeDataManager() {
        mCache = SimpleCache.get(AppRuntime.getAppContext());
    }

    public static HomeDataManager getInstance() {
        return HomeDataManager.Holder.sInstance;
    }


    public HomeBannerModel getHomeBanner() {
        Object data = mCache.getAsObject(HOME_DATA_BANNER_CACHE_NAME);
        if (data instanceof HomeBannerModel) {
            return (HomeBannerModel) data;
        }
        return HomePresetData.getHomeBannerModel();
    }

    public void saveHomeBanner(HomeBannerModel banner) {
        if (banner == null) {
            return;
        }
        mCache.put(HOME_DATA_BANNER_CACHE_NAME, banner);
    }

    public HomeCategoryModel getHomeCategory() {
        Object data = mCache.getAsObject(HOME_DATA_CATEGORY_CACHE_NAME);
        if (data instanceof HomeCategoryModel) {
            return (HomeCategoryModel) data;
        }
        return HomePresetData.getHomeCategoryModel();
    }

    public void saveHomeCategory(HomeCategoryModel model) {
        if (model == null) {
            return;
        }
        mCache.put(HOME_DATA_CATEGORY_CACHE_NAME, model);
    }

    public HomeActivityModel getHomeActivity() {
        Object data = mCache.getAsObject(HOME_DATA_ACTIVITY_CACHE_NAME);
        if (data instanceof HomeActivityModel) {
            return (HomeActivityModel) data;
        }
        return HomePresetData.getHomeActivityModel();
    }

    public void saveHomeActivity(List<HomeActivityModel.Activity> activityList) {
        if (activityList == null) {
            return;
        }
        HomeActivityModel homeActivityModel = new HomeActivityModel(activityList);
        mCache.put(HOME_DATA_ACTIVITY_CACHE_NAME, homeActivityModel);
    }

    private static class Holder {
        private static final HomeDataManager sInstance = new HomeDataManager();
    }
}
