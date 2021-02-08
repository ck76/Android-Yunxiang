
package com.yunxiang.android.news.data;

import com.yunxiang.android.news.model.NewsFlowModel;

import com.yunxiang.android.news.model.NewsFlowModel;


public interface OnFetchListener {

    /**
     * 拉取成功
     *
     * @param newsFlowModel 资讯流Model
     * @param status        状态码
     */
    void onFetchNews(NewsFlowModel newsFlowModel, int status);

}
