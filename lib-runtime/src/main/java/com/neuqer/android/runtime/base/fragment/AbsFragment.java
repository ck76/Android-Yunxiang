package com.neuqer.android.runtime.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neuqer.android.runtime.base.FragmentBackHandler;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class AbsFragment extends Fragment implements FragmentBackHandler {

    /** Fragment加载的View */
    private View mView;
    /** ButterKnife */
    private Unbinder mUnBinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutRes(), container, false);
        mUnBinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariable();
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    @Nullable
    public View getFragmentView() {
        return mView;
    }

    /**
     * 获取Fragment布局资源
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * 初始化变量
     */
    protected abstract void initVariable();

    /**
     * 初始化View，挂载相关事件
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected void loadData() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
