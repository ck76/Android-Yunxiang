package com.yunxiang.android.base.paging.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;


public class PagingBoundaryCallback<T> extends PagedList.BoundaryCallback<T> {


    private MutableLiveData<Boolean> hasMore;
    private MutableLiveData<Boolean> isEmpty;

    public PagingBoundaryCallback(MutableLiveData<Boolean> hasMore,
                                  MutableLiveData<Boolean> isEmpty) {
        this.hasMore = hasMore;
        this.isEmpty = isEmpty;
    }

    /**
     * 初始化加载为空
     */
    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        isEmpty.postValue(true);
        hasMore.postValue(false);
    }

    /**
     * LoadMore为空
     *
     * @param itemAtEnd 末尾数据
     */
    @Override
    public void onItemAtEndLoaded(@NonNull T itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        hasMore.postValue(false);
    }
}
