
package com.yunxiang.android.main;

import android.widget.ImageView;
import android.widget.Toast;

import com.neuqer.android.ui.ScrollBanViewPager;
import com.neuqer.android.ui.popmenu.PopMenu;
import com.neuqer.android.ui.popmenu.PopMenuItem;
import com.neuqer.android.ui.popmenu.PopMenuItemClickListener;
import com.neuqer.android.ui.tab.GradientTabStrip;
import com.yunxiang.android.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import com.yunxiang.android.R;
import com.yunxiang.android.product.view.activity.ProductAssessActivity;
import com.yunxiang.android.product.view.activity.ProductPublishActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_view_pager)
    ScrollBanViewPager mViewPager;
    @BindView(R.id.main_tab_strip)
    GradientTabStrip mTabStrip;
    @BindView(R.id.main_tab_center_plus)
    ImageView mHomePlusTab;

    private MainTabStripAdapter mTabStripAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected String getActivityTitle() {
        return getString(R.string.activity_title_main);
    }

    @Override
    protected void initVariable() {
        mTabStripAdapter = new MainTabStripAdapter(getSupportFragmentManager());
    }

    @Override
    protected void initView() {
        mViewPager.setScanScroll(false);
        mViewPager.setAdapter(mTabStripAdapter);
        mTabStrip.setAdapter(mTabStripAdapter);
        mTabStrip.bindViewPager(mViewPager);
    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.main_tab_center_plus)
    void showPlusMenu() {
        PopMenu popMenu = new PopMenu.Builder(MainActivity.this)
                .addMenuItem(new PopMenuItem(1, "", getDrawable(R.drawable.ic_main_tab_plus_publish)))
                .addMenuItem(new PopMenuItem(2, "", getDrawable(R.drawable.ic_main_tab_plus_assess)))
                .setColumn(2)
                .setItemClickListener(new PopMenuItemClickListener() {
                    @Override
                    public void onItemClick(PopMenuItem item) {
                        switch (item.getId()) {
                            case 1:
                                startActivity(ProductPublishActivity.class);
                                break;
                            case 2:
                                startActivity(ProductAssessActivity.class);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .setHorizontalPadding(24)
                .setMarginToBottom(96)
                .build();
        popMenu.show();
    }

    @Override
    protected boolean toolbarEnable() {
        return false;
    }
}
