package com.yunxiang.android.search.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunxiang.android.R;
import com.yunxiang.android.search.SearchViewModel;

import java.util.List;


public class PopularSearchAdapter extends RecyclerView.Adapter<PopularSearchAdapter.ViewHolder> {


    private Context mContext;
    private List<String> mDataList;
    private OnPopularClickListener mClickListener;

    public PopularSearchAdapter(Context context, SearchViewModel viewModel) {
        mContext = context;
        mDataList = viewModel.getPopularList().getValue();
        if (mDataList != null && mDataList.size() > SearchViewModel.MAX_POPULAR_SHOW) {
            mDataList = mDataList.subList(0, SearchViewModel.MAX_POPULAR_SHOW);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_item_popular, viewGroup, false);
        return new ViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (mDataList != null && !mDataList.isEmpty()) {
            viewHolder.bind(mDataList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private OnPopularClickListener mListener;

        public ViewHolder(@NonNull View itemView, OnPopularClickListener listener) {
            super(itemView);
            mListener = listener;
        }

        public void bind(String search) {
            TextView textView = itemView.findViewById(R.id.txt_search_popular);
            textView.setText(search);
            textView.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onClick(search);
                }
            });
        }
    }

    public void setClickListener(OnPopularClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface OnPopularClickListener {
        void onClick(String search);
    }
}
