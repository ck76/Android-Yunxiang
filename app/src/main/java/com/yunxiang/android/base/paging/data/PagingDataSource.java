package com.yunxiang.android.base.paging.data;

import android.arch.paging.DataSource;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.yunxiang.android.base.paging.PagingViewModel;
import com.yunxiang.android.base.paging.api.PagingCallBack;
import com.yunxiang.android.base.paging.api.PagingRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class PagingDataSource<T> extends PositionalDataSource<T> implements DataSource.InvalidatedCallback {

    private Runnable mRetry;
    private PagingViewModel<T> mViewModel;
    private PagingRepository mApi;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public PagingDataSource(PagingViewModel<T> viewModel, PagingRepository api) {
        this.mViewModel = viewModel;
        this.mApi = api;

        register(mViewModel.registerRetry().subscribe(s -> {
            if (mRetry != null) {
                mRetry.run();
                mRetry = null;
            }
        }, Throwable::printStackTrace));

        addInvalidatedCallback(this);
    }

    private void register(Disposable disposable) {
        mDisposable.add(disposable);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<T> callback) {

        mViewModel.getRefreshState().postValue(NetworkStatus.loading());


        mApi.getDataList(0, PagingViewModel.INIT_LOAD_SIZE, new PagingCallBack() {
            @Override
            public void onSuccess(List list) {
                mViewModel.getRefreshState().postValue(NetworkStatus.success());
                mViewModel.hasMore().postValue(true);
                if (isEmptyList(list)) {
                    callback.onResult(new ArrayList<>(), 0);
                } else {
                    callback.onResult(new ArrayList(list), 0);
                }
            }

            @Override
            public void onError(Exception e) {
                mViewModel.getRefreshState().postValue(NetworkStatus.error());
                mRetry = () -> loadInitial(params, callback);
            }
        });
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<T> callback) {

        mViewModel.getLoadingState().postValue(NetworkStatus.loading());
        mApi.getDataList(params.startPosition, PagingViewModel.PAGE_SIZE, new PagingCallBack() {
            @Override
            public void onSuccess(List list) {
                mViewModel.getLoadingState().postValue(NetworkStatus.success());
                if (isEmptyList(list)) {
                    callback.onResult(new ArrayList<>());
                } else {
                    callback.onResult(list);
                }
            }

            @Override
            public void onError(Exception e) {
                mViewModel.getLoadingState().postValue(NetworkStatus.error());
                mRetry = () -> loadRange(params, callback);
            }
        });
    }


    private boolean isEmptyList(List list) {
        return list == null || list.isEmpty();
    }


    @Override
    public void onInvalidated() {
        removeInvalidatedCallback(this);
        mDisposable.clear();
    }
}
