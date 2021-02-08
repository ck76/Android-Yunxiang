package com.yunxiang.android.config.consumer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.neuqer.android.util.GsonUtil;
import com.yunxiang.android.news.data.TabDataManager;
import com.yunxiang.android.news.model.TabModel;

import java.util.List;


public class NewsTabConfig implements ConfigConsumer {

    public static final String NAME = "news_tab";
    public static final String KEY_LIST = "tab_list";

    @Override
    public void onReceiveConfig(JsonObject config) {
        JsonArray tabArray = config.getAsJsonArray(KEY_LIST);
        List<TabModel> tabList = GsonUtil.get().fromJson(tabArray, new TypeToken<List<TabModel>>() {
        }.getType());
        TabDataManager.getInstance().saveTabList(tabList);
    }
}
