

package com.neuqer.android.splash.ui;

import android.support.v4.view.ViewPager;

import com.neuqer.android.runtime.base.activity.AbsActivity;
import com.neuqer.android.splash.R;
import com.neuqer.android.splash.R2;
import com.neuqer.android.splash.SplashManager;
import com.neuqer.android.splash.SplashRuntime;
import com.neuqer.android.splash.adapter.SplashAdapter;

import butterknife.BindView;


public class SplashActivity extends AbsActivity implements OnEnterHomeListener {


    @BindView(R2.id.view_pager)
    ViewPager viewPager;

    private SplashAdapter mSplashAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initVariable() {
        mSplashAdapter = new SplashAdapter(getSupportFragmentManager(), SplashManager.getSplashData());
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(mSplashAdapter);
    }

    @Override
    public void enterHome() {
        SplashRuntime.getContext().startHomeActivity(SplashActivity.this);
        finish();
    }

    @Override
    protected boolean toolbarEnable() {
        return false;
    }
}
