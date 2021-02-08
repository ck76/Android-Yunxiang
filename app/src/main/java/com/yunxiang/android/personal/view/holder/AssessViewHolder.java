package com.yunxiang.android.personal.view.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunxiang.android.R;
import com.yunxiang.android.base.paging.holder.BaseViewHolder;
import com.yunxiang.android.personal.model.EvaluationModel;
import com.yunxiang.android.personal.model.JadeModel;
import com.yunxiang.android.product.view.activity.DetailAssessActivity;

import butterknife.BindView;


public class AssessViewHolder extends BaseViewHolder<JadeModel> {


    @BindView(R.id.img_cover)
    ImageView mCoverImg;
    @BindView(R.id.txt_goods_title)
    TextView mTitle;
    @BindView(R.id.txt_goods_price)
    TextView mPrice;
    @BindView(R.id.txt_evaluate_result)
    TextView mResult;


    public AssessViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(JadeModel data, int position) {
        mTitle.setText(data.getStoneName());
        mPrice.setText(itemView.getContext().getString(R.string.label_price, String.valueOf(data.getStonePrice())));
        if (position % 2 == 0) {
            mPrice.setVisibility(View.VISIBLE);
            mResult.setText(itemView.getContext().getString(R.string.personal_evaluate_success));
            mResult.setTextColor(itemView.getContext().getResources().getColor(R.color.personal_evaluate_success));

        } else {
            mPrice.setVisibility(View.GONE);
            mResult.setText(itemView.getContext().getString(R.string.personal_evaluating));
            mResult.setTextColor(itemView.getContext().getResources().getColor(R.color.personal_evaluating));
        }
        Glide.with(itemView).load(data.getStonePics().getImgUrls().get(0)).into(mCoverImg);

        EvaluationModel assessModel = new EvaluationModel();
        assessModel.setId(data.getCheckId());
        assessModel.setCheckResult(data.getCheckResult());
        assessModel.setCheckContent(data.getCheckContent());

        assessModel.setStoneDescription(data.getStoneDescription());
        assessModel.setStoneName(data.getStoneName());
        assessModel.setStonePics(data.getStonePics());
        assessModel.setStonePrice(data.getStonePrice());
        assessModel.setStoneType(data.getStoneType());

        itemView.setOnClickListener(v -> DetailAssessActivity.start(itemView.getContext(), assessModel));
    }
}
