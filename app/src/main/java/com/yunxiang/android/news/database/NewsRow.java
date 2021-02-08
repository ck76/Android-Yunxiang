package com.yunxiang.android.news.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = NewsRow.TABLE_NAME,
        indices = {@Index(value = {"news_id", "tab_id"}, unique = true)})
public class NewsRow {

    /** 表名字 */
    public static final String TABLE_NAME = "news_list";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int id;

    @ColumnInfo(name = "news_id")
    public String newId;

    @ColumnInfo(name = "tab_id")
    public int tabId;

    @ColumnInfo(name = "layout")
    public String layout;

    @ColumnInfo(name = "data")
    public String data;

    @ColumnInfo(name = "is_read")
    public int isRead;

    @ColumnInfo(name = "ext")
    public String ext;
}
