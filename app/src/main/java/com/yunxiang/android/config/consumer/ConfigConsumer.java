package com.yunxiang.android.config.consumer;

import com.google.gson.JsonObject;

public interface ConfigConsumer {

    /**
     * 收到配置信息
     *
     * @param config 数据
     */
    void onReceiveConfig(JsonObject config);
}
