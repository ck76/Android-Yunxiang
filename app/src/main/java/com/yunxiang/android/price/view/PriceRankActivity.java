package com.yunxiang.android.price.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseActivity;
import com.yunxiang.android.price.PriceConstants;
import com.yunxiang.android.price.view.adapter.PricePagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceRankActivity extends BaseActivity {


    @BindView(R.id.txt_toolbar_title)
    TextView mTitleTxt;
    @BindView(R.id.price_rank_tab)
    TabLayout mTabLayout;
    @BindView(R.id.price_rank_pager)
    ViewPager mViewPager;
    @BindView(R.id.base_toolbar)
    Toolbar mBaseToolbar;

    private int mPriceType;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_price_rank;
    }

    @Override
    protected String getActivityTitle() {
        return "";
    }

    @Override
    protected void initVariable() {
        Intent intent = getIntent();
        if (intent != null) {
            mPriceType = intent.getIntExtra(PriceConstants.KEY_PRICE_TYPE, PricePagerAdapter.TYPE_RISE);
        }
    }

    @Override
    protected boolean backButtonEnable() {
        return true;
    }

    @Override
    protected void initView() {
        mBaseToolbar.setNavigationOnClickListener(v -> finish());
        mTitleTxt.setText(mPriceType == PricePagerAdapter.TYPE_RISE
                ? getString(R.string.price_chart_title_rise)
                : getString(R.string.price_chart_title_drop));

        PricePagerAdapter adapter = new PricePagerAdapter(getSupportFragmentManager(), this, mPriceType);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected boolean toolbarEnable() {
        return false;
    }

}
