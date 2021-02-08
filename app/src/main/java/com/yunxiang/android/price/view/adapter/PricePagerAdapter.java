package com.yunxiang.android.price.view.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yunxiang.android.R;
import com.yunxiang.android.price.view.PriceRankFragment;


public class PricePagerAdapter extends FragmentPagerAdapter {

    public static final int TYPE_CATEGORY = 0;
    public static final int TYPE_GOODS = 1;
    public static final int TYPE_RISE = 2;
    public static final int TYPE_DROP = 3;

    private static final int TAB_COUNT = 2;

    private static final int[] TAB_TITLE = {
            R.string.price_tab_type_rise,
            R.string.price_tab_type_drop,
            R.string.price_tab_goods_rise,
            R.string.price_tab_goods_drop
    };

    private Context mContext;
    private int mPriceType;


    public PricePagerAdapter(FragmentManager fm, Context context, int priceType) {
        super(fm);
        mContext = context;
        mPriceType = priceType;
    }

    @Override
    public Fragment getItem(int i) {
        return PriceRankFragment.getNewInstance(mPriceType, i);
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        int resId;
        if (position == 0) {
            resId = mPriceType == TYPE_RISE ? TAB_TITLE[0] : TAB_TITLE[1];
        } else {
            resId = mPriceType == TYPE_RISE ? TAB_TITLE[2] : TAB_TITLE[3];
        }
        return mContext.getString(resId);
    }
}
