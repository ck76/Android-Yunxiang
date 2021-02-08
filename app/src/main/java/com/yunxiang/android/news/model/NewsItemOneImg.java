
package com.yunxiang.android.news.model;

import org.json.JSONException;
import org.json.JSONObject;


public class NewsItemOneImg extends NewsItemData {

    private static final String KEY_IMG_URL = "img_url";

    public String imgUrl;

    @Override
    public NewsItemData toModel(JSONObject jsonObject) {
        super.toModel(jsonObject);
        if (jsonObject != null) {
            imgUrl = jsonObject.optString(KEY_IMG_URL);
        }
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        try {
            jsonObject.put(KEY_IMG_URL, imgUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
