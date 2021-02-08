
package com.yunxiang.android.news.data;

import com.neuqer.android.mock.news.NewDataFlowMock;
import com.yunxiang.android.news.parser.NewsFlowModelParser;

public class NewsDataManager {


    /**
     * 私有构造方法
     */
    private NewsDataManager() {
    }

    public static NewsDataManager get() {
        return NewDataManagerHolder.sInstance;
    }

    /**
     * 网络获取数据
     *
     * @param fetchListener 回调接口
     */
    public void fetchDataAsync(int page, OnFetchListener fetchListener) {
        String data = new NewDataFlowMock().getMockData(page, 25);
        if (fetchListener != null) {
            fetchListener.onFetchNews(NewsFlowModelParser.parseNewsFlow(data), 0);
        }
    }

    /**
     * 静态内部类实现单例
     */
    private static class NewDataManagerHolder {
        private static final NewsDataManager sInstance = new NewsDataManager();
    }
}
