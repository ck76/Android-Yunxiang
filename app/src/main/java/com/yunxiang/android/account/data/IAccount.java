package com.yunxiang.android.account.data;


public interface IAccount {

    /**
     * 请求失败回调
     *
     * @param status 失败状态码
     */
    void onError(int status);


    interface ICheckSignedListener extends IAccount {

        /**
         * 注册检查回调
         *
         * @param isSignedUp 是否注册
         */
        void hasSigned(boolean isSignedUp);
    }

    interface ILoginListener extends IAccount {

        /**
         * 登录/注册成功
         *
         * @param token  token
         * @param status 状态码
         */
        void onLoginSuccess(String token, int status);
    }

    interface IResetListener extends IAccount {
        /**
         * 重置密码成功
         *
         * @param status 状态码
         */
        void onResetSuccess(int status);
    }

    interface IVerifyListener extends IAccount {

        /**
         * 验证码发送成功回调
         *
         * @param status 状态码
         */
        void onMsgSend(int status);

        /**
         * 验证码校验成功回调
         *
         * @param status 状态码
         */
        void onVerifySuccess(int status);
    }
}
