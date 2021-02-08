package com.yunxiang.android.search.view;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neuqer.android.util.ToastUtil;
import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseActivity;
import com.yunxiang.android.search.SearchViewModel;
import com.yunxiang.android.search.view.adapter.PopularSearchAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;


public class SearchActivity extends BaseActivity {


    @BindView(R.id.img_search_back)
    ImageView mBackImg;
    @BindView(R.id.btn_search)
    TextView mSearchTxt;
    @BindView(R.id.img_delete)
    ImageView mDeleteImg;
    @BindView(R.id.fl_search_list)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.edit_search)
    EditText mSearchEdit;
    @BindView(R.id.img_more_history)
    ImageView mMoreHistoryImg;
    @BindView(R.id.img_clear_history)
    ImageView mClearHistoryImg;
    @BindView(R.id.recycler_popular_search)
    RecyclerView mPopularSearchList;
    @BindView(R.id.txt_has_hide)
    TextView mHasHideTxt;
    @BindView(R.id.img_has_hide)
    ImageView mHasHideImg;
    @BindView(R.id.search_history_container)
    LinearLayout mHistoryContainer;

    private SearchViewModel mViewModel;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void initVariable() {
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        mViewModel.setContext(this);
    }

    @Override
    protected void initView() {
        initSearch();
        initHistory();
        initPopular();
    }

    /**
     * 初始化搜索框
     */
    private void initSearch() {
        mDeleteImg.setVisibility(View.GONE);
        mBackImg.setOnClickListener(v -> finish());
        mDeleteImg.setOnClickListener(v -> mSearchEdit.setText(""));
        mSearchTxt.setOnClickListener(v -> goSearchResult(mSearchEdit.getText().toString()));
        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mDeleteImg.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * 初始化搜索历史
     */
    private void initHistory() {
        mHistoryContainer.setVisibility(View.VISIBLE);
        mClearHistoryImg.setOnClickListener(v -> mViewModel.clearHistory());
        mMoreHistoryImg.setOnClickListener(v -> {
            mMoreHistoryImg.setVisibility(View.GONE);
            mPopularSearchList.setVisibility(View.GONE);
            mHasHideTxt.setVisibility(View.VISIBLE);
            mHasHideImg.setImageResource(R.drawable.icon_not_see_white);
            mViewModel.showHistory(true);
        });

        mViewModel.getHasMore().observe(this, hasMore -> {
            if(hasMore == null || !hasMore) {
                mMoreHistoryImg.setVisibility(View.GONE);
                return;
            }
            mMoreHistoryImg.setVisibility(View.VISIBLE);
        });

        mViewModel.getHistoryList().observe(this, list -> {
            if (list == null || list.isEmpty()) {
                mHistoryContainer.setVisibility(View.GONE);
                return;
            }
            mFlowLayout.setAdapter(new TagAdapter<String>(list) {
                @Override
                public View getView(FlowLayout parent, int position, String str) {
                    TextView history = (TextView) LayoutInflater
                            .from(SearchActivity.this)
                            .inflate(R.layout.search_item_history, mFlowLayout, false);
                    history.setText(str);
                    return history;
                }
            });
        });

        mFlowLayout.setOnTagClickListener((view, position, parent) -> {
            List<String> list = mViewModel.getHistoryList().getValue();
            if (list == null || list.isEmpty()) {
                return false;
            }
            goSearchResult(list.get(position));
            return true;
        });
        mViewModel.showHistory(false);
    }

    /**
     * 初始化热门搜索
     */
    private void initPopular() {
        mHasHideTxt.setVisibility(View.GONE);
        mPopularSearchList.setVisibility(View.VISIBLE);
        mHasHideImg.setImageResource(R.drawable.icon_see_white);
        mHasHideTxt.setOnClickListener(v -> {
            mHasHideTxt.setVisibility(View.GONE);
            mMoreHistoryImg.setVisibility(View.VISIBLE);
            mPopularSearchList.setVisibility(View.VISIBLE);
            mHasHideImg.setImageResource(R.drawable.icon_see_white);
            mViewModel.showHistory(false);
        });

        mPopularSearchList.setLayoutManager(new GridLayoutManager(this, 2));
        PopularSearchAdapter adapter = new PopularSearchAdapter(this, mViewModel);
        adapter.setClickListener(this::goSearchResult);
        mPopularSearchList.setAdapter(adapter);
    }

    private void goSearchResult(String s) {
        if ("".equals(s)) {
            ToastUtil.show(SearchActivity.this, "搜索内容不能为空");
            return;
        }
        mViewModel.addHistory(s);
        SearchResultActivity.start(SearchActivity.this, s);
        finish();
    }

    @Override
    protected boolean toolbarEnable() {
        return false;
    }
}
