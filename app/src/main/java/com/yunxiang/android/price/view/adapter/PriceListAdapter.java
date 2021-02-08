package com.yunxiang.android.price.view.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunxiang.android.R;
import com.yunxiang.android.base.paging.PagingListAdapter;
import com.yunxiang.android.personal.data.Jade;
import com.yunxiang.android.price.model.IPriceItemModel;
import com.yunxiang.android.price.model.PriceItemChart;
import com.yunxiang.android.price.model.PriceItemGoods;
import com.yunxiang.android.price.view.holder.PriceChartViewHolder;
import com.yunxiang.android.price.view.holder.PriceItemViewHolder;

import static com.yunxiang.android.price.model.IPriceItemModel.TYPE_CHART;


public class PriceListAdapter extends PagingListAdapter<Jade> {

    public static final int TYPE_CHART = 0;
    public static final int TYPE_PRICE_HEAD = 1;
    public static final int TYPE_PRICE = 2;


    private boolean withOrder;

    public PriceListAdapter(LifecycleOwner owner) {
        super(DIFF_CALLBACK, owner);
    }

    public void setWithOrder(boolean withOrder) {
        this.withOrder = withOrder;
    }

    @Override
    protected RecyclerView.ViewHolder createNormalViewHolder(ViewGroup viewGroup, int type) {
        return type == TYPE_CHART
                ? createChartViewHolder(viewGroup)
                : createPriceViewHolder(viewGroup, type == TYPE_PRICE_HEAD);
    }

    private RecyclerView.ViewHolder createPriceViewHolder(ViewGroup viewGroup, boolean isHead) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(isHead
                ? R.layout.price_item_goods_head
                : R.layout.price_item_goods, viewGroup, false);
        return new PriceItemViewHolder(view, withOrder);
    }

    private RecyclerView.ViewHolder createChartViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.price_item_chart, viewGroup, false);
        return new PriceChartViewHolder(view);
    }


    @Override
    public int getItemViewType(int position) {

        if (withOrder) {
            return TYPE_PRICE;
        }

        switch (position) {
            case 0:
            case 1:
                return TYPE_CHART;
            case 2:
                return TYPE_PRICE_HEAD;
            default:
                return TYPE_PRICE;
        }
    }

    private static final DiffUtil.ItemCallback<Jade> DIFF_CALLBACK = new DiffUtil.ItemCallback<Jade>() {
        @Override
        public boolean areItemsTheSame(@NonNull Jade jade, @NonNull Jade t1) {
            return t1.getId() == jade.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Jade jade, @NonNull Jade t1) {
            return t1.equals(jade);
        }
    };

}
