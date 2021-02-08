package com.yunxiang.android.base.paging.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected Unbinder mUnBinder;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        mUnBinder = ButterKnife.bind(this, itemView);
    }

    public void bind(T data, int position) {

    }

    public void unBind(){
        mUnBinder.unbind();
    }
}
