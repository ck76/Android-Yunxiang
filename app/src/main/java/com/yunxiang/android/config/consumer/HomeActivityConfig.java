package com.yunxiang.android.config.consumer;

import com.google.gson.JsonObject;

import com.neuqer.android.util.GsonUtil;
import com.yunxiang.android.home.data.HomeDataManager;
import com.yunxiang.android.home.model.HomeActivityModel;


public class HomeActivityConfig implements ConfigConsumer {

    public static final String NAME = "home_activity";

    @Override
    public void onReceiveConfig(JsonObject config) {
        if (config == null) {
            return;
        }
        HomeActivityModel model = GsonUtil.get().fromJson(config, HomeActivityModel.class);
        if (model != null) {
            HomeDataManager.getInstance().saveHomeActivity(model.getActivityList());
        }
    }
}
