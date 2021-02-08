package com.yunxiang.android.base.paging.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;


public class PagingDataSourceFactory<T> extends DataSource.Factory<Integer, T> {


    private MutableLiveData<PagingDataSource<T>> mDataSource = new MutableLiveData<>();

    public PagingDataSourceFactory(PagingDataSource<T> dataSource) {
        mDataSource.setValue(dataSource);
    }

    @Override
    public DataSource<Integer, T> create() {
        return mDataSource.getValue();
    }

    public MutableLiveData<PagingDataSource<T>> getDataSource() {
        return mDataSource;
    }
}
