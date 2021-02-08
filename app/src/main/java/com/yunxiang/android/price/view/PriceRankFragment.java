package com.yunxiang.android.price.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.neuqer.android.ui.RecyclerViewDivider;
import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseFragment;
import com.yunxiang.android.base.paging.PagingLayoutManager;
import com.yunxiang.android.price.PriceConstants;
import com.yunxiang.android.price.PriceFragment;
import com.yunxiang.android.price.PriceViewModel;
import com.yunxiang.android.price.data.PriceRepository;
import com.yunxiang.android.price.view.adapter.PriceListAdapter;

import butterknife.BindView;

public class PriceRankFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefreshView;

    private PriceViewModel mViewModel;

    /**
     * 价格类型 涨/跌 价
     */
    private int mPriceType;
    /**
     * 榜单类型 品类/商品
     */
    private int mRankType;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_price_rank;
    }

    @Override
    protected void initVariable() {
        mViewModel = ViewModelProviders.of(this).get(PriceViewModel.class);
        Bundle args = getArguments();
        if (args != null) {
            mPriceType = args.getInt(PriceConstants.KEY_PRICE_TYPE);
            mRankType = args.getInt(PriceConstants.KEY_RANK_TYPE);
        }
    }

    @Override
    protected void initView() {
        initList();
    }

    private void initList() {
        PriceListAdapter adapter = new PriceListAdapter(this);
        adapter.setSupportEmptyView(true);
        adapter.setSupportStatusView(true);
        adapter.setViewModel(mViewModel);
        adapter.setWithOrder(true);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new PagingLayoutManager(getContext()));
        RecyclerViewDivider divider = new RecyclerViewDivider(getContext(),
                DividerItemDecoration.HORIZONTAL);

        mRecyclerView.addItemDecoration(divider);


        mRefreshView.setRefreshing(true);
        mRefreshView.setOnRefreshListener(() -> {
            mRefreshView.setRefreshing(true);
            adapter.refresh();
        });
        mViewModel.getRefreshState().observe(this, networkStatus -> {
            if (networkStatus != null && !networkStatus.isLoading()) {
                mRefreshView.setRefreshing(false);
            }
        });
    }

    public static PriceRankFragment getNewInstance(int priceType, int rankType) {
        Bundle args = new Bundle();
        args.putInt(PriceConstants.KEY_PRICE_TYPE, priceType);
        args.putInt(PriceConstants.KEY_RANK_TYPE, rankType);
        PriceRankFragment fragment = new PriceRankFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
