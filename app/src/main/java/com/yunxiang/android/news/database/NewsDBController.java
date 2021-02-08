package com.yunxiang.android.news.database;

import android.arch.core.util.Function;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Room;

import com.neuqer.android.util.TypeCallback;
import com.yunxiang.android.news.model.NewsModel;
import com.yunxiang.android.news.parser.NewsItemDataParser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yunxiang.android.news.view.NewsType;
import com.neuqer.android.runtime.AppRuntime;
import com.neuqer.android.util.JsonUtil;


public class NewsDBController {

    /** 数据库实例 */
    private NewsDatabase mDatabase;
    /** IO 线程池 */
    private ExecutorService mExecutorService;

    /**
     * 私有构造方法
     */
    private NewsDBController() {
        mDatabase = Room.databaseBuilder(AppRuntime.getAppContext(), NewsDatabase.class, NewsDatabase.DB_NAME).build();
        mExecutorService = Executors.newCachedThreadPool();
    }

    /**
     * 获取单例
     */
    public static NewsDBController getInstance() {
        return Holder.sInstance;
    }

    /**
     * 获取资讯数据对象
     *
     * @param tabId 资讯TabId
     */
    public void getNewsDataSource(int tabId, TypeCallback<DataSource.Factory<Integer, NewsModel>> callback) {
        if (callback == null) {
            return;
        }
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                DataSource.Factory<Integer, NewsModel> source = mDatabase.getNewsDao().getNewsSource(tabId)
                        .map(new Function<NewsRow, NewsModel>() {
                            @Override
                            public NewsModel apply(NewsRow input) {
                                return convertNewRow(input);
                            }
                        });
                callback.onCallback(source);
            }
        });
    }

    /**
     * 查询指定Tab下的所有News数据
     *
     * @param tabId TabId
     */
    public void getAllNews(int tabId, TypeCallback<List<NewsModel>> callback) {
        if (callback == null) {
            return;
        }
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                List<NewsRow> rowList = mDatabase.getNewsDao().getAll(tabId);
                if (rowList == null) {
                    callback.onCallback(null);
                }
                List<NewsModel> newsModelList = new ArrayList<>(rowList.size());
                for (NewsRow row : rowList) {
                    newsModelList.add(convertNewRow(row));
                }
                callback.onCallback(newsModelList);
            }
        });
    }

    /**
     * 查询特定资讯
     *
     * @param tabId  TabId
     * @param newsId 资讯Id
     */
    public void getNews(int tabId, String newsId, TypeCallback<NewsModel> callback) {
        if (callback == null) {
            return;
        }
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                NewsRow row = mDatabase.getNewsDao().getNews(tabId, newsId);
                callback.onCallback(convertNewRow(row));
            }
        });

    }

    /**
     * 存储资讯
     *
     * @param list 资讯列表
     */
    public void saveNews(int tabId, List<NewsModel> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        List<NewsRow> rowList = new ArrayList<>();
        for (NewsModel model : list) {
            model.tabId = tabId;
            rowList.add(convertNewModel(model));
        }
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.getNewsDao().insertList(rowList);
            }
        });
    }

    /**
     * 存储资讯
     *
     * @param item 资讯
     */
    public void saveNews(NewsModel item) {
        if (item == null) {
            return;
        }
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.getNewsDao().insertItem(convertNewModel(item));
            }
        });
    }

    /**
     * 更新资讯
     *
     * @param item 资讯
     */
    public void updateItem(NewsModel item) {
        if (item == null) {
            return;
        }
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.getNewsDao().updateItem(convertNewModel(item));
            }
        });
    }

    /**
     * {@link NewsRow} 转换为 {@link NewsModel}
     */
    private NewsModel convertNewRow(NewsRow row) {
        if (row == null) {
            return null;
        }
        NewsModel model = new NewsModel();
        model.id = row.newId;
        model.tabId = row.tabId;
        model.isRead = row.isRead;
        model.layout = row.layout;
        model.data = NewsItemDataParser.parseItemData(NewsType.getLayout(row.layout),
                JsonUtil.parseString(row.data));
        return model;
    }

    /**
     * {@link NewsModel} 转换为 {@link NewsRow}
     */
    private NewsRow convertNewModel(NewsModel model) {
        if (model == null) {
            return null;
        }
        NewsRow row = new NewsRow();
        row.newId = model.id;
        row.tabId = model.tabId;
        row.isRead = model.isRead;
        row.layout = model.layout;
        row.data = model.data.toJson().toString();
        return row;
    }

    /**
     * 单例内部类
     */
    private static class Holder {
        private static NewsDBController sInstance = new NewsDBController();
    }
}
