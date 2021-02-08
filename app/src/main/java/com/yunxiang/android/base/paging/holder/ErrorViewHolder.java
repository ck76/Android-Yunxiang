package com.yunxiang.android.base.paging.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yunxiang.android.base.paging.data.NetworkStatus;
import com.yunxiang.android.base.paging.PagingViewModel;


public class ErrorViewHolder extends RecyclerView.ViewHolder {

    private PagingViewModel mViewModel;
    private NetworkStatus mRefreshStatus;
    private NetworkStatus mLoadingStatus;

    public ErrorViewHolder(@NonNull View itemView, PagingViewModel viewModel) {
        super(itemView);
        mViewModel = viewModel;
        mRefreshStatus = (NetworkStatus) mViewModel.getRefreshState().getValue();
        mLoadingStatus = (NetworkStatus) mViewModel.getLoadingState().getValue();
    }

    public void bind(boolean support) {
        ViewGroup.LayoutParams params = itemView.getLayoutParams();
        if (mRefreshStatus != null && mRefreshStatus.isFailed()) {
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else if (support && mLoadingStatus != null && mLoadingStatus.isFailed()) {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        itemView.setLayoutParams(params);
        itemView.setOnClickListener(v -> mViewModel.retry());
    }
}
