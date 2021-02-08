package com.yunxiang.android.search.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yunxiang.android.search.SearchResultViewModel;
import com.yunxiang.android.search.data.SearchResultRepository;
import com.yunxiang.android.search.model.ResultTab;
import com.yunxiang.android.search.model.SortType;
import com.yunxiang.android.search.view.SearchResultFragment;

import java.util.List;


public class ResultPagerAdapter extends FragmentPagerAdapter {

    private List<ResultTab> mTabList;
    private SearchResultFragment mCurFragment;
    private SearchResultViewModel mViewModel;

    public ResultPagerAdapter(FragmentManager fm) {
        super(fm);
        mTabList = SearchResultRepository.getInstance().getTabList();
    }

    @Override
    public Fragment getItem(int i) {
        mCurFragment = SearchResultFragment.getInstance(mTabList.get(i));
        mViewModel = mCurFragment.getViewModel();
        return mCurFragment;
    }

    @Override
    public int getCount() {
        return mTabList == null ? 0 : mTabList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position).getTitle();
    }

    public void sort(SortType sortType) {
        if (mViewModel != null) {
            mViewModel.sort(sortType);
        }
    }

    public void filter(long min, long max, int tradeType) {
        if (mViewModel != null) {
            mViewModel.filter(min, max, tradeType);
        }
    }
}
