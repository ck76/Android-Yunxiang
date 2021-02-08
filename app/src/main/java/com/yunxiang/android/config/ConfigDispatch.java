package com.yunxiang.android.config;

import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.neuqer.android.util.GsonUtil;
import com.yunxiang.android.config.consumer.HomeActivityConfig;
import com.yunxiang.android.config.consumer.HomeBannerConfig;
import com.yunxiang.android.config.consumer.HomeCategoryConfig;
import com.yunxiang.android.config.consumer.NewsTabConfig;

import java.util.HashMap;
import java.util.Set;

import com.yunxiang.android.config.consumer.ConfigConsumer;


public class ConfigDispatch {

    private HashMap<String, Class<? extends ConfigConsumer>> mConfigConsumerMap;


    public ConfigDispatch() {
        mConfigConsumerMap = new HashMap<>();
        initDispatchMap();
    }

    /**
     * 分发处理
     */
    public void dispatch(String config) {
        if (TextUtils.isEmpty(config)) {
            return;
        }
        JsonObject configJson = GsonUtil.parseString(config);
        Set<String> nameSet = configJson.keySet();
        Class<? extends ConfigConsumer> configConsumerCls;
        for (String name : nameSet) {
            if ((configConsumerCls = getConfigConsumer(name)) != null) {
                try {
                    ConfigConsumer consumer = configConsumerCls.newInstance();
                    consumer.onReceiveConfig(configJson.getAsJsonObject(name));
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 由Config信息节点名称，获取对象处理对象
     */
    private Class<? extends ConfigConsumer> getConfigConsumer(String name) {
        if (mConfigConsumerMap == null) {
            return null;
        }
        return mConfigConsumerMap.get(name);
    }

    /**
     * 初始化分发器集合
     */
    private void initDispatchMap() {
        mConfigConsumerMap.clear();
        mConfigConsumerMap.put(HomeBannerConfig.NAME, HomeBannerConfig.class);
        mConfigConsumerMap.put(HomeCategoryConfig.NAME, HomeCategoryConfig.class);
        mConfigConsumerMap.put(HomeActivityConfig.NAME, HomeCategoryConfig.class);
        mConfigConsumerMap.put(NewsTabConfig.NAME, NewsTabConfig.class);
    }
}
