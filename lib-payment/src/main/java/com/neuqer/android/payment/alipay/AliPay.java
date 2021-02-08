
package com.neuqer.android.payment.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.neuqer.android.payment.pay.Pay;
import com.neuqer.android.payment.pay.PayResultListener;
import com.neuqer.android.payment.pay.PayType;

import java.util.Map;


public class AliPay implements Pay<AliPayInfo> {

    private PayResultListener mPayResultListener;
    private static final int SDK_PAY_FLAG = 0x01;

    @Override
    public void callPay(final Activity activity, final AliPayInfo payInfo, PayResultListener listener) {
        mPayResultListener = listener;

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(activity);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo.getOrderInfo(), true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    int resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (resultStatus == AliPayCode.CODE_SUCCESS) {
                        if (mPayResultListener != null) {
                            mPayResultListener.onSuccess(PayType.ALI_PAY, payResult.getResult());
                        }
                    } else if (resultStatus == AliPayCode.CODE_CANCEL) {
                        if (mPayResultListener != null) {
                            mPayResultListener.onCancel(PayType.ALI_PAY);
                        }
                    } else if (resultStatus == AliPayCode.CODE_HANDLING) {
                        if (mPayResultListener != null) {
                            mPayResultListener.onProcess(PayType.ALI_PAY);
                        }
                    } else {
                        if (mPayResultListener != null) {
                            mPayResultListener.onFailed(PayType.ALI_PAY, payResult.getResultStatus(),
                                    AliPayCode.getTextByCode(payResult.getResultStatus()));
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
