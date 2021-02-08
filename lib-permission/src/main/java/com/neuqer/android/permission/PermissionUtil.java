
package com.neuqer.android.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.neuqer.android.util.ApplicationInfo;

import static android.os.Build.VERSION_CODES.M;


public final class PermissionUtil {

    public static boolean isPermissionTargetVersion(Context context) {
        return ApplicationInfo.getTargetSdkVersion(context) >= Build.VERSION_CODES.M;
    }

    public static List<String> filterDenyPermissions(Context context, List<String> permissions) {
        if (permissions == null) {
            return null;
        }
        List<String> denyPermission = new ArrayList<>();
        for (String permission : permissions) {
            if (!isPermissionGranted(context, permission)) {
                denyPermission.add(permission);
            }
        }
        return denyPermission;
    }

    public static boolean shouldShowRequestPermissionRationale(Context ctx, String permission) {
        if (ctx instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) ctx, permission);
        } else {
            PackageManager packageManager = ctx.getPackageManager();
            Class pkManagerClass = packageManager.getClass();
            try {
                Method method = pkManagerClass.getMethod("shouldShowRequestPermissionRationale", String.class);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                return (boolean) method.invoke(packageManager, permission);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static boolean isPermissionGranted(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= M) {
            if (isPermissionTargetVersion(context)) {
                return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
            } else {
                return PermissionChecker.checkSelfPermission(context, permission)
                        == PermissionChecker.PERMISSION_GRANTED;
            }
        }
        return true;
    }
}
