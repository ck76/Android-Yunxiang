
package com.yunxiang.android.base;

import com.neuqer.android.webview.WebViewActivity;
import com.yunxiang.android.R;


public class WebViewTest extends WebViewActivity {

    @Override
    protected int getWebViewParent() {
        return R.id.web_view_container;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.test;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void loadData() {
        super.loadData();
        loadUrl("https://m.jd.com/");
    }
}
