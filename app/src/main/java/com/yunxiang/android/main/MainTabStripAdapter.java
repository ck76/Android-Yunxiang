
package com.yunxiang.android.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;

import com.neuqer.android.runtime.AppRuntime;
import com.neuqer.android.ui.tab.GradientTabStrip;
import com.yunxiang.android.main.data.MainTabDataManager;


public class MainTabStripAdapter extends FragmentPagerAdapter implements GradientTabStrip.GradientTabAdapter {


    public MainTabStripAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        try {
            Class fragmentClass = MainTabDataManager.getFragmentClass(position);
            if (fragmentClass != null) {
                return (Fragment) fragmentClass.newInstance();
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return MainTabDataManager.getMainTabItemCount();
    }

    @Override
    public Drawable getNormalDrawable(int position, Context context) {
        return ContextCompat.getDrawable(context, MainTabDataManager.getTabNormalDrawable(position));
    }

    @Override
    public Drawable getSelectedDrawable(int position, Context context) {
        return ContextCompat.getDrawable(context, MainTabDataManager.getTabSelectedDrawable(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return AppRuntime.getAppContext().getString(MainTabDataManager.getTabTitle(position));
    }

    @Override
    public boolean isTagEnable(int position) {
        return false;
    }

    @Override
    public String getTag(int position) {
        return null;
    }
}
