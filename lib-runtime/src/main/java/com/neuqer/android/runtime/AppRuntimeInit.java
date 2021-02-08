package com.neuqer.android.runtime;

import android.app.Application;


public class AppRuntimeInit {

    /**
     * 设置运行环境
     */
    public static void onApplicationAttachBaseContext(Application application, boolean debug) {
        AppRuntime.sApplication = application;
        AppRuntime.sDebug = debug;
    }
}
