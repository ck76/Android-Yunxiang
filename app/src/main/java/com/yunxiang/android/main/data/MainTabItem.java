
package com.yunxiang.android.main.data;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.yunxiang.android.base.BaseFragment;

import com.yunxiang.android.base.BaseFragment;


public class MainTabItem {

    /** Fragment */
    private Class<? extends BaseFragment> mFragmentClass;
    /** Tab标题 */
    @StringRes
    private int mTitle;
    /** 正常态资源 */
    @DrawableRes
    private int mNormalDrawable;
    /** 选择态资源 */
    @DrawableRes
    private int mSelectedDrawable;

    public MainTabItem(Class<? extends BaseFragment> fragmentClass, int title,
                       int normalDrawable, int selectedDrawable) {
        mFragmentClass = fragmentClass;
        mTitle = title;
        mNormalDrawable = normalDrawable;
        mSelectedDrawable = selectedDrawable;
    }


    public Class<? extends BaseFragment> getFragmentClass() {
        return mFragmentClass;
    }

    @StringRes
    public int getTitle() {
        return mTitle;
    }

    @DrawableRes
    public int getNormalDrawable() {
        return mNormalDrawable;
    }

    @DrawableRes
    public int getSelectedDrawable() {
        return mSelectedDrawable;
    }
}
