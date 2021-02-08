
package com.neuqer.android.scheme;

import android.content.Context;
import android.text.TextUtils;


public class Scheme {

    /**
     * Scheme分发
     *
     * @param context 上下文
     * @param scheme  Scheme
     */
    public static void dispatch(Context context, String scheme) {
        dispatch(context, scheme, null);
    }

    /**
     * Scheme分发
     *
     * @param context  上下文
     * @param scheme   Scheme
     * @param callback 接口回调
     */
    public static void dispatch(Context context, String scheme, SchemeCallback callback) {
        if (context == null || TextUtils.isEmpty(scheme)) {
            return;
        }
        MainSchemeDispatch dispatch = new MainSchemeDispatch(context);
        dispatch.dispatch(scheme, callback);
    }
}
