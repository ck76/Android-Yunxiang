package com.yunxiang.android.product.view.activity.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neuqer.android.ui.banner.Banner;
import com.neuqer.android.ui.banner.BannerConfig;
import com.neuqer.android.ui.banner.loader.ImageLoader;
import com.yunxiang.android.R;
import com.yunxiang.android.base.BaseActivity;
import com.yunxiang.android.personal.model.EvaluationModel;
import com.yunxiang.android.personal.model.JadeModel;
import com.yunxiang.android.product.DetailViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class DetailBaseActivity extends BaseActivity {


    public static final int BANNER_DELAY_TIME = 3000;

    public static final String KEY_JADE = "key_jade";
    public static final String KEY_ASSESS = "key_assess";


    @BindView(R.id.detail_banner)
    Banner mBanner;
    @BindView(R.id.img_detail_back)
    ImageView mBackImg;
    @BindView(R.id.detail_title)
    TextView mTitleTxt;
    @BindView(R.id.detail_info_container)
    LinearLayout mInfoLayout;
    @BindView(R.id.txt_desc)
    TextView mDescTxt;
    @BindView(R.id.detail_bottom_container)
    LinearLayout mBottomLayout;

    protected DetailViewModel mViewModel;
    private List<String> mUrlList;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_base_detail;
    }

    @Override
    protected void initVariable() {
        initViewModel();
        mUrlList = new ArrayList<>();
    }

    @Override
    protected void initView() {
        mBackImg.setOnClickListener(v -> finish());
        initInfoLayout();
        initBottomLayout();
        ButterKnife.bind(this);
    }

    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        Intent intent = getIntent();
        if (intent != null) {
            JadeModel jadeModel = (JadeModel) intent.getSerializableExtra(KEY_JADE);
            EvaluationModel assessModel = (EvaluationModel) intent.getSerializableExtra(KEY_ASSESS);
            if (jadeModel != null) {
                mViewModel.setJadeDetail(jadeModel);
                mViewModel.getJadeDetail().observe(this, this::onJadeDetailChange);
            } else if (assessModel != null) {
                mViewModel.setEvaluationDetail(assessModel);
                mViewModel.getEvaluationDetail().observe(this, this::onEvaluationDetailChange);
            }
        }
    }

    private void initBanner() {
        mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        mBanner.setImages(mUrlList);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(BANNER_DELAY_TIME);
        mBanner.start();
    }


    private void initInfoLayout() {
        View view = LayoutInflater.from(this)
                .inflate(getInfoLayoutRes(), mInfoLayout, false);
        mInfoLayout.addView(view);
    }

    private void initBottomLayout() {
        View view = LayoutInflater.from(this)
                .inflate(getBottomLayout(), mBottomLayout, false);
        mBottomLayout.addView(view);
    }


    @LayoutRes
    protected abstract int getInfoLayoutRes();

    @LayoutRes
    protected abstract int getBottomLayout();


    protected void onJadeDetailChange(JadeModel jadeModel) {
        if (jadeModel.getGoodPics() != null) {
            mUrlList = jadeModel.getGoodPics().getImgUrls();
            initBanner();
        }
        mTitleTxt.setText(jadeModel.getGoodsName());
        mDescTxt.setText(jadeModel.getGoodDescribe());

    }

    protected void onEvaluationDetailChange(EvaluationModel assessModel) {
        if (assessModel.getStonePics() != null) {
            mUrlList = assessModel.getStonePics().getImgUrls();
            initBanner();
        }
        mTitleTxt.setText(assessModel.getStoneName());
        mDescTxt.setText(assessModel.getStoneDescription());
    }


    @Override
    protected boolean toolbarEnable() {
        return false;
    }
}
