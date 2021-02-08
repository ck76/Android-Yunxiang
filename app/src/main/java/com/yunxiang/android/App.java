
package com.yunxiang.android;

import android.app.Application;
import android.content.Context;

import com.neuqer.android.network.HttpClient;
import com.neuqer.android.runtime.AppRuntimeInit;
import com.neuqer.android.scheme.SchemeFactory;
import com.neuqer.android.scheme.SchemeRuntime;
import com.neuqer.android.splash.SplashRuntimeInit;
import com.yunxiang.android.ioc.SplashContext;
import com.yunxiang.android.network.TokenInterceptor;
import com.yunxiang.android.news.NewsSchemeDispatch;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        AppRuntimeInit.onApplicationAttachBaseContext(this, BuildConfig.DEBUG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initHttpClient();
        initScheme();
        initRuntime();
    }

    /**
     * 初始化网络库配置
     */
    private void initHttpClient() {
        // 当前为最简单配置，后续按需添加其他配置
        HttpClient.getInstance().onApplicationCreate(this);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        clientBuilder.addInterceptor(logInterceptor);
        clientBuilder.addNetworkInterceptor(new TokenInterceptor());
        HttpClient.getInstance().globalConfig(new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build()));
    }

    /**
     * 注册Scheme分发器
     */
    private void initScheme() {
        SchemeRuntime.setSchemeHeader("yunxiang");
        SchemeFactory.registerDispatch(NewsSchemeDispatch.NAME, NewsSchemeDispatch.class);
    }

    /**
     * 初始化Ioc接口实例
     */
    private void initRuntime() {
        SplashRuntimeInit.init(new SplashContext());
    }
}
