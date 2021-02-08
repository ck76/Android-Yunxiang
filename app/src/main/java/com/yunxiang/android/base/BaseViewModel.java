package com.yunxiang.android.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BaseViewModel extends ViewModel {

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        mDisposable.clear();
        super.onCleared();
    }

    protected void register(Disposable disposable) {
        mDisposable.add(disposable);
    }
}
