package com.yunxiang.android.search;

import android.arch.lifecycle.MutableLiveData;

import com.yunxiang.android.base.paging.PagingViewModel;
import com.yunxiang.android.base.paging.data.PagingDataSource;
import com.yunxiang.android.personal.data.Jade;
import com.yunxiang.android.personal.data.JadeRepository;
import com.yunxiang.android.search.data.ResultSortManager;
import com.yunxiang.android.search.model.SortType;

import java.util.List;


public class SearchResultViewModel extends PagingViewModel<Jade> {

    private MutableLiveData<List<Jade>> mShowDataList;

    private ResultSortManager mSortManager;

    public SearchResultViewModel() {
        mShowDataList = new MutableLiveData<>();
        mSortManager = ResultSortManager.getInstance();
    }

    @Override
    protected PagingDataSource<Jade> getDataSource() {
        return new PagingDataSource<>(this, new JadeRepository());
    }


    public void sort(SortType sortType) {
//        mShowDataList.setValue(mSortManager.sort(getDataList().getValue(), sortType));
        showList(mSortManager.sort(getDataList().getValue(), sortType));
    }

    public void filter(long min, long max, int tradeType) {
//        mShowDataList.setValue(mSortManager.filter(getDataList().getValue(), min, max, tradeType));
        showList(mSortManager.filter(getDataList().getValue(), min, max, tradeType));
    }

    public MutableLiveData<List<Jade>> getShowDataList() {
        return mShowDataList;
    }
}
