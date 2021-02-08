package com.yunxiang.android.base.paging.api;

import com.yunxiang.android.base.model.BaseModel;

import java.util.List;


public interface PagingCallBack {

    void onSuccess(List<? extends BaseModel> list);


    void onError(Exception e);
}
