package com.yunxiang.android.search;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.yunxiang.android.search.data.SearchHistoryRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends ViewModel {


    // 历史保存上限
    public static final int MAX_HISTORY_SAVE = 20;
    // 历史显示上限
    public static final int MAX_HISTORY_SHOW = 9;
    // 热门显示上限
    public static final int MAX_POPULAR_SHOW = 9;


    private int mUserId = 1;
    private MutableLiveData<List<String>> mHistoryList;
    private MutableLiveData<List<String>> mPopularList;


    private MutableLiveData<Boolean> hasMore;
    private SearchHistoryRepository mRepository;


    public SearchViewModel() {
        mHistoryList = new MutableLiveData<>();
        mPopularList = new MutableLiveData<>();
        hasMore = new MutableLiveData<>();
        hasMore.setValue(false);
    }

    public void setContext(Context context) {
        mRepository = new SearchHistoryRepository(context);
    }


    public MutableLiveData<Boolean> getHasMore() {
        if (hasMore.getValue() == null) {
            hasMore.setValue(false);
        }
        return hasMore;
    }

    public MutableLiveData<List<String>> getHistoryList() {
        if (mHistoryList.getValue() == null) {
            mHistoryList.setValue(mRepository.get(mUserId));
        }
        return mHistoryList;
    }

    public MutableLiveData<List<String>> getPopularList() {
        if (mPopularList.getValue() == null) {
            mPopularList.setValue(mockPopular());
        }
        return mPopularList;
    }


    public void addHistory(String search) {
        mRepository.add(mUserId, search);
        showHistory(false);
    }


    public void clearHistory() {
        mRepository.clear(mUserId);
        mHistoryList.setValue(mRepository.get(mUserId));
    }

    public void showHistory(boolean withAll) {
        List<String> list = mRepository.get(mUserId);
        if (list.size() > MAX_HISTORY_SHOW) {
            if(withAll) {
                hasMore.setValue(false);
            } else {
                hasMore.setValue(true);
                list = list.subList(0, MAX_HISTORY_SHOW);
            }
        } else {
            hasMore.setValue(false);
        }
        mHistoryList.setValue(list);
    }


    private List<String> mockPopular() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            list.add("最新款玛瑙首饰");
            list.add("玛瑙竞价抢购");
            list.add("最新款碧玺装饰");
        }
        return list;
    }
}
