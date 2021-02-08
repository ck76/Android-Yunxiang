
package com.yunxiang.android.news.view.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunxiang.android.news.model.NewsItemOneImg;
import com.yunxiang.android.news.model.NewsModel;

import com.yunxiang.android.R;
import com.yunxiang.android.news.util.NewsImgLoader;


public class NewsOneImgLeftView extends NewsRelativeLayout {

    private TextView mTitle;
    private ImageView mImageView;
    private NewsLabelView mLabelView;

    public NewsOneImgLeftView(Context context) {
        super(context);
    }

    @Override
    public void initInflate(LayoutInflater inflater) {
        inflater.inflate(R.layout.news_item_one_img_left, this);
    }

    @Override
    public void initView(Context context) {
        mTitle = findViewById(R.id.news_title);
        mImageView = findViewById(R.id.news_img);
        mLabelView = findViewById(R.id.news_label);
    }

    @Override
    public void updateView(NewsModel model) {
        NewsItemOneImg oneImg = (NewsItemOneImg) model.data;
        if (mTitle != null) {
            mTitle.setText(oneImg.title);
        }
        NewsImgLoader.loadImg(getContext(), mImageView, oneImg.imgUrl);
        if (mLabelView != null) {
            mLabelView.updateView(model);
        }
    }
}
