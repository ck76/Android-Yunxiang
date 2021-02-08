package com.yunxiang.android.base.paging.api;


public interface PagingRepository {

    void getDataList(int offset, int count, PagingCallBack callBack);
}
