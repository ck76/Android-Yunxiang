package com.yunxiang.android.base.paging;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.paging.DataSource;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunxiang.android.R;
import com.yunxiang.android.base.paging.data.NetworkStatus;
import com.yunxiang.android.base.paging.holder.BaseViewHolder;
import com.yunxiang.android.base.paging.holder.EmptyViewHolder;
import com.yunxiang.android.base.paging.holder.ErrorViewHolder;
import com.yunxiang.android.base.paging.holder.LoadingViewHolder;

import java.util.List;


public abstract class PagingListAdapter<T> extends PagedListAdapter<T, RecyclerView.ViewHolder> {

    private static final int TYPE_LOADING = 1001; // 正在加载更多
    private static final int TYPE_ERROR = 1002; // 加载更多失败
    private static final int TYPE_EMPTY = 1003; // 空列表

    private PagingViewModel<T> mViewModel;
    private LifecycleOwner mLifecycleOwner;

    private NetworkStatus.Status mLoadingStatus = null;
    private NetworkStatus.Status mRefreshStatus = null;

    private boolean isEmpty = false;
    private boolean hasMore = false;
    private boolean isLoadingShowing = false;
    private boolean supportEmptyView = false;
    private boolean supportStatusView = false;

    // 监听数据列表变化
    private final Observer<PagedList<T>> mDataListObserver = this::submitList;

    // 监听LoadMore状态
    private final Observer<NetworkStatus> mLoadingObserver = networkStatus -> {
        if (networkStatus == null) {
            return;
        }
        setLoadingStatus(networkStatus.getStatus());
    };

    // 监听Refresh状态
    private final Observer<NetworkStatus> mRefreshObserver = networkStatus -> {
        if (networkStatus == null) {
            return;
        }
        setRefreshStatus(networkStatus.getStatus());
    };

    // 监听是否为空列表
    private final Observer<Boolean> mEmptyObserver = isEmpty -> {
        if (isEmpty == null) {
            return;
        }
        setEmpty(isEmpty);
    };

    // 监听是否有更多
    private final Observer<Boolean> mHasMoreObserver = hasMore -> {
        if (hasMore == null) {
            return;
        }
        setHasMore(hasMore);
    };


    protected PagingListAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback,
                                LifecycleOwner owner) {
        super(diffCallback);
        mLifecycleOwner = owner;
    }

    public void setViewModel(PagingViewModel<T> viewModel) {
        if (mViewModel != null) {
            mViewModel.getLoadingState().removeObserver(mLoadingObserver);
            mViewModel.getRefreshState().removeObserver(mRefreshObserver);
            mViewModel.getDataList().removeObserver(mDataListObserver);
            mViewModel.hasMore().removeObserver(mHasMoreObserver);
            mViewModel.isEmpty().removeObserver(mEmptyObserver);
        }
        mViewModel = viewModel;
        mViewModel.getLoadingState().observe(mLifecycleOwner, mLoadingObserver);
        mViewModel.getRefreshState().observe(mLifecycleOwner, mRefreshObserver);
        mViewModel.getDataList().observe(mLifecycleOwner, mDataListObserver);
        mViewModel.hasMore().observe(mLifecycleOwner, mHasMoreObserver);
        mViewModel.isEmpty().observe(mLifecycleOwner, mEmptyObserver);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        switch (type) {
            case TYPE_EMPTY:
                return createEmptyViewHolder(viewGroup);
            case TYPE_LOADING:
                return createLoadingViewHolder(viewGroup);
            case TYPE_ERROR:
                return createErrorViewHolder(viewGroup);
            default:
                return createNormalViewHolder(viewGroup, type);
        }
    }

    protected abstract RecyclerView.ViewHolder createNormalViewHolder(ViewGroup viewGroup, int type);

    private RecyclerView.ViewHolder createEmptyViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_paging_empty, viewGroup, false);
        return new EmptyViewHolder(view);
    }

    private RecyclerView.ViewHolder createErrorViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_paging_error, viewGroup, false);
        return new ErrorViewHolder(view, mViewModel);
    }

    private RecyclerView.ViewHolder createLoadingViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_paging_loading, viewGroup, false);
        return new LoadingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int type = getItemViewType(position);
        if (type == TYPE_EMPTY && viewHolder instanceof EmptyViewHolder) {
            ((EmptyViewHolder) viewHolder).bind((showLoadingStatusView() && isAtEnd()), isEmpty());
        } else if (type == TYPE_LOADING && viewHolder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) viewHolder).bind();
        } else if (type == TYPE_ERROR && viewHolder instanceof ErrorViewHolder) {
            ((ErrorViewHolder) viewHolder).bind(showLoadingStatusView());
        } else if (viewHolder instanceof BaseViewHolder) {
            T data = getItem(position);
            ((BaseViewHolder<T>) viewHolder).bind(data, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int itemCount = getItemCount() - 1;
        if (position == 0 && showEmptyView()) {
            return TYPE_EMPTY;
        } else if (position == 0 && isRefreshError()) {
            return TYPE_ERROR;
        } else if (showLoadingStatusView() && position == itemCount && itemCount != 0) {
            return getLoadingStatus();
        } else {
            return getDataItemViewType(position);
        }
    }

    private int getLoadingStatus() {
        if (isAtEnd()) {
            return TYPE_EMPTY;
        } else if (isLoadingError()) {
            return TYPE_ERROR;
        } else if (isLoading()) {
            return TYPE_LOADING;
        }
        throw new RuntimeException("unknown loading status type");
    }


    protected int getDataItemViewType(int position) {
        return 0;
    }


    @Override
    public int getItemCount() {
        int empty = showEmptyView() ? 1 : 0;
        int dataItemCount = getDataItemCount();
        int status = showLoadingStatusView() ? 1 : 0;
        return dataItemCount + status + empty;
    }

    public int getDataItemCount() {
        return super.getItemCount();
    }



    /**
     * 是否支持展示全屏的EmptyView
     *
     * @return
     */
    public boolean isSupportEmptyView() {
        return supportEmptyView;
    }

    /**
     * 设置是否支持展示全屏的EmptyView
     *
     * @param support
     */
    public void setSupportEmptyView(boolean support) {
        this.supportEmptyView = support;
        notifyDataSetChanged();
    }

    /**
     * 当前数据列表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return isEmpty && getDataItemCount() == 0;
    }

    /**
     * 动态设置是否为空列表状态
     *
     * @param empty
     */
    public void setEmpty(boolean empty) {
        if (isEmpty == empty) {
            return;
        }
        this.isEmpty = empty;
        if (isEmpty()) {
            notifyItemInserted(0);
        } else {
            notifyItemRemoved(0);
        }
    }

    /**
     * 是否展示EmptyView
     *
     * @return
     */
    private boolean showEmptyView() {
        return isSupportEmptyView() && isEmpty();
    }


    public boolean isSupportStatusView() {
        return supportStatusView;
    }

    public void setSupportStatusView(boolean support) {
        this.supportStatusView = support;
        notifyDataSetChanged();
    }

    private void setLoadingStatus(NetworkStatus.Status status) {
        // 获取旧的显示状态
        boolean oldStatus = isLoadingShowing;
        // 更新加载状态
        this.mLoadingStatus = status;
        // 获取新的网络状态
        boolean newStatus = showLoadingStatusView();
        if (!oldStatus && newStatus) {
            // 展示Status
            notifyItemInserted(getDataItemCount());
        } else if (oldStatus && !newStatus) {
            // 消除Status
            notifyItemRemoved(getDataItemCount());
        } else {
            notifyDataSetChanged();
        }
        isLoadingShowing = newStatus;
    }

    // 是否展示加载状态
    public boolean showLoadingStatusView() {
        if (!isSupportStatusView()) {
            return false;
        }
        return isLoading() || isLoadingError() || isAtEnd();
    }

    public boolean isLoadingError() {
        return mLoadingStatus == NetworkStatus.Status.FAILED;
    }

    public boolean isLoading() {
        return mLoadingStatus == NetworkStatus.Status.RUNNING;
    }

    public boolean isAtEnd() {
        // 当前列表不为空且暂无更多
        return !isEmpty() && !hasMore();
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }

    public boolean hasMore() {
        return hasMore;
    }


    public void refresh() {
        // 清空数据
        submitList(null);
        if (mViewModel != null) {
            mViewModel.refresh();
            setViewModel(mViewModel);
        }
    }

    public void setRefreshStatus(NetworkStatus.Status status) {
        this.mRefreshStatus = status;
        notifyDataSetChanged();
    }

    public boolean isRefreshError() {
        return mRefreshStatus == NetworkStatus.Status.FAILED;
    }

    public boolean isRefreshing() {
        return mRefreshStatus == NetworkStatus.Status.RUNNING;
    }

}
