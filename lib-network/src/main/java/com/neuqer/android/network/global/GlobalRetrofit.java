
package com.neuqer.android.network.global;

import java.util.HashMap;

import retrofit2.Retrofit;

public class GlobalRetrofit {

    private Retrofit mRetrofit;
    private Retrofit.Builder mBuilder;
    private HashMap<String, Object> mServiceCache;

    private GlobalRetrofit() {
        mServiceCache = new HashMap<>();
    }

    public void init(Retrofit.Builder builder) {
        if (mBuilder != null) {
            throw new InitException("重复初始化");
        }
        mBuilder = builder;
    }

    public static GlobalRetrofit getInstance() {
        return Holder.sInstance;
    }

    private Retrofit getRetrofit() {
        if (mRetrofit != null) {
            return mRetrofit;
        }
        if (mBuilder == null) {
            throw new InitException("未进行初始化");
        }
        mRetrofit = mBuilder.build();
        return mRetrofit;
    }

    /**
     * 使用全局参数创建请求
     */
    public <K> K createService(Class<K> service) {
        K retrofitService = (K) mServiceCache.get(service.getCanonicalName());
        if (retrofitService == null) {
            retrofitService = getRetrofit().create(service);
            mServiceCache.put(service.getCanonicalName(), retrofitService);
        }
        return retrofitService;
    }

    private static class Holder {
        private static GlobalRetrofit sInstance = new GlobalRetrofit();
    }
}
