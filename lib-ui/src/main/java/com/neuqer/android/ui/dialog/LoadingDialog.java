package com.neuqer.android.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.neuqer.android.ui.R;


public class LoadingDialog extends AbsDialog {

    private TextView mHintText;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.dialog, Gravity.CENTER,
                context.getResources().getDimensionPixelOffset(R.dimen.dialog_loading_width),
                WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public LoadingDialog setHintText(String text) {
        if (!TextUtils.isEmpty(text) && mHintText != null) {
            mHintText.setText(text);
        }
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void init(View rootView) {
        mHintText = rootView.findViewById(R.id.txt_hint);
    }
}
