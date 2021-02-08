package com.neuqer.android.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;


public abstract class SimpleAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;
    protected List<T> mData;

    public SimpleAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<T> data) {
        this.mData = data;
    }

    protected T getItemModel(int position) {
        if (mData == null || position < 0 || position >= mData.size()) {
            return null;
        }
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }
}
