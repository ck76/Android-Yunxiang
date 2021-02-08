
package com.neuqer.android.splash.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.neuqer.android.runtime.base.fragment.AbsFragment;
import com.neuqer.android.splash.R;
import com.neuqer.android.splash.R2;

import butterknife.BindView;


public class GuideFragment extends AbsFragment {

    public static final String KEY_IMG_RES = "key_img_res";
    public static final String KEY_SHOW_ENTER = "show_enter";

    @BindView(R2.id.img_content)
    ImageView contentImg;
    @BindView(R2.id.btn_enter)
    Button enterBtn;

    @DrawableRes
    private int mImgRes;
    private boolean mShowEnter;
    private OnEnterHomeListener mEnterHomeListener;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void initVariable() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mImgRes = bundle.getInt(KEY_IMG_RES);
        mShowEnter = bundle.getBoolean(KEY_SHOW_ENTER);
    }

    @Override
    protected void initView() {
        contentImg.setImageResource(mImgRes);
        if (mShowEnter) {
            enterBtn.setVisibility(View.VISIBLE);
            enterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mEnterHomeListener != null) {
                        mEnterHomeListener.enterHome();
                    }
                }
            });
        } else {
            enterBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEnterHomeListener) {
            mEnterHomeListener = (OnEnterHomeListener) context;
        }
    }
}
