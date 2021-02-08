

package com.neuqer.android.payment.pay;

import android.app.Activity;


public interface Pay<T extends PayInfo> {
    /**
     * 调起支付
     *
     * @param activity Activity
     * @param payInfo  支付信息
     * @param listener 支付结果回调
     */
    void callPay(Activity activity, T payInfo, PayResultListener listener);
}
