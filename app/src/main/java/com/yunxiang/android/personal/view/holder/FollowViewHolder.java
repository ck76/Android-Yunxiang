package com.yunxiang.android.personal.view.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.yunxiang.android.R;
import com.yunxiang.android.personal.model.JadeModel;
import com.yunxiang.android.product.view.activity.DetailProductActivity;

import butterknife.BindView;


public class FollowViewHolder extends OrderViewHolder {

    @BindView(R.id.txt_range)
    TextView mRangeTxt;
    @BindView(R.id.txt_speed)
    TextView mSpeedTxt;
    @BindView(R.id.txt_range_label)
    TextView mRangeLabelTxt;
    @BindView(R.id.txt_speed_label)
    TextView mSpeedLabelTxt;


    public FollowViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    @Override
    public void bind(JadeModel data, int position) {
        super.bind(data, position);

        mRangeTxt.setText(warpPercent(Math.abs(0.66)));
        mSpeedTxt.setText(warpPercent(Math.abs(0.77)));
        mRangeLabelTxt.setText(position % 2 == 0
                ? R.string.price_label_range_rise
                : R.string.price_label_range_drop);
        mSpeedLabelTxt.setText(position % 2 == 1
                ? R.string.price_label_speed_rise
                : R.string.price_label_speed_drop);
        itemView.setOnClickListener(v -> DetailProductActivity.start(itemView.getContext(), data));
    }

    private String warpPercent(double data) {
        return String.valueOf((float) (data * 100)) + "%";
    }
}
