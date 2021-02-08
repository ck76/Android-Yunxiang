package com.neuqer.android.runtime;

import android.app.Application;
import android.content.Context;


public class AppRuntime {

    /** Application */
    static Application sApplication;
    /** Debug标识 */
    static boolean sDebug;

    /**
     * 获取AppContext
     */
    public static Context getAppContext() {
        return sApplication;
    }

    /**
     * 获取Application
     */
    public static Application getApplication() {
        return sApplication;
    }

    /**
     * 全局Debug开关
     */
    public static boolean isDebug() {
        return sDebug;
    }
}
