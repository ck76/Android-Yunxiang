package com.yunxiang.android.price.view.holder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunxiang.android.R;
import com.yunxiang.android.base.paging.holder.BaseViewHolder;
import com.yunxiang.android.personal.data.Jade;
import com.yunxiang.android.price.PriceConstants;
import com.yunxiang.android.price.view.PriceRankActivity;
import com.yunxiang.android.price.view.adapter.PricePagerAdapter;

import butterknife.BindView;


public class PriceChartViewHolder extends BaseViewHolder<Jade> {

    @BindView(R.id.chart_title)
    TextView mChartTitleTxt;
    @BindView(R.id.chart_subtitle)
    TextView mChartInfoTxt;
    @BindView(R.id.chart_container)
    LinearLayout mChartContainer;

    public PriceChartViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Jade data, int position) {
        mChartInfoTxt.setOnClickListener(v -> goToRankList(position));
        if (position == 0) {
            mChartTitleTxt.setText(R.string.price_chart_title_rise);
            mChartTitleTxt.setTextColor(itemView.getContext().getResources().getColor(R.color.price_chart_rise));
        } else {
            mChartTitleTxt.setText(R.string.price_chart_title_drop);
            mChartTitleTxt.setTextColor(itemView.getContext().getResources().getColor(R.color.price_chart_drop));
        }
    }

    private void goToRankList(int pos) {
        Context context = itemView.getContext();
        Intent intent = new Intent(context, PriceRankActivity.class);
        intent.putExtra(PriceConstants.KEY_PRICE_TYPE, pos == 0
                ? PricePagerAdapter.TYPE_RISE
                : PricePagerAdapter.TYPE_DROP);
        context.startActivity(intent);
    }
}
