
package com.yunxiang.android.main.data;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.yunxiang.android.base.BaseFragment;
import com.yunxiang.android.news.NewsFragment;
import com.yunxiang.android.personal.PersonalFragment;
import com.yunxiang.android.price.PriceFragment;

import java.util.ArrayList;
import java.util.List;

import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseFragment;
import com.yunxiang.android.home.HomeFragment;
import com.yunxiang.android.news.NewsFragment;
import com.yunxiang.android.personal.PersonalFragment;
import com.yunxiang.android.price.PriceFragment;


public class MainTabDataManager {

    /** 数据集合 */
    private static List<MainTabItem> sMainTabItems;

    static {
        sMainTabItems = new ArrayList<>();
        sMainTabItems.add(new MainTabItem(
                HomeFragment.class,
                R.string.main_tab_home,
                R.drawable.ic_home_normal,
                R.drawable.ic_home_selected));
        sMainTabItems.add(new MainTabItem(
                PriceFragment.class,
                R.string.main_tab_price,
                R.drawable.ic_price_normal,
                R.drawable.ic_price_selected));
        sMainTabItems.add(new MainTabItem(
                NewsFragment.class,
                R.string.main_tab_news,
                R.drawable.ic_news_normal,
                R.drawable.ic_news_selected));
        sMainTabItems.add(new MainTabItem(
                PersonalFragment.class,
                R.string.main_tab_personal,
                R.drawable.ic_personal_normal,
                R.drawable.ic_personal_selected));
    }

    public static int getMainTabItemCount() {
        return sMainTabItems != null ? sMainTabItems.size() : 0;
    }

    public static Class<? extends BaseFragment> getFragmentClass(int position) {
        if (position < 0 || position > getMainTabItemCount()) {
            return null;
        }
        return sMainTabItems.get(position).getFragmentClass();
    }

    @StringRes
    public static int getTabTitle(int position) {
        if (position < 0 || position > getMainTabItemCount()) {
            return -1;
        }
        return sMainTabItems.get(position).getTitle();
    }

    @DrawableRes
    public static int getTabNormalDrawable(int position) {
        if (position < 0 || position > getMainTabItemCount()) {
            return -1;
        }
        return sMainTabItems.get(position).getNormalDrawable();
    }

    @DrawableRes
    public static int getTabSelectedDrawable(int position) {
        if (position < 0 || position > getMainTabItemCount()) {
            return -1;
        }
        return sMainTabItems.get(position).getSelectedDrawable();
    }
}
