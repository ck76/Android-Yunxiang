
package com.neuqer.android.permission;

import java.util.List;


interface PermissionRequestResponder {

    /**
     * 权限申请结束
     *
     * @param permissions 申请的权限列表
     */
    void onPermissionResult(List<String> permissions);
}
