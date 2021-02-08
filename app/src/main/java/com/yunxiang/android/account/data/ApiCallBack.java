package com.yunxiang.android.account.data;

import com.neuqer.android.network.exception.ExceptionEngine;
import com.neuqer.android.network.response.ApiResponse;
import com.neuqer.android.runtime.AppRuntime;
import com.neuqer.android.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class ApiCallBack<T> implements Callback<ApiResponse<T>> {

    private IAccount mErrorListener;

    public ApiCallBack(IAccount errorListener) {
        mErrorListener = errorListener;
    }

    abstract void onDataBack(ApiResponse<T> response);

    @Override
    public void onResponse(Call<ApiResponse<T>> call, Response<ApiResponse<T>> response) {
        if (response != null && response.body() != null) {
            int code = response.body().getCode();
            if (code == AccountRepository.STATUS_CODE_OK) {
                onDataBack(response.body());
            } else {
                mErrorListener.onError(code);
            }
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        ToastUtil.show(AppRuntime.getAppContext(), ExceptionEngine.handleException(t).getMsg());
    }
}
