

package com.yunxiang.android.news.view.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.yunxiang.android.news.model.NewsItemText;
import com.yunxiang.android.news.model.NewsModel;

import com.yunxiang.android.R;


public class NewsTextView extends NewsRelativeLayout {

    private TextView mTitle;
    private NewsLabelView mLabelView;

    public NewsTextView(Context context) {
        super(context);
    }

    @Override
    public void initInflate(LayoutInflater inflater) {
        inflater.inflate(R.layout.news_item_text, this);
    }

    @Override
    public void initView(Context context) {
        mTitle = findViewById(R.id.news_title);
        mLabelView = findViewById(R.id.news_label);
    }

    @Override
    public void updateView(NewsModel model) {
        NewsItemText itemText = (NewsItemText) model.data;
        if (mTitle != null) {
            mTitle.setText(itemText.title);
        }
        if (mLabelView != null) {
            mLabelView.updateView(model);
        }
    }
}
