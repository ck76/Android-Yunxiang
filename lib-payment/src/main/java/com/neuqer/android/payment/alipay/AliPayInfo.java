
package com.neuqer.android.payment.alipay;

import com.neuqer.android.payment.pay.PayInfo;

public class AliPayInfo implements PayInfo {

    private String orderInfo;

    public AliPayInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }
}
