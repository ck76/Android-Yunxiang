package com.yunxiang.android.product.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neuqer.android.util.ToastUtil;
import com.yunxiang.android.R;
import com.yunxiang.android.personal.model.JadeModel;
import com.yunxiang.android.product.view.activity.base.DetailBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;

public class DetailProductActivity extends DetailBaseActivity {


    public static final int DEAL_TYPE_BUYOUT_ONLY = 1;
    public static final int DEAL_TYPE_WITH_AUCTION = 2;

    @Nullable
    @BindView(R.id.txt_buyout_price)
    TextView mBuyoutPriceTxt;
    @Nullable
    @BindView(R.id.txt_auction_price)
    TextView mAuctionPriceTxt;
    @Nullable
    @BindView(R.id.txt_auction_time)
    TextView mAuctionTimeTxt;
    @Nullable
    @BindView(R.id.txt_follow_number)
    TextView mFollowNumberTxt;
    @Nullable
    @BindView(R.id.img_follow)
    ImageView mFollowImg;
    @Nullable
    @BindView(R.id.view_divider)
    View mDividerView;
    @Nullable
    @BindView(R.id.txt_auction)
    TextView mAuctionTxt;
    @Nullable
    @BindView(R.id.ll_auction_container)
    LinearLayout mAuctionContainer;

    @Override
    protected void initView() {
        super.initView();
        mViewModel.getJadeDetail().observe(this, jadeModel -> {
            if (jadeModel == null) {
                return;
            }
            initBuyoutInfo(jadeModel);
            if (jadeModel.getBuyType() == DEAL_TYPE_WITH_AUCTION) {
                initAuctionInfo(jadeModel);
            }
            mFollowNumberTxt.setText(String.valueOf(jadeModel.getFollowNumber()));
        });
        mViewModel.queryGoodsDetail();
    }

    private void initBuyoutInfo(JadeModel jadeModel) {
        mAuctionTxt.setVisibility(View.GONE);
        mDividerView.setVisibility(View.GONE);
        mAuctionContainer.setVisibility(View.GONE);
        mBuyoutPriceTxt.setText(getString(R.string.label_price,
                String.valueOf(jadeModel.getBuyoutPrice())));
    }

    private void initAuctionInfo(JadeModel jadeModel) {
        mAuctionContainer.setVisibility(View.VISIBLE);
        mAuctionTxt.setVisibility(View.VISIBLE);
        mDividerView.setVisibility(View.VISIBLE);
        mAuctionPriceTxt.setText(getString(R.string.label_price,
                String.valueOf(jadeModel.getAuctionPrice())));
    }


    @Override
    protected int getInfoLayoutRes() {
        return R.layout.detail_item_product_info;
    }

    @Override
    protected int getBottomLayout() {
        return R.layout.detail_item_product_bottom;
    }

    @Optional
    @OnClick(R.id.img_follow)
    public void onMFollowImgClicked() {
        boolean isSelect = mFollowImg.isSelected();
        mFollowImg.setSelected(!isSelect);
        ToastUtil.show(this, "点击了收藏");
    }

    @Optional
    @OnClick(R.id.img_share)
    public void onMShareImgClicked() {
        ToastUtil.show(this, "点击了分享");
    }

    @Optional
    @OnClick(R.id.txt_buyout)
    public void onMBuyoutTxtClicked() {
        ToastUtil.show(this, "点击了一口价");
    }

    @Optional
    @OnClick(R.id.view_divider)
    public void onMDividerViewClicked() {
        ToastUtil.show(this, "点击了竞拍");
    }

    @Optional
    @OnClick(R.id.txt_check_proof)
    public void onViewClicked() {
        ToastUtil.show(this, "查看官方云想证书");
    }


    public static void start(Context context, JadeModel jadeModel) {
        Intent intent = new Intent(context, DetailProductActivity.class);
        intent.putExtra(KEY_JADE, jadeModel);
        context.startActivity(intent);
    }
}
