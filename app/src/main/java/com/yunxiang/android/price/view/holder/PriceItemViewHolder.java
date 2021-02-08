package com.yunxiang.android.price.view.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunxiang.android.R;
import com.yunxiang.android.base.paging.holder.BaseViewHolder;
import com.yunxiang.android.personal.data.Jade;

import butterknife.BindView;


public class PriceItemViewHolder extends BaseViewHolder<Jade> {

    @BindView(R.id.img_cover)
    ImageView mCoverImg;
    @BindView(R.id.txt_range)
    TextView mRangeTxt;
    @BindView(R.id.txt_speed)
    TextView mSpeedTxt;
    @BindView(R.id.txt_goods_title)
    TextView mGoodsTitleTxt;
    @BindView(R.id.txt_range_label)
    TextView mRangeLabelTxt;
    @BindView(R.id.txt_speed_label)
    TextView mSpeedLabelTxt;
    @BindView(R.id.txt_rank_order)
    TextView mRankOrder;

    private boolean withOrder;

    public PriceItemViewHolder(@NonNull View itemView, boolean withOrder) {
        super(itemView);
        this.withOrder = withOrder;
    }


    @Override
    public void bind(Jade data, int position) {

        mGoodsTitleTxt.setText(data.getTitle());
        mRangeTxt.setText(warpPercent(Math.abs(data.getRange())));
        mSpeedTxt.setText(warpPercent(Math.abs(data.getSpeed())));
        mRangeLabelTxt.setText(data.getRange() >= 0
                ? R.string.price_label_range_rise
                : R.string.price_label_range_drop);
        mSpeedLabelTxt.setText(data.getSpeed() >= 0
                ? R.string.price_label_speed_rise
                : R.string.price_label_speed_drop);
        Glide.with(itemView).load(data.getCover()).into(mCoverImg);

        if (withOrder) {
            mRankOrder.setText(String.valueOf(position + 1));
            mRankOrder.setVisibility(View.VISIBLE);
        } else {
            mRankOrder.setVisibility(View.GONE);
        }
    }


    private String warpPercent(double data) {
        return String.valueOf((float) (data * 100)) + "%";
    }
}
