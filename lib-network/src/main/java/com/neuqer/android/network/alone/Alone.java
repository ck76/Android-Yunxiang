
package com.neuqer.android.network.alone;

import com.neuqer.android.network.global.InitException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


public class Alone {

    private Retrofit.Builder mBuilder;
    private OkHttpClient.Builder mClientBuilder;

    public Alone() {
    }

    public Alone config(Retrofit.Builder builder, OkHttpClient.Builder clientBuilder) {
        mBuilder = builder;
        mClientBuilder = clientBuilder;
        return this;
    }

    public <K> K createService(Class<K> service) {
        if (mBuilder == null) {
            throw new InitException("未进行配置，请先调用 config() 方法");
        }
        mBuilder.client(mClientBuilder.build());
        return mBuilder.build().create(service);
    }
}
