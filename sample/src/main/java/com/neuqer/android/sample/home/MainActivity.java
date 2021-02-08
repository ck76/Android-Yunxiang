
package com.neuqer.android.sample.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.neuqer.android.runtime.base.activity.AbsActivity;

import com.neuqer.android.sample.R;


public class MainActivity extends AbsActivity {

    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected String getActivityTitle() {
        return "示例工程";
    }

    @Override
    protected void initVariable() {
        mListAdapter = new ListAdapter(this);
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(mListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void loadData() {
        mListAdapter.setItemModelList(ListDataManager.getmItemList());
    }

    @Override
    protected boolean backButtonEnable() {
        return true;
    }
}
