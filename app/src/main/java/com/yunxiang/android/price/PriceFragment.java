
package com.yunxiang.android.price;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseFragment;
import com.yunxiang.android.base.paging.PagingLayoutManager;
import com.yunxiang.android.price.view.adapter.PriceListAdapter;
import com.yunxiang.android.search.view.SearchActivity;

import butterknife.BindView;


public class PriceFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefreshView;
    @BindView(R.id.txt_search)
    TextView mSearchTxt;

    private PriceViewModel mViewModel;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_price;
    }

    @Override
    protected void initVariable() {
        mViewModel = ViewModelProviders.of(this).get(PriceViewModel.class);
    }

    @Override
    protected void initView() {
        mSearchTxt.setOnClickListener(v -> startActivity(new Intent(getContext(), SearchActivity.class)));
        initList();
    }


    private void initList() {
        PriceListAdapter adapter = new PriceListAdapter(this);
        adapter.setSupportEmptyView(true);
        adapter.setSupportStatusView(true);
        adapter.setViewModel(mViewModel);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new PagingLayoutManager(getContext()));

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
}
