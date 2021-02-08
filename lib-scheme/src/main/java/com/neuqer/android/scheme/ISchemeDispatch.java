package com.neuqer.android.scheme;

import android.content.Context;


public interface ISchemeDispatch {
    /**
     * 分发处理Action
     *
     * @param context  上下文
     * @param entity   Scheme信息
     * @param callback 回调
     * @return 是否成功处理
     */
    boolean dispatch(Context context, SchemeEntity entity, SchemeCallback callback);
}
