
package com.yunxiang.android.news.view.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunxiang.android.R;
import com.yunxiang.android.news.model.NewsItemThreeImg;
import com.yunxiang.android.news.model.NewsModel;
import com.yunxiang.android.news.util.NewsImgLoader;


public class NewsThreeImgView extends NewsRelativeLayout {

    private TextView mTitle;
    private ImageView mImgLeft;
    private ImageView mImgMiddle;
    private ImageView mImgRight;
    private NewsLabelView mLabelView;

    public NewsThreeImgView(Context context) {
        super(context);
    }

    @Override
    public void initInflate(LayoutInflater inflater) {
        inflater.inflate(R.layout.news_item_three_img, this);
    }

    @Override
    public void initView(Context context) {
        mTitle = findViewById(R.id.news_title);
        mImgLeft = findViewById(R.id.img_left);
        mImgMiddle = findViewById(R.id.img_middle);
        mImgRight = findViewById(R.id.img_right);
        mLabelView = findViewById(R.id.news_label);
    }

    @Override
    public void updateView(NewsModel model) {
        NewsItemThreeImg threeImg = (NewsItemThreeImg) model.data;
        if (mTitle != null) {
            mTitle.setText(threeImg.title);
        }
        NewsImgLoader.loadImg(getContext(), mImgLeft, threeImg.leftImgUrl);
        NewsImgLoader.loadImg(getContext(), mImgMiddle, threeImg.midImgUrl);
        NewsImgLoader.loadImg(getContext(), mImgRight, threeImg.rightImgUrl);
        if (mLabelView != null) {
            mLabelView.updateView(model);
        }
    }
}
