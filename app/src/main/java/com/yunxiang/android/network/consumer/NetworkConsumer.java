package com.yunxiang.android.network.consumer;

import com.neuqer.android.network.exception.ApiException;
import com.neuqer.android.network.response.ApiResponse;

import io.reactivex.functions.Consumer;


public abstract class NetworkConsumer<T> implements Consumer<ApiResponse<T>> {

    public abstract void onSuccess(T t);


    @Override
    public void accept(ApiResponse<T> tApiResponse) throws Exception {

        if (tApiResponse == null) {
            throw new ApiException(-1, "null response");
        }

        if (tApiResponse.getCode() == 0) {
            onSuccess(tApiResponse.getData());
            return;
        }
        throw new ApiException(tApiResponse.getCode(), tApiResponse.getMsg());
    }
}
