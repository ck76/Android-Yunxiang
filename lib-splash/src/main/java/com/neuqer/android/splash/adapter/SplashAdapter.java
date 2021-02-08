
package com.neuqer.android.splash.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.neuqer.android.splash.model.Ad;
import com.neuqer.android.splash.model.Guide;
import com.neuqer.android.splash.model.Slogan;
import com.neuqer.android.splash.model.Splash;
import com.neuqer.android.splash.ui.GuideFragment;
import com.neuqer.android.splash.ui.SloganFragment;
import com.neuqer.android.splash.ui.SplashAdFragment;

import java.util.List;

public class SplashAdapter extends FragmentPagerAdapter {

    private List<Splash> mSplashList;

    public SplashAdapter(FragmentManager fm, List<Splash> splashList) {
        super(fm);
        this.mSplashList = splashList;
    }

    @Override
    public Fragment getItem(int position) {
        Splash splash = mSplashList.get(position);
        Bundle bundle = new Bundle();
        if (splash instanceof Guide) {
            bundle.putInt(GuideFragment.KEY_IMG_RES, ((Guide) splash).getImgRes());
            bundle.putBoolean(GuideFragment.KEY_SHOW_ENTER, ((Guide) splash).isShowInter());
            GuideFragment guideFragment = new GuideFragment();
            guideFragment.setArguments(bundle);
            return guideFragment;
        } else if (splash instanceof Slogan) {
            bundle.putInt(SloganFragment.KEY_IMG_RES, ((Slogan) splash).getImgRes());
            SloganFragment sloganFragment = new SloganFragment();
            sloganFragment.setArguments(bundle);
            return sloganFragment;
        } else if (splash instanceof Ad) {
            bundle.putString(GuideFragment.KEY_IMG_RES, ((Ad) splash).getImgPath());
            SplashAdFragment adFragment = new SplashAdFragment();
            adFragment.setArguments(bundle);
            return adFragment;
        } else {
            throw new IllegalStateException("不支持的引导页类型");
        }
    }

    @Override
    public int getCount() {
        return mSplashList != null ? mSplashList.size() : 0;
    }
}
