
package com.yunxiang.android.news.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.yunxiang.android.news.view.IPagerView;

import com.yunxiang.android.news.view.IPagerView;

public abstract class NewsTabFragment extends Fragment {

    protected static final String EXTRA_TAB_ID = "tab_id";

    private Activity mContext;
    private FrameLayout mRootView;
    private IPagerView mPagerView;
    private View mContentView;
    protected int mTabId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTabId = bundle.getInt(EXTRA_TAB_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        if (mRootView == null) {
            mRootView = new FrameLayout(mContext);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            mRootView.setLayoutParams(params);
        }
        // 初始化View
        if (!isContentViewInflated()) {
            addToRootView(createEmptyView());
            inflateContentView();
        }
        return mRootView;
    }

    private boolean isContentViewInflated() {
        return mPagerView != null && mContentView != null;
    }

    private void createPagerViewIfNeed() {
        if (mPagerView == null) {
            mPagerView = obtainPagerViewImpl();
            mPagerView.init(mContext);
        }
    }

    private void inflateContentView() {
        createPagerViewIfNeed();
        mContentView = mPagerView.onCreateView(mContext, null);
        addToRootView(mContentView);
    }

    private void addToRootView(View childView) {
        if (childView != null && childView.getParent() != null) {
            ViewGroup parent = (ViewGroup) childView.getParent();
            parent.removeView(childView);
        }

        if (mRootView != null && childView != null) {
            mRootView.removeAllViews();
            mRootView.addView(childView);
        }
    }

    private View createEmptyView() {
        // TODO: 2018/10/10 空白View
        return new View(mContext);
    }

    protected abstract IPagerView obtainPagerViewImpl();
}
