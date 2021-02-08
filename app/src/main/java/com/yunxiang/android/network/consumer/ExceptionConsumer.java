package com.yunxiang.android.network.consumer;

import com.neuqer.android.network.exception.ExceptionEngine;
import com.neuqer.android.runtime.AppRuntime;
import com.neuqer.android.util.ToastUtil;

import io.reactivex.functions.Consumer;

public class ExceptionConsumer implements Consumer<Throwable> {

    @Override
    public void accept(Throwable throwable) throws Exception {
        ToastUtil.show(AppRuntime.getAppContext(),
                ExceptionEngine.handleException(throwable).getMsg());
    }
}
