package com.yunxiang.android.config;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ConfigService {

    @GET("app/config")
    Call<String> getAppConfig();
}
