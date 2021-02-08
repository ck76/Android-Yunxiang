
package com.neuqer.android.permission;

import java.util.List;


public interface PermissionRequestCallback {

    /**
     * 权限申请通过
     */
    void onPermissionGranted();

    /**
     * 权限申请失败
     *
     * @param deniedPermissions 权限拒绝列表
     */
    void onPermissionDenied(List<String> deniedPermissions);
}
