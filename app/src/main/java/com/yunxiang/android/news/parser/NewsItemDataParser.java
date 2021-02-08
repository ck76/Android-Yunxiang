
package com.yunxiang.android.news.parser;

import org.json.JSONObject;

import com.yunxiang.android.news.model.NewsItemOneImg;
import com.yunxiang.android.news.model.NewsItemThreeImg;
import com.yunxiang.android.news.view.NewsType;
import com.yunxiang.android.news.model.NewsItemData;
import com.yunxiang.android.news.model.NewsItemText;

public final class NewsItemDataParser {


    /**
     * 解析Item数据
     *
     * @param layout     Item类型
     * @param jsonObject ItemData Json数据
     * @return Item Model
     */
    public static NewsItemData parseItemData(NewsType layout, JSONObject jsonObject) {
        if (layout == null || jsonObject == null) {
            return null;
        }
        switch (layout) {
            case TEXT:
                return new NewsItemText().toModel(jsonObject);
            case ONE_IMG_LEFT:
                return new NewsItemOneImg().toModel(jsonObject);
            case THREE_IMG:
                return new NewsItemThreeImg().toModel(jsonObject);
            default:
                return null;
        }
    }

}
