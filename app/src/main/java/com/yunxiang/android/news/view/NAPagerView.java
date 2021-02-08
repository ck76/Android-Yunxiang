
package com.yunxiang.android.news.view;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.neuqer.android.ui.RecyclerViewDivider;
import com.neuqer.android.util.TypeCallback;
import com.yunxiang.android.news.data.NewsDataManager;
import com.yunxiang.android.news.data.OnFetchListener;
import com.yunxiang.android.news.database.NewsDBController;

import com.yunxiang.android.R;
import com.yunxiang.android.news.model.NewsFlowModel;
import com.yunxiang.android.news.model.NewsModel;


public class NAPagerView implements IPagerView {

    /** TAG */
    private static final String TAG = "NAPagerView";
    /** 每页个数 */
    private static final int PER_PAGE_SIZE = 20;
    /** 距离底部多少时，取下一页数据 */
    private static final int PRE_FETCH_DISTANCE = 5;
    private Context mContext;
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NewsAdapter mNewsAdapter;
    private RecyclerViewDivider mDivider;
    private int mTabId;
    private LiveData<PagedList<NewsModel>> mNewsLiveData;
    private int mPage = 0;

    public NAPagerView(int tabId) {
        mTabId = tabId;
    }

    @Override
    public void init(Activity activity) {
        mContext = activity;
    }

    @Override
    public View onCreateView(Activity context, Bundle info) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.fragment_news_tab_pager, null, false);
        mRecyclerView = rootView.findViewById(R.id.news_list);
        mRefreshLayout = rootView.findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mNewsAdapter = new NewsAdapter(mContext);
        mRecyclerView.setAdapter(mNewsAdapter);
        mDivider = new RecyclerViewDivider(mContext, DividerItemDecoration.HORIZONTAL, R.drawable.bg_news_item_divider);
        mRecyclerView.addItemDecoration(mDivider);
        initDataSource();
        loadMoreNews();
        return rootView;
    }

    /**
     * 加载数据
     */
    private void initDataSource() {
        PagedList.Config pageConfig = new PagedList.Config.Builder()
                .setPageSize(PER_PAGE_SIZE)
                .setPrefetchDistance(PRE_FETCH_DISTANCE)
                .setInitialLoadSizeHint(PER_PAGE_SIZE)
                .build();
        NewsDBController.getInstance().getNewsDataSource(mTabId,
                new TypeCallback<DataSource.Factory<Integer, NewsModel>>() {
                    @Override
                    public void onCallback(DataSource.Factory<Integer, NewsModel> source) {
                        mNewsLiveData = new LivePagedListBuilder<>(source, pageConfig).build();
                        setDataObserver();
                    }
                });
    }

    /**
     * 添加数据观察者
     */
    private void setDataObserver() {
        if (!(mContext instanceof LifecycleOwner)) {
            return;
        }
        mNewsLiveData.observe((LifecycleOwner) mContext, new Observer<PagedList<NewsModel>>() {
            @Override
            public void onChanged(@Nullable PagedList<NewsModel> newsModels) {
                Log.i(TAG, "收到数据:" + newsModels.size());
                mNewsAdapter.submitList(newsModels);
            }
        });
    }

    /**
     * 获取资讯数据，接口为临时方法，后续需要优化接口，不适用page
     */
    private void loadMoreNews() {
        NewsDataManager.get().fetchDataAsync(mPage, new OnFetchListener() {
            @Override
            public void onFetchNews(NewsFlowModel newsFlowModel, int status) {
                NewsDBController.getInstance().saveNews(mTabId, newsFlowModel.getNewsModelList());
                mPage++;
                mRefreshLayout.setRefreshing(false);
                mRecyclerView.scrollToPosition(0);
            }
        });
    }

    /**
     * 下拉刷新回调
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadMoreNews();
        }
    };
}
