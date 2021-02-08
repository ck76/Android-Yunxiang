package com.yunxiang.android.search.view.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yunxiang.android.R;
import com.yunxiang.android.base.paging.PagingListAdapter;
import com.yunxiang.android.base.paging.holder.BaseViewHolder;
import com.yunxiang.android.personal.data.Jade;
import com.yunxiang.android.search.data.ResultSortManager;

import butterknife.BindView;


public class ResultListAdapter extends PagingListAdapter<Jade> {


    public ResultListAdapter(LifecycleOwner owner) {
        super(DIFF_CALLBACK, owner);
    }

    @Override
    protected RecyclerView.ViewHolder createNormalViewHolder(ViewGroup viewGroup, int type) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_item_result, viewGroup, false);
        return new ViewHolder(view);
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


    public static class ViewHolder extends BaseViewHolder<Jade> {

        @BindView(R.id.img_cover)
        ImageView mCoverImg;
        @BindView(R.id.txt_goods_title)
        TextView mTitleTxt;
        @BindView(R.id.txt_range)
        TextView mRangeTxt;
        @BindView(R.id.txt_speed)
        TextView mSpeedTxt;
        @BindView(R.id.txt_goods_price)
        TextView mPriceTxt;
        @BindView(R.id.txt_auction)
        TextView mAuctionTxt;
        @BindView(R.id.txt_range_label)
        TextView mRangeLabelTxt;
        @BindView(R.id.txt_speed_label)
        TextView mSpeedLabelTxt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bind(Jade data, int position) {
            mTitleTxt.setText(data.getTitle());
            mPriceTxt.setText(itemView.getContext().getString(R.string.label_price, String.valueOf(data.getPrice())));
            mAuctionTxt.setVisibility(data.getTradeType() == ResultSortManager.TRADE_TYPE_AUCTION ? View.VISIBLE : View.GONE);
            mRangeTxt.setText(warpPercent(Math.abs(data.getRange())));
            mSpeedTxt.setText(warpPercent(Math.abs(data.getSpeed())));
            mRangeLabelTxt.setText(data.getRange() >= 0
                    ? R.string.price_label_range_rise
                    : R.string.price_label_range_drop);
            mSpeedLabelTxt.setText(data.getSpeed() >= 0
                    ? R.string.price_label_speed_rise
                    : R.string.price_label_speed_drop);
            RoundedCorners roundedCorners = new RoundedCorners(24);
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
            Glide.with(itemView).load(data.getCover()).apply(options).into(mCoverImg);
        }

        private String warpPercent(double data) {
            return String.valueOf((float) (data * 100)) + "%";
        }
    }


}
