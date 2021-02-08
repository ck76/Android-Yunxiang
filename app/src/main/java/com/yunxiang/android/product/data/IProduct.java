package com.yunxiang.android.product.data;


public interface IProduct {
    /**
     * 请求失败回调
     *
     * @param status 失败状态码
     */
    void onError(int status);

    interface IPublishListener extends IProduct {
        /**
         * 发布商品请求回调
         *
         * @param status 状态码
         */
        void publish(int status);
    }

    interface IAssessListener extends IProduct {
        /**
         * 预约商品评估
         *
         * @param status 状态码
         */
        void assess(int status);
    }
}
