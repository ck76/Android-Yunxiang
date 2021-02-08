package com.yunxiang.android.news.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {NewsRow.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {

    /** 数据库名称 */
    public static final String DB_NAME = "news.db";

    /**
     * 抽象方法，定义CURD操作
     */
    public abstract NewsDao getNewsDao();
}
