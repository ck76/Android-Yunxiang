

package com.yunxiang.android.news.view.item;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.yunxiang.android.R;


public abstract class NewsRelativeLayout extends RelativeLayout implements NewsItemLayout, NewsItem {

    public NewsRelativeLayout(Context context) {
        this(context, null);
    }

    public NewsRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsRelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NewsRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate(LayoutInflater.from(context));
        initView(context);
        setPadding(getResources().getDimensionPixelSize(R.dimen.news_padding_left),
                getResources().getDimensionPixelSize(R.dimen.news_padding_top),
                getResources().getDimensionPixelSize(R.dimen.news_padding_right),
                getResources().getDimensionPixelSize(R.dimen.news_padding_bottom));
    }
}
