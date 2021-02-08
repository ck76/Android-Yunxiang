package com.yunxiang.android.news.database;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface NewsDao {

    @Query("select * from news_list where tab_id = :tabId order by _id desc")
    List<NewsRow> getAll(int tabId);

    @Query("select * from news_list where tab_id = :tabId order by _id desc")
    DataSource.Factory<Integer, NewsRow> getNewsSource(int tabId);

    @Query("select * from news_list where tab_id = :tabId and news_id = :newsId")
    NewsRow getNews(int tabId, String newsId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<NewsRow> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(NewsRow item);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItem(NewsRow table);
}
