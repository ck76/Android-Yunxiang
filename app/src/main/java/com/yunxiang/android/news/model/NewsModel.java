

package com.yunxiang.android.news.model;

import com.yunxiang.android.news.parser.NewsItemDataParser;

import org.json.JSONException;
import org.json.JSONObject;

import com.yunxiang.android.news.view.NewsType;
import com.yunxiang.android.news.parser.NewsItemDataParser;


public class NewsModel implements INewsData<NewsModel> {

    public static final String KEY_ID = "_id";
    public static final String KEY_LAYOUT = "layout";
    public static final String KEY_DATA = "data";

    /** 资讯唯一标识 */
    public String id;
    /** 资讯TabId */
    public int tabId;
    /** 是否已读 */
    public int isRead;
    /** 资讯类型Layout */
    public String layout;
    /** 资讯具体数据 */
    public NewsItemData data;


    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KEY_ID, id);
            jsonObject.put(KEY_LAYOUT, layout);
            if (data != null) {
                jsonObject.put(KEY_DATA, data.toJson());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public NewsModel toModel(JSONObject jsonObject) {
        if (jsonObject == null) {
            return this;
        }
        id = jsonObject.optString(KEY_ID);
        layout = jsonObject.optString(KEY_LAYOUT);
        data = NewsItemDataParser.parseItemData(NewsType.getLayout(layout), jsonObject.optJSONObject(KEY_DATA));
        return this;
    }
}
