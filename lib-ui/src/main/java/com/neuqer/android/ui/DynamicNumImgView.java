package com.neuqer.android.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import com.neuqer.android.ui.R;

public class DynamicNumImgView extends LinearLayout {

    private ImgLoader mImageLoader;
    private Context mContext;
    private List<? extends Model> mImgList;
    private int mImgNum;
    private OnImgClickListener mOnImgClickListener;

    public DynamicNumImgView(Context context) {
        this(context, null);
    }

    public DynamicNumImgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicNumImgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        mContext = context;
    }

    /**
     * 设置图片
     *
     * @param imgList 图片类表
     */
    public void setImgList(List<? extends Model> imgList, ImgLoader imageLoader) {
        if (imgList == null || imgList.size() <= 0 || imageLoader == null) {
            return;
        }
        mImageLoader = imageLoader;
        mImgList = imgList;
        removeAllViews();
        inflateLayout(imgList.size());
        loadImg();
    }

    public void setOnImgClickListener(OnImgClickListener onImgClickListener) {
        mOnImgClickListener = onImgClickListener;
    }

    /**
     * 加载布局
     *
     * @param imgNum 图片数量
     */
    private void inflateLayout(int imgNum) {
        int layoutRes;
        mImgNum = imgNum;
        switch (imgNum) {
            case 1:
                layoutRes = R.layout.dynamic_num_img_one;
                break;
            case 2:
                layoutRes = R.layout.dynamic_num_img_two;
                break;
            case 3:
                layoutRes = R.layout.dynamic_num_img_three;
                break;
            case 4:
                layoutRes = R.layout.dynamic_num_img_four;
                break;
            case 5:
                layoutRes = R.layout.dynamic_num_img_five;
                break;
            default:
                layoutRes = R.layout.dynamic_num_img_five;
                mImgNum = 5;
                break;
        }
        LayoutInflater.from(mContext).inflate(layoutRes, this);
    }

    /**
     * 加载图片
     */
    private void loadImg() {
        int curNum = 1;
        while (curNum <= mImgNum) {
            ImageView imageView = getImageView(curNum);
            if (imageView != null && mImageLoader != null) {
                final int realPos = curNum - 1;
                mImageLoader.loadImg(mContext, mImgList.get(realPos).getImgUrl(), imageView);
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnImgClickListener != null) {
                            mOnImgClickListener.onClick(v, realPos);
                        }
                    }
                });
            }
            curNum++;
        }
    }

    private ImageView getImageView(int position) {
        switch (position) {
            case 1:
                return findViewById(R.id.img_one);
            case 2:
                return findViewById(R.id.img_two);
            case 3:
                return findViewById(R.id.img_three);
            case 4:
                return findViewById(R.id.img_four);
            case 5:
                return findViewById(R.id.img_five);
            default:
                return null;
        }
    }

    public interface OnImgClickListener {
        void onClick(View view, int position);
    }

    public interface ImgLoader {
        void loadImg(Context context, String path, ImageView imageView);
    }

    public interface Model {
        String getImgUrl();
    }
}
