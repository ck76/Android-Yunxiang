package com.yunxiang.android.search.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseActivity;
import com.yunxiang.android.search.data.ResultSortManager;
import com.yunxiang.android.search.model.SortType;
import com.yunxiang.android.search.view.adapter.ResultPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author fhyPayaso
 */
public class SearchResultActivity extends BaseActivity {

    public static final String KEY_SEARCH_CONTENT = "key_search_content";
    @BindView(R.id.img_search_back)
    ImageView mBackImg;
    @BindView(R.id.txt_search)
    TextView mSearchTxt;
    @BindView(R.id.img_result_filter)
    ImageView mFilterShowImg;
    @BindView(R.id.result_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.result_view_pager)
    ViewPager mViewPager;
    @BindView(R.id.result_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.txt_filter_buyout)
    TextView mFilterBuyoutTxt;
    @BindView(R.id.txt_filter_auction)
    TextView mFilterAuctionTxt;
    @BindView(R.id.edit_min_price)
    EditText mMinPriceEdit;
    @BindView(R.id.edit_max_price)
    EditText mMaxPriceEdit;
    @BindView(R.id.img_result_filter_hide)
    ImageView mFilterHideImg;


    private String mSearchContent;
    private ResultPagerAdapter mAdapter;
    private int mTradeType;
    private boolean mTimeIsDown, mRangeIsDown, mSpeedIsDown, mPriceIsDown;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initVariable() {
        Intent intent = getIntent();
        mSearchContent = intent.getStringExtra(KEY_SEARCH_CONTENT);
    }

    @Override
    protected void initView() {
        mBackImg.setOnClickListener(v -> finish());
        mFilterShowImg.setOnClickListener(v -> mDrawerLayout.openDrawer(Gravity.END));
        mFilterHideImg.setOnClickListener(v -> mDrawerLayout.closeDrawer(Gravity.END));
        mSearchTxt.setText(mSearchContent);
        mSearchTxt.setOnClickListener(v -> {
            startActivity(SearchActivity.class);
            finish();
        });
        mAdapter = new ResultPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        initListState();
    }


    public static void start(Context context, String search) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra(KEY_SEARCH_CONTENT, search);
        context.startActivity(intent);
    }


    @Override
    protected boolean toolbarEnable() {
        return false;
    }

    @OnClick({R.id.txt_sort_time,
            R.id.txt_sort_up,
            R.id.txt_sort_down,
            R.id.txt_sort_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_sort_time:
                mTimeIsDown = !mTimeIsDown;
                mAdapter.sort(SortType.byTime(mTimeIsDown));
                break;
            case R.id.txt_sort_up:
                mRangeIsDown = !mRangeIsDown;
                mAdapter.sort(SortType.byRange(mRangeIsDown));
                break;
            case R.id.txt_sort_down:
                mSpeedIsDown = !mSpeedIsDown;
                mAdapter.sort(SortType.bySpeed(mSpeedIsDown));
                break;
            case R.id.txt_sort_price:
                mPriceIsDown = !mPriceIsDown;
                mAdapter.sort(SortType.byPrice(mPriceIsDown));
                break;
        }
    }

    public void initListState() {
        mTimeIsDown = true;
        mPriceIsDown = true;
        mRangeIsDown = true;
        mSpeedIsDown = true;
        mAdapter.sort(SortType.byTime(true));
        mTradeType = ResultSortManager.TRADE_TYPE_UNKNOWN;
        mFilterBuyoutTxt.setSelected(false);
        mFilterAuctionTxt.setSelected(false);
        mMaxPriceEdit.setText("");
        mMinPriceEdit.setText("");
    }

    @OnClick(R.id.txt_filter_buyout)
    public void onMFilterBuyoutTxtClicked() {
        mTradeType = ResultSortManager.TRADE_TYPE_BUYOUT;
        mFilterBuyoutTxt.setSelected(true);
        mFilterAuctionTxt.setSelected(false);
    }

    @OnClick(R.id.txt_filter_auction)
    public void onMFilterAuctionTxtClicked() {
        mTradeType = ResultSortManager.TRADE_TYPE_AUCTION;
        mFilterBuyoutTxt.setSelected(false);
        mFilterAuctionTxt.setSelected(true);

    }

    @OnClick(R.id.txt_filter_confirm)
    public void onTxtFilterConfirmClicked() {
        String minStr = mMinPriceEdit.getText().toString().trim();
        String maxStr = mMaxPriceEdit.getText().toString().trim();
        long minLong = minStr.isEmpty() ? ResultSortManager.MIN_PRICE : Long.valueOf(minStr);
        long maxLong = maxStr.isEmpty() ? ResultSortManager.MAX_PRICE : Long.valueOf(maxStr);
        mAdapter.filter(minLong, maxLong, mTradeType);
        mDrawerLayout.closeDrawer(Gravity.END);
    }
}
