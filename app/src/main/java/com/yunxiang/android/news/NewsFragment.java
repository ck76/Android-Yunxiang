
package com.yunxiang.android.news;

import android.support.v4.view.ViewPager;

import com.neuqer.android.ui.tab.PagerSlidingTab;
import com.yunxiang.android.base.BaseFragment;
import com.yunxiang.android.news.view.NewsTabAdapter;

import butterknife.BindView;
import com.yunxiang.android.R;

public class NewsFragment extends BaseFragment {


    @BindView(R.id.tabs)
    PagerSlidingTab mTabs;
    @BindView(R.id.pager)
    ViewPager mPager;

    private NewsTabAdapter adapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initVariable() {
        adapter = new NewsTabAdapter(getChildFragmentManager());
        mPager.setAdapter(adapter);
        mTabs.setViewPager(mPager);
    }

    @Override
    protected void initView() {

    }
}
