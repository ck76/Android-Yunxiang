package com.neuqer.android.splash.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.neuqer.android.runtime.base.fragment.AbsFragment;
import com.neuqer.android.splash.R;
import com.neuqer.android.splash.R2;

import java.io.File;

import butterknife.BindView;


public class SplashAdFragment extends AbsFragment {

    public static final String KEY_IMG_PATH = "key_img_path";

    @BindView(R2.id.img_content)
    ImageView contentImg;
    @BindView(R2.id.btn_skip)
    Button skipBtn;

    private String mImgPath;
    private OnEnterHomeListener mEnterHomeListener;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_splash_ad;
    }

    @Override
    protected void initVariable() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mImgPath = bundle.getString(KEY_IMG_PATH);
    }

    @Override
    protected void initView() {
        if (!TextUtils.isEmpty(mImgPath)) {
            Glide.with(this).load(new File(mImgPath)).into(contentImg);
        }
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEnterHomeListener != null) {
                    mEnterHomeListener.enterHome();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEnterHomeListener) {
            mEnterHomeListener = (OnEnterHomeListener) context;
        }
    }
}
