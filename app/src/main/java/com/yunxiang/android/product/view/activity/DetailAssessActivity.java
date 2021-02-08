package com.yunxiang.android.product.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.neuqer.android.util.ToastUtil;
import com.yunxiang.android.R;
import com.yunxiang.android.personal.model.EvaluationModel;
import com.yunxiang.android.product.view.activity.base.DetailBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;


public class DetailAssessActivity extends DetailBaseActivity {

    @Nullable
    @BindView(R.id.txt_assess_price)
    TextView mPriceTxt;
    @Nullable
    @BindView(R.id.txt_assess_result)
    TextView mResultTxt;
    @Nullable
    @BindView(R.id.txt_stone_type)
    TextView mStoneTypeTxt;
//    @Nullable
//    @BindView(R.id.txt_stone_level)
//    TextView mStoneLevelTxt;

    @Override
    protected void initView() {
        super.initView();
        mViewModel.getEvaluationDetail().observe(this, assessModel -> {
            if (assessModel == null) {
                return;
            }
            mPriceTxt.setText(getString(R.string.label_price, String.valueOf(assessModel.getStonePrice())));
            mResultTxt.setText(assessModel.getCheckResult());
            mStoneTypeTxt.setText(assessModel.getStoneType());
        });
        mViewModel.queryAssessDetail();
    }

    @Override
    protected int getInfoLayoutRes() {
        return R.layout.detail_item_assess_info;
    }

    @Override
    protected int getBottomLayout() {
        return R.layout.detail_item_assess_bottom;
    }

    public static void start(Context context, EvaluationModel assessModel) {
        Intent intent = new Intent(context, DetailAssessActivity.class);
        intent.putExtra(KEY_ASSESS, assessModel);
        context.startActivity(intent);
    }

    @Optional
    @OnClick(R.id.txt_publish_assess)
    public void onViewClicked() {
        ToastUtil.show(this, "发布");
    }
}
