package com.yunxiang.android.news.view.item;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunxiang.android.news.model.NewsModel;

import com.yunxiang.android.R;
import com.yunxiang.android.news.model.NewsModel;


public class NewsLabelView extends LinearLayout {

    /** 上下文 */
    private Context mContext;
    /** 来源 */
    private TextView mSourceView;

    public NewsLabelView(Context context) {
        this(context, null);
    }

    public NewsLabelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsLabelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        mSourceView = new TextView(mContext);
        mSourceView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                mContext.getResources().getDimensionPixelOffset(R.dimen.news_label_text_size));
        mSourceView.setTextColor(ContextCompat.getColor(mContext, R.color.news_label_text));

        addView(mSourceView);
    }

    public void updateView(NewsModel newsModel) {
        if (newsModel == null) {
            return;
        }
        if (!TextUtils.isEmpty(newsModel.data.source)) {
            setVisibility(VISIBLE);
            mSourceView.setText(newsModel.data.source);
        } else {
            setVisibility(GONE);
        }
    }
}
