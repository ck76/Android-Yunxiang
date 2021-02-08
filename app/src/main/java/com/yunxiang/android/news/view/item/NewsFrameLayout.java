
package com.yunxiang.android.news.view.item;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.yunxiang.android.R;


public abstract class NewsFrameLayout extends FrameLayout implements NewsItemLayout, NewsItem {

    public NewsFrameLayout(Context context) {
        this(context, null);
    }

    public NewsFrameLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NewsFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate(LayoutInflater.from(context));
        initView(context);
        setPadding(getResources().getDimensionPixelSize(R.dimen.news_padding_left),
                getResources().getDimensionPixelSize(R.dimen.news_padding_top),
                getResources().getDimensionPixelSize(R.dimen.news_padding_right),
                getResources().getDimensionPixelSize(R.dimen.news_padding_bottom));
    }
}
