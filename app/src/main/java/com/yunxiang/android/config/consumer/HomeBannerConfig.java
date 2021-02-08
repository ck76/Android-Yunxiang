package com.yunxiang.android.config.consumer;

import com.google.gson.JsonObject;

import com.neuqer.android.util.GsonUtil;
import com.yunxiang.android.home.data.HomeDataManager;
import com.yunxiang.android.home.model.HomeBannerModel;

public class HomeBannerConfig implements ConfigConsumer {

    public static final String NAME = "home_banner";

    @Override
    public void onReceiveConfig(JsonObject config) {
        if (config == null) {
            return;
        }
        HomeBannerModel model = GsonUtil.get().fromJson(config, HomeBannerModel.class);
        if (model != null) {
            HomeDataManager.getInstance().saveHomeBanner(model);
        }
    }
}
