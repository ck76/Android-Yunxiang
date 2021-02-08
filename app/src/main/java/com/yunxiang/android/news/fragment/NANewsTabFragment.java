
package com.yunxiang.android.news.fragment;

import android.os.Bundle;

import com.yunxiang.android.news.model.TabModel;
import com.yunxiang.android.news.view.IPagerView;
import com.yunxiang.android.news.view.NAPagerView;

import com.yunxiang.android.news.model.TabModel;
import com.yunxiang.android.news.view.IPagerView;
import com.yunxiang.android.news.view.NAPagerView;

public class NANewsTabFragment extends NewsTabFragment {

    @Override
    protected IPagerView obtainPagerViewImpl() {
        return new NAPagerView(mTabId);
    }

    public static NewsTabFragment newInstance(TabModel tab) {
        Bundle data = new Bundle();
        if (tab != null) {
            data.putInt(EXTRA_TAB_ID, tab.getId());
        }
        NANewsTabFragment fragment = new NANewsTabFragment();
        fragment.setArguments(data);
        return fragment;
    }
}
