
package com.yunxiang.android.news.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yunxiang.android.news.model.TabModel;

import java.util.List;

import com.yunxiang.android.news.data.TabDataManager;
import com.yunxiang.android.news.fragment.NANewsTabFragment;
import com.yunxiang.android.news.model.TabModel;


public class NewsTabAdapter extends FragmentPagerAdapter {

    private List<TabModel> mTabModelList;

    public NewsTabAdapter(FragmentManager fm) {
        super(fm);
        mTabModelList = TabDataManager.getInstance().getTabList();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabModelList.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return mTabModelList != null ? mTabModelList.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {
        return NANewsTabFragment.newInstance(mTabModelList.get(position));
    }
}
