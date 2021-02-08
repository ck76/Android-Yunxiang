
package com.yunxiang.android.news;

import android.content.Context;
import android.text.TextUtils;

import com.neuqer.android.scheme.AbsSchemeDispatch;
import com.neuqer.android.scheme.SchemeCallback;
import com.neuqer.android.scheme.SchemeEntity;
import com.neuqer.android.util.JsonUtil;

import org.json.JSONObject;


public class NewsSchemeDispatch extends AbsSchemeDispatch {

    /** 分发器名称 */
    public static final String NAME = "news";
    /** 详情页 */
    private static final String ACTION_DETAIL = "details";
    private static final String KEY_URL = "url";

    @Override
    public boolean dispatch(Context context, SchemeEntity entity, SchemeCallback callback) {
        switch (entity.getAction()) {
            case ACTION_DETAIL:
                return handleNewsDetail(context, entity.getParams());
        }
        return false;
    }

    /**
     * 处理调详情页
     *
     * @param context 上下文
     * @param params  参数
     */
    private boolean handleNewsDetail(Context context, String params) {
        JSONObject paramsJson = JsonUtil.parseString(params);
        String url = paramsJson.optString(KEY_URL);
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        NewsDetailActivity.start(context, url);
        return true;
    }
}
