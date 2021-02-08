

package com.yunxiang.android.news.fragment;

import com.yunxiang.android.news.model.TabModel;
import com.yunxiang.android.news.view.IPagerView;


public class RNNewsTabFragment extends NewsTabFragment {
    @Override
    protected IPagerView obtainPagerViewImpl() {
        return null;
    }

    public static NewsTabFragment newInstance(TabModel tab) {
        return null;
    }
}
