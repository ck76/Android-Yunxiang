
package com.yunxiang.android.news.parser;

import android.text.TextUtils;

import com.yunxiang.android.news.filter.NewsModelFilter;
import com.yunxiang.android.news.model.NewsFlowModel;
import com.yunxiang.android.news.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.yunxiang.android.news.filter.NewsModelFilter;
import com.yunxiang.android.news.model.NewsFlowModel;
import com.yunxiang.android.news.model.NewsModel;


public final class NewsFlowModelParser {

    private static final String KEY_ITEM_LIST = "items";

    /**
     * 解析ItemFlow数据
     *
     * @param flowString ItemFlow JsonString数据
     * @return ItemFlow Model
     */
    public static NewsFlowModel parseNewsFlow(String flowString) {
        if (TextUtils.isEmpty(flowString)) {
            return null;
        }
        NewsFlowModel newsFlowModel = new NewsFlowModel();
        try {
            JSONObject jsonObject = new JSONObject(flowString);
            JSONArray itemArray = jsonObject.optJSONArray(KEY_ITEM_LIST);
            if (itemArray == null) {
                return newsFlowModel;
            }

            List<NewsModel> newsModelList = new ArrayList<>();
            int newsSize = itemArray.length();
            for (int i = 0; i < newsSize; i++) {
                JSONObject newsObject = itemArray.optJSONObject(i);
                if (newsObject != null) {
                    NewsModel newsModel = new NewsModel().toModel(newsObject);
                    if (NewsModelFilter.check(newsModel) == NewsModelFilter.OK) {
                        newsModelList.add(newsModel);
                    }
                }
            }
            newsFlowModel.setNewsModelList(newsModelList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsFlowModel;
    }
}
