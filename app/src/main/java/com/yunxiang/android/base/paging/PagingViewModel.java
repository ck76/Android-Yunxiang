package com.yunxiang.android.base.paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.yunxiang.android.base.paging.data.NetworkStatus;
import com.yunxiang.android.base.paging.data.PagingBoundaryCallback;
import com.yunxiang.android.base.paging.data.PagingDataSource;
import com.yunxiang.android.base.paging.data.PagingDataSourceFactory;

import java.util.List;

import io.reactivex.subjects.PublishSubject;


public abstract class PagingViewModel<T> extends ViewModel {

    public static final int INIT_LOAD_SIZE = 20;

    public static final int PAGE_SIZE = 20;

    private LiveData<PagedList<T>> mDataList;

    private MutableLiveData<NetworkStatus> mRefreshState = new MutableLiveData<>();

    protected MutableLiveData<NetworkStatus> mLoadingState = new MutableLiveData<>();

    protected MutableLiveData<Boolean> isEmpty = new MutableLiveData<>();

    protected MutableLiveData<Boolean> hasMore = new MutableLiveData<>();

    private PublishSubject<String> retry = PublishSubject.create();

    private PagingDataSourceFactory<T> mFactory;


    public PagingViewModel() {
        if (mDataList == null) {
            mFactory = new PagingDataSourceFactory<>(getDataSource());
            mDataList = buildPagedList();
        }
    }

    private LiveData<PagedList<T>> buildPagedList() {
        isEmpty.setValue(false);
        return new LivePagedListBuilder<>(mFactory, getConfig())
                .setBoundaryCallback(new PagingBoundaryCallback<>(hasMore, isEmpty))
                .build();
    }

    public LiveData<PagedList<T>> getDataList() {
        return mDataList;
    }

    public MutableLiveData<NetworkStatus> getLoadingState() {
        return mLoadingState;
    }

    public MutableLiveData<NetworkStatus> getRefreshState() {
        return mRefreshState;
    }

    public MutableLiveData<Boolean> isEmpty() {
        return isEmpty;
    }

    public MutableLiveData<Boolean> hasMore() {
        return hasMore;
    }

    public void retry() {
        retry.onNext("retry");
    }

    public void refresh() {
        mDataList = buildPagedList();
    }

    public void showList(List<T> list) {
        if(mDataList.getValue()!=null) {
            mDataList.getValue().clear();
            mDataList.getValue().addAll(list);
        }
    }


    public PublishSubject<String> registerRetry() {
        return retry;
    }

    private PagedList.Config getConfig() {
        return new PagedList.Config.Builder()
                //配置分页加载的数量
                .setPageSize(PAGE_SIZE)
                //配置是否启动PlaceHolders
                .setEnablePlaceholders(false)
                //初始化加载的数量
                .setInitialLoadSizeHint(INIT_LOAD_SIZE)
                .build();
    }

    protected abstract PagingDataSource<T> getDataSource();
}

