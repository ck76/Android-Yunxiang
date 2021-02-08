package com.yunxiang.android.account.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.neuqer.android.ui.dialog.LoadingDialog;
import com.yunxiang.android.account.CheckFormatHelper;
import com.yunxiang.android.account.data.AccountDataCenter;
import com.yunxiang.android.account.data.AccountRepository;
import com.yunxiang.android.base.BaseActivity;


public abstract class AccountBaseActivity extends BaseActivity {

    protected LoadingDialog mLoadingDialog;
    protected AccountRepository mRepository;
    protected AccountDataCenter mDataCenter;
    protected CheckFormatHelper mFormatHelper;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mLoadingDialog = new LoadingDialog(this);
        mFormatHelper = new CheckFormatHelper(this);
        mRepository = AccountRepository.getInstance();
        mDataCenter = AccountDataCenter.getInstance();
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    protected void startActivity(Class<?> clazz, boolean withFinish) {
        startActivity(clazz);
        if (withFinish) {
            finish();
        }
    }

    protected void startActivity(Class<?> clazz, Bundle bundle, boolean withFinish) {
        startActivity(clazz, bundle);
        if (withFinish) {
            finish();
        }
    }
}
