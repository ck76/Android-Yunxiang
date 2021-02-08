package com.yunxiang.android.personal.view.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunxiang.android.R;
import com.yunxiang.android.base.paging.holder.BaseViewHolder;
import com.yunxiang.android.personal.model.JadeModel;
import com.yunxiang.android.search.data.ResultSortManager;

import butterknife.BindView;

public class OrderViewHolder extends BaseViewHolder<JadeModel> {


    @BindView(R.id.img_cover)
    ImageView mCoverImg;
    @BindView(R.id.txt_goods_title)
    TextView mTitle;
    @BindView(R.id.txt_goods_price)
    TextView mPrice;
    @BindView(R.id.txt_auction)
    TextView mAuction;


    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(JadeModel data, int position) {
        mTitle.setText(data.getStoneName());
        mPrice.setText(itemView.getContext().getString(R.string.label_price, String.valueOf(data.getStonePrice())));
        mAuction.setVisibility(data.getBuyType() == ResultSortManager.TRADE_TYPE_AUCTION ? View.VISIBLE : View.GONE);
        Glide.with(itemView).load(data.getStonePics().getImgUrls().get(0)).into(mCoverImg);
    }
}
