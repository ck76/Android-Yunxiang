
package com.neuqer.android.permission;

import android.content.Context;

import java.util.List;


interface PermissionRequestCaller {

    /**
     * 申请权限
     *
     * @param context    上下文
     * @param responder  权限申请结果响应器
     * @param permission 权限列表
     */
    void requestPermission(Context context, PermissionRequestResponder responder, List<String> permission);

}
