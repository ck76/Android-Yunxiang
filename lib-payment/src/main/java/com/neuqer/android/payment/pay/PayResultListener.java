
package com.neuqer.android.payment.pay;


public interface PayResultListener {

    /**
     * 支付成功
     */
    void onSuccess(PayType type, String resultInfo);

    /**
     * 正在处理中，支付结果未知，需要根据Server状态进行查询确认
     */
    void onProcess(PayType type);

    /**
     * 支付失败
     */
    void onFailed(PayType type, int errCode, String errMsg);

    /**
     * 取消支付
     */
    void onCancel(PayType type);
}
