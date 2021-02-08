package com.yunxiang.android.network;

import com.neuqer.android.cache.SimpleCache;
import com.neuqer.android.runtime.AppRuntime;
import com.yunxiang.android.account.AccountConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = SimpleCache.get(AppRuntime.getAppContext()).getAsString(AccountConstants.KEY_TOKEN);
        if (token == null) {
            // 测试token
            token = "W3U3gguhKU2JbQXSoSCetrCrEwIDZVvkMrzaRDGlhHp?fqSIBTV=XGsz?6yL>G[N";
        }
        return chain.proceed(chain
                .request()
                .newBuilder()
                .addHeader(AccountConstants.KEY_TOKEN, token)
                .build());
    }
}
