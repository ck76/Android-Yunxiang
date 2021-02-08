
package com.yunxiang.android.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.neuqer.android.ui.searchview.MaterialSearchView;
import com.neuqer.android.ui.searchview.OnQueryTextListener;
import com.neuqer.android.ui.searchview.SearchViewListener;
import com.yunxiang.android.base.BaseFragment;
import com.yunxiang.android.home.adapter.HomeAdapter;
import com.yunxiang.android.home.data.HomeDataManager;
import com.yunxiang.android.home.model.HomeActivityModel;
import com.yunxiang.android.home.model.HomeBannerModel;
import com.yunxiang.android.home.model.HomeCategoryModel;
import com.yunxiang.android.home.model.HomeCommodity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import com.yunxiang.android.R;
import com.yunxiang.android.search.view.SearchActivity;


public class HomeFragment extends BaseFragment {

//    @BindView(R.id.home_toolbar)
//    Toolbar mHomeToolbar;
//    @BindView(R.id.search_view)
//    MaterialSearchView mSearchView;
    @BindView(R.id.home_recycler_view)
    RecyclerView mHomeRecyclerView;
    @BindView(R.id.txt_search)
    TextView mSearchTxt;

    private static final int HOME_GRID_SPAN_COUNT = 2;
    private HomeBannerModel mBannerData;
    private HomeCategoryModel mCategoryData;
    private HomeActivityModel mActivityData;
    private HomeAdapter mHomeAdapter;
    private GridLayoutManager mLayoutManager;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariable() {
        mBannerData = HomeDataManager.getInstance().getHomeBanner();
        mCategoryData = HomeDataManager.getInstance().getHomeCategory();

        List<HomeActivityModel.Activity> activity = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            activity.add(new HomeActivityModel.Activity("http://img.hellofhy.cn/19-1-1/31970855.jpg", ""));
        }
        mActivityData = new HomeActivityModel(activity);

        List<HomeCommodity> commodityList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String img = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3603392925," +
                    "3760071205&fm=26&gp=0.jpg";
            commodityList.add(new HomeCommodity(i, img, "很长的阿斯达萨达奥术大师多大叔大婶大萨达多爱迪生大所商品：" + i, i * 100.0));
        }

        mHomeAdapter = new HomeAdapter(getActivity(), mBannerData, mCategoryData, mActivityData, commodityList);
        mLayoutManager = new GridLayoutManager(getActivity(), HOME_GRID_SPAN_COUNT);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == mHomeAdapter.getBannerPosition()
                        || position == mHomeAdapter.getCategoryPosition()
                        || position == mHomeAdapter.getActivityPosition()) {
                    return HOME_GRID_SPAN_COUNT;
                } else {
                    return 1;
                }
            }
        });
        mSearchTxt.setOnClickListener(v -> startActivity(new Intent(getContext(), SearchActivity.class)));
    }

    @Override
    protected void initView() {
//        // 搜索框
//        setHasOptionsMenu(true);
//        if (getActivity() instanceof AppCompatActivity) {
//            ((AppCompatActivity) getActivity()).setSupportActionBar(mHomeToolbar);
//        }
//        mSearchView.setVoiceSearch(false);
//        mSearchView.setCursorDrawable(R.drawable.color_cursor_white);
//        mSearchView.setEllipsize(true);
//        mSearchView.setOnQueryTextListener(new OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getActivity(), "Query: " + query, Toast.LENGTH_LONG).show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//        mSearchView.setOnSearchViewListener(new SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//
//            }
//        });
        mHomeRecyclerView.setLayoutManager(mLayoutManager);
        mHomeRecyclerView.setAdapter(mHomeAdapter);
    }

    @Override
    protected void loadData() {

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
//        inflater.inflate(R.menu.menu_main, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        mSearchView.setMenuItem(item);
//    }
//
//    @Override
//    public boolean onBackPressed() {
//        if (mSearchView.isSearchOpen()) {
//            mSearchView.closeSearch();
//            return true;
//        } else {
//            return super.onBackPressed();
//        }
//    }
}