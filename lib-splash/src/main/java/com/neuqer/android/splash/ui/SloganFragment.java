
package com.neuqer.android.splash.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.neuqer.android.runtime.base.fragment.AbsFragment;
import com.neuqer.android.splash.R;
import com.neuqer.android.splash.R2;

import butterknife.BindView;


public class SloganFragment extends AbsFragment {

    private static final int DELAY = 1500;
    public static final String KEY_IMG_RES = "key_img_res";

    @BindView(R2.id.img_slogan)
    ImageView sloganImg;

    @DrawableRes
    private int mImgRes;
    private OnEnterHomeListener mEnterHomeListener;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_slogan;
    }

    @Override
    protected void initVariable() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mImgRes = bundle.getInt(KEY_IMG_RES);
    }

    @Override
    protected void initView() {
        sloganImg.setImageResource(mImgRes);
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mEnterHomeListener != null) {
                    mEnterHomeListener.enterHome();
                }
            }
        }, DELAY);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEnterHomeListener) {
            mEnterHomeListener = (OnEnterHomeListener) context;
        }
    }
}
