
package com.neuqer.android.payment;

import android.app.Activity;

import com.alipay.sdk.app.EnvUtils;
import com.neuqer.android.payment.alipay.AliPay;
import com.neuqer.android.payment.alipay.AliPayInfo;
import com.neuqer.android.payment.pay.PayResultListener;


public class SimplePay {

    /**
     * 调起支付宝支付
     *
     * @param activity  Activity
     * @param orderInfo 订单信息
     * @param listener  结果回调
     */
    public static void aipay(Activity activity, String orderInfo, PayResultListener listener) {
        // 使用沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        new AliPay().callPay(activity, new AliPayInfo(orderInfo), listener);
    }
}
