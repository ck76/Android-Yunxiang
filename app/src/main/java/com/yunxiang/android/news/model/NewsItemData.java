
package com.yunxiang.android.news.model;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class NewsItemData implements INewsData<NewsItemData> {


    public static final String KEY_TITLE = "title";
    public static final String KEY_CMD = "cmd";
    public static final String KEY_SOURCE = "source";

    /** 标题 */
    public String title;
    /** cmd参数，一般用于点击跳转 */
    public String cmd;
    /** 来源 */
    public String source;

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KEY_TITLE, title);
            jsonObject.put(KEY_CMD, cmd);
            jsonObject.put(KEY_SOURCE, source);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    @Override
    public NewsItemData toModel(JSONObject jsonObject) {
        if (jsonObject != null) {
            title = jsonObject.optString(KEY_TITLE);
            cmd = jsonObject.optString(KEY_CMD);
            source = jsonObject.optString(KEY_SOURCE);
        }
        return this;
    }
}
