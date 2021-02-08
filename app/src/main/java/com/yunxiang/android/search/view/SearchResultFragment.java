package com.yunxiang.android.search.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.neuqer.android.ui.RecyclerViewDivider;
import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseFragment;
import com.yunxiang.android.base.paging.PagingLayoutManager;
import com.yunxiang.android.personal.data.Jade;
import com.yunxiang.android.search.SearchResultViewModel;
import com.yunxiang.android.search.model.ResultTab;
import com.yunxiang.android.search.view.adapter.ResultListAdapter;

import java.util.List;

import butterknife.BindView;

public class SearchResultFragment extends BaseFragment {


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private SearchResultViewModel mViewModel;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search_result;
    }

    @Override
    protected void initVariable() {
        mViewModel = ViewModelProviders.of(this).get(SearchResultViewModel.class);
    }

    @Override
    protected void initView() {
        ResultListAdapter adapter = new ResultListAdapter(this);
        adapter.setSupportStatusView(true);
        adapter.setSupportEmptyView(true);
        adapter.setViewModel(mViewModel);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new PagingLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(getContext(), DividerItemDecoration.HORIZONTAL));

        mViewModel.getShowDataList().observe(this, new Observer<List<Jade>>() {
            @Override
            public void onChanged(@Nullable List<Jade> list) {

                adapter.notifyDataSetChanged();
            }
        });
    }

    public SearchResultViewModel getViewModel() {
        return mViewModel;
    }

    public static SearchResultFragment getInstance(ResultTab tab) {
        SearchResultFragment fragment = new SearchResultFragment();

        return fragment;
    }
}
