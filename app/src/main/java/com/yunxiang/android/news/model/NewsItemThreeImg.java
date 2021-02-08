

package com.yunxiang.android.news.model;

import org.json.JSONException;
import org.json.JSONObject;


public class NewsItemThreeImg extends NewsItemData {

    private static final String KEY_LEFT_IMG_URL = "left_img_url";
    private static final String KEY_MID_IMG_URL = "mid_img_url";
    private static final String KEY_RIGHT_IMG_URL = "right_img_url";

    public String leftImgUrl;
    public String midImgUrl;
    public String rightImgUrl;

    @Override
    public NewsItemData toModel(JSONObject jsonObject) {
        super.toModel(jsonObject);
        if (jsonObject != null) {
            leftImgUrl = jsonObject.optString(KEY_LEFT_IMG_URL);
            midImgUrl = jsonObject.optString(KEY_MID_IMG_URL);
            rightImgUrl = jsonObject.optString(KEY_RIGHT_IMG_URL);
        }
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        try {
            jsonObject.put(KEY_LEFT_IMG_URL, leftImgUrl);
            jsonObject.put(KEY_MID_IMG_URL, midImgUrl);
            jsonObject.put(KEY_RIGHT_IMG_URL, rightImgUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}


