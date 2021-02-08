package com.neuqer.android.webview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.just.agentweb.IWebLayout;


public class SimpleWebLayout implements IWebLayout<WebView, FrameLayout> {

    private FrameLayout mRoot;
    private WebView mWebView;

    public SimpleWebLayout(Context context) {
        mRoot = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.simple_web_layout, null);
        mWebView = mRoot.findViewById(R.id.web_view);
    }

    @NonNull
    @Override
    public FrameLayout getLayout() {
        return mRoot;
    }

    @Nullable
    @Override
    public WebView getWebView() {
        return mWebView;
    }
}
