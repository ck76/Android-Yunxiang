
package com.yunxiang.android.news;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.neuqer.android.webview.WebViewActivity;
import com.yunxiang.android.R;


public class NewsDetailActivity extends WebViewActivity {

    private static final String KEY_URL = "url";
    private String mUrl;

    public static void start(Context context, String url) {
        if (context == null || TextUtils.isEmpty(url)) {
            return;
        }
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected int getWebViewParent() {
        return R.id.web_view_container;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initVariable() {
        mUrl = getIntent().getStringExtra(KEY_URL);
    }

    @Override
    protected void loadData() {
        loadUrl(mUrl);
    }
}
