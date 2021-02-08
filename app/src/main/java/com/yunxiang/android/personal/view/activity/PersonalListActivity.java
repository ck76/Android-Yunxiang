package com.yunxiang.android.personal.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.neuqer.android.ui.RecyclerViewDivider;
import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseActivity;
import com.yunxiang.android.base.paging.PagingLayoutManager;
import com.yunxiang.android.personal.PersonalConstants;
import com.yunxiang.android.personal.data.PersonalRepository;
import com.yunxiang.android.personal.view.adapter.PersonalListAdapter;
import com.yunxiang.android.personal.view.viewmodel.PersonalListViewModel;

import butterknife.BindView;

import static com.yunxiang.android.personal.PersonalConstants.TYPE_EVALUATE;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_FOLLOW;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_ORDER_BUY;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_ORDER_SELL;
import static com.yunxiang.android.personal.PersonalConstants.TYPE_RELEASE;


public class PersonalListActivity extends BaseActivity {


    @BindView(R.id.txt_toolbar_title)
    TextView mTitleTxt;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefreshView;
    @BindView(R.id.base_toolbar)
    Toolbar mBaseToolbar;
    @BindView(R.id.layout_sort)
    View mSortLayout;

    private int mListType;
    private PersonalListViewModel mViewModel;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_personal_list;
    }

    @Override
    protected void initVariable() {
        mListType = getIntent().getIntExtra(PersonalConstants.KEY_PERSONAL_LIST_TYPE,
                TYPE_ORDER_BUY);
        PersonalRepository.getInstance().setListType(mListType);
        mViewModel = ViewModelProviders.of(this).get(PersonalListViewModel.class);
    }

    @Override
    protected void initView() {
        initTitle();
        initList();
        mSortLayout.setVisibility(mListType == TYPE_FOLLOW ? View.VISIBLE : View.GONE);
    }

    private void initTitle() {
        mBaseToolbar.setNavigationOnClickListener(v -> finish());
        int resId;
        switch (mListType) {
            case TYPE_ORDER_BUY:
                resId = R.string.personal_title_order_buy;
                break;
            case TYPE_ORDER_SELL:
                resId = R.string.personal_title_order_sell;
                break;
            case TYPE_RELEASE:
                resId = R.string.personal_title_release;
                break;
            case TYPE_EVALUATE:
                resId = R.string.personal_title_evaluate;
                break;
            case TYPE_FOLLOW:
                resId = R.string.personal_title_follow;
                break;
            default:
                throw new RuntimeException();
        }
        mTitleTxt.setText(resId);
    }


    private void initList() {
        PersonalListAdapter adapter = new PersonalListAdapter(this, mListType);
        adapter.setSupportEmptyView(true);
        adapter.setSupportStatusView(true);
        adapter.setViewModel(mViewModel);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new PagingLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(this, DividerItemDecoration.HORIZONTAL));

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


    public static void startActivity(Context context, int orderType) {
        Intent intent = new Intent(context, PersonalListActivity.class);
        intent.putExtra(PersonalConstants.KEY_PERSONAL_LIST_TYPE, orderType);
        context.startActivity(intent);
    }

    @Override
    protected boolean toolbarEnable() {
        return false;
    }
}
