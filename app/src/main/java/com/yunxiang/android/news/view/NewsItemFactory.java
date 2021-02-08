
package com.yunxiang.android.news.view;

import android.content.Context;

import com.yunxiang.android.news.view.item.NewsItem;
import com.yunxiang.android.news.view.item.NewsOneImgLeftView;
import com.yunxiang.android.news.view.item.NewsTextView;

import com.yunxiang.android.news.view.item.NewsThreeImgView;


public class NewsItemFactory {

    /**
     * 创建资讯Item
     *
     * @param context 上下文
     * @param layout  布局样式
     * @return 资讯Item View
     */
    public static NewsItem createNewsItem(Context context, NewsType layout) {
        NewsItem item = null;
        switch (layout) {
            case TEXT:
                item = new NewsTextView(context);
                break;
            case ONE_IMG_LEFT:
                item = new NewsOneImgLeftView(context);
                break;
            case THREE_IMG:
                item = new NewsThreeImgView(context);
                break;
            default:
                break;
        }
        if (item == null) {
            throw new IllegalStateException("createNewsItem error: " + layout);
        }
        return item;
    }
}
