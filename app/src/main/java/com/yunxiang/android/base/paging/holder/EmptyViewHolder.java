package com.yunxiang.android.base.paging.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunxiang.android.R;

public class EmptyViewHolder extends RecyclerView.ViewHolder {

    private TextView mNoMoreDataTxt;

    public EmptyViewHolder(@NonNull View itemView) {
        super(itemView);
        mNoMoreDataTxt = itemView.findViewById(R.id.txt_no_more);
    }

    public void bind(boolean atEnd, boolean isEmpty) {
        ViewGroup.LayoutParams params = itemView.getLayoutParams();
        if (isEmpty && !atEnd) {
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mNoMoreDataTxt.setText("暂无数据");
        } else if (!isEmpty && atEnd) {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            mNoMoreDataTxt.setText("暂无更多数据");
        }
        itemView.setLayoutParams(params);
    }
}
