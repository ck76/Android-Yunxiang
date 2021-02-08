
package com.yunxiang.android.ioc;

import android.content.Context;
import android.content.Intent;

import com.neuqer.android.splash.ISplashContext;
import com.yunxiang.android.main.MainActivity;


public class SplashContext implements ISplashContext {

    @Override
    public void startHomeActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
