
package com.yunxiang.android.news.view;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.neuqer.android.scheme.Scheme;
import com.yunxiang.android.news.model.NewsModel;
import com.yunxiang.android.news.view.item.NewsItem;


public class NewsAdapter extends PagedListAdapter<NewsModel, NewsAdapter.NewsViewHolder> {

    private Context mContext;

    public NewsAdapter(Context context) {
        super(sDiffCallback);
        mContext = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsType layout = NewsType.values()[viewType];
        NewsItem item = NewsItemFactory.createNewsItem(mContext, layout);
        return new NewsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder viewHolder, int position) {
        NewsItem newsItem = viewHolder.getNesItem();
        NewsModel newsModel = getItem(position);
        newsItem.updateView(newsModel);
        // TODO: 2018/11/23 临时测试 ，后期需要更换位置
        ((View) newsItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scheme.dispatch(mContext, newsModel.data.cmd);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        NewsModel model = getItem(position);
        return model == null ? NewsType.NO_MATCH.ordinal() : NewsType.getLayout(model.layout).ordinal();
    }

    /**
     * 去重
     */
    static DiffUtil.ItemCallback<NewsModel> sDiffCallback = new DiffUtil.ItemCallback<NewsModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull NewsModel newsModel, @NonNull NewsModel newsModel1) {
            return TextUtils.equals(newsModel.id, newsModel1.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull NewsModel newsModel, @NonNull NewsModel newsModel1) {
            return TextUtils.equals(newsModel.data.title, newsModel1.data.title);
        }
    };

    /**
     * 资讯Item ViewHolder
     */
    static class NewsViewHolder extends RecyclerView.ViewHolder {

        NewsItem mNewsItem;

        public NewsViewHolder(@NonNull NewsItem item) {
            super((View) item);
            mNewsItem = item;
        }

        public NewsItem getNesItem() {
            return mNewsItem;
        }
    }
}
