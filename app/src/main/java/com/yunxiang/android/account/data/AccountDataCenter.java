package com.yunxiang.android.account.data;

import android.os.Bundle;
import android.os.Looper;

import java.util.HashMap;


public class AccountDataCenter {

    private HashMap<String, Object> mDataCenter;
    private Thread mMainThread;

    private AccountDataCenter() {
        mDataCenter = new HashMap<>();
    }

    private static class AccountDataCenterHolder {

        static final AccountDataCenter INSTANCE = new AccountDataCenter();
    }

    public static AccountDataCenter getInstance() {
        return AccountDataCenterHolder.INSTANCE;
    }

    public AccountDataCenter put(String key, Object data) {
        if (data != null && isMainThread()) {
            mDataCenter.put(key, data);
        }
        return this;
    }

    public AccountDataCenter put(Bundle bundle) {
        if (bundle != null && isMainThread()) {
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                if (value != null) {
                    mDataCenter.put(key, value);
                }
            }
        }
        return this;
    }

    public <T> T get(String key) {
        return (T) this.mDataCenter.get(key);
    }


    public <T> T get(String key, T defaultValue) {
        return !this.mDataCenter.containsKey(key) ? defaultValue : this.get(key);
    }


    public void onCleared() {
        mDataCenter.clear();
    }

    private boolean isMainThread() {
        if (this.mMainThread == null) {
            this.mMainThread = Looper.getMainLooper().getThread();
        }
        return Thread.currentThread() == this.mMainThread;
    }
}
