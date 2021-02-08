
package com.yunxiang.android.news.filter;

import android.text.TextUtils;

import com.yunxiang.android.news.model.NewsItemOneImg;
import com.yunxiang.android.news.model.NewsItemThreeImg;
import com.yunxiang.android.news.view.NewsType;
import com.yunxiang.android.news.model.NewsItemData;
import com.yunxiang.android.news.model.NewsModel;


public class NewsModelFilter {

    public static final int OK = 0;
    public static final int ERROR_EMPTY = 1;
    public static final int ERROR_NO_DATA = 2;
    public static final int ERROR_INVALID_LAYOUT = 3;
    public static final int ERROR_NO_TITLE = 4;
    public static final int ERROR_NO_IMG_URL = 5;


    /**
     * 校验数据的准确性
     *
     * @param newsModel
     * @return 错误码
     */
    public static int check(NewsModel newsModel) {
        if (newsModel == null) {
            return ERROR_EMPTY;
        }
        if (newsModel.data == null) {
            return ERROR_NO_DATA;
        }
        if (TextUtils.isEmpty(newsModel.layout) || !NewsType.isSupportLayout(newsModel.layout)) {
            return ERROR_INVALID_LAYOUT;
        }
        NewsItemData itemData = newsModel.data;
        NewsType layout = NewsType.getLayout(newsModel.layout);
        switch (layout) {
            case TEXT:
                return TextUtils.isEmpty(itemData.title) ? ERROR_NO_TITLE : OK;
            case ONE_IMG_LEFT:
                NewsItemOneImg oneImg = (NewsItemOneImg) itemData;
                if (TextUtils.isEmpty(oneImg.title)) {
                    return ERROR_NO_TITLE;
                }
                return TextUtils.isEmpty(oneImg.imgUrl) ? ERROR_NO_IMG_URL : OK;
            case THREE_IMG:
                NewsItemThreeImg threeImg = (NewsItemThreeImg) itemData;
                if (TextUtils.isEmpty(threeImg.title)) {
                    return ERROR_NO_TITLE;
                }
                if (TextUtils.isEmpty(threeImg.leftImgUrl)
                        || TextUtils.isEmpty(threeImg.midImgUrl)
                        || TextUtils.isEmpty(threeImg.rightImgUrl)) {
                    return ERROR_NO_IMG_URL;
                }
                return OK;
            default:
                return ERROR_INVALID_LAYOUT;
        }
    }

}
