package com.yunxiang.android.config;

import com.neuqer.android.network.HttpClient;
import com.yunxiang.android.network.HttpConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ConfigManager {

    /**
     * 获取云端配置并分发解析
     */
    public void getConfigData() {
        HttpClient.getInstance().createService(ConfigService.class).getAppConfig().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null && response.code() == HttpConstants.HTTP_SUCCESS) {
                    new ConfigDispatch().dispatch(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

            }
        });
    }
}
