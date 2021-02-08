package com.neuqer.android.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


public final class ApplicationInfo {


    public static int getTargetSdkVersion(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo != null ? packageInfo.applicationInfo.targetSdkVersion : -1;
    }

    public static String getVersionName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo != null ? packageInfo.versionName : "0.0";
    }

    static PackageInfo getPackageInfo(Context context) {
        try {
            return context != null ?
                    context.getPackageManager().getPackageInfo(context.getPackageName(), 0) : null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
