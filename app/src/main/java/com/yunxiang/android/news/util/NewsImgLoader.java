
package com.yunxiang.android.news.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class NewsImgLoader {

    public static void loadImg(Context context, ImageView imageView, String url) {
        if (context == null || imageView == null || TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);
    }
}
