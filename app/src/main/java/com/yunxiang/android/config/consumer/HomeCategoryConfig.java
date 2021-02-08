package com.yunxiang.android.config.consumer;

import com.google.gson.JsonObject;

import com.neuqer.android.util.GsonUtil;
import com.yunxiang.android.home.data.HomeDataManager;
import com.yunxiang.android.home.model.HomeCategoryModel;



public class HomeCategoryConfig implements ConfigConsumer {

    public static final String NAME = "home_category";

    @Override
    public void onReceiveConfig(JsonObject config) {
        if (config == null) {
            return;
        }
        HomeCategoryModel model = GsonUtil.get().fromJson(config, HomeCategoryModel.class);
        if (model != null) {
            HomeDataManager.getInstance().saveHomeCategory(model);
        }
    }
}
