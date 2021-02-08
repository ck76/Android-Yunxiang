package com.yunxiang.android.account.data;

import com.google.gson.JsonObject;
import com.neuqer.android.cache.SimpleCache;
import com.neuqer.android.network.HttpClient;
import com.neuqer.android.network.response.ApiResponse;
import com.neuqer.android.runtime.AppRuntime;
import com.yunxiang.android.account.AccountConstants;


public class AccountRepository {

    /**
     * 请求成功状态码
     */
    public static final int STATUS_CODE_OK = 0;
    /**
     * 该手机号对应的验证码不存在或已过期状态码
     */
    public static final int VERIFY_NOT_EXIST = 1001;
    /**
     * 验证码不正确
     */
    public static final int VERIFY_INCORRECT = 1002;
    /**
     * 该手机号验证码请求过于频繁
     */
    public static final int FREQUENT_REQUESTS = 1003;
    /**
     * 手机号未被注册
     */
    public static final int UNREGISTERED = 1004;
    /**
     * 该手机号已注册
     */
    public static final int ALREADY_REGISTERED = 2001;
    /**
     * 用户未登录
     */
    public static final int NOT_LOGGED_IN = 3001;
    /**
     * token与手机号不匹配
     */
    public static final int PHONE_TOKEN_MISMATCH = 3003;
    /**
     * 手机号和密码不匹配
     */
    public static final int PHONE_PWD_MISMATCH = 3004;

    // TODO: 2018/11/19 超过密码错误次数限制 server暂无该错误码 
    public static final int ERROR_TIME_LIMIT = -1;


    private static final String FIELD_HAS_SIGNED = "isSignedUp";

    private static final String FIELD_TOKEN = "token";


    private AccountApi mApi;

    private AccountRepository(AccountApi api) {
        mApi = api;
    }

    private static class AccountDataManagerHolder {
        private static final AccountRepository INSTANCE = new AccountRepository(
                HttpClient.getInstance().createService(AccountApi.class)
        );
    }

    public static AccountRepository getInstance() {
        return AccountDataManagerHolder.INSTANCE;
    }

    /**
     * 判断手机号是否已经注册
     */
    public void checkHasSigned(String phoneNum, IAccount.ICheckSignedListener listener) {

        mApi.checkHasSigned(phoneNum).enqueue(new ApiCallBack<JsonObject>(listener) {
            @Override
            void onDataBack(ApiResponse<JsonObject> response) {
                JsonObject jsonObject = response.getData();
                SimpleCache.get(AppRuntime.getAppContext())
                        .put(AccountConstants.KEY_PHONE_NUMBER, phoneNum);
                listener.hasSigned(jsonObject.get(FIELD_HAS_SIGNED).getAsBoolean());
            }
        });
    }

    /**
     * 获取注册验证码
     */
    public void getRegisterVerify(String phone, IAccount.IVerifyListener listener) {
        mApi.getRegisterVerify(phone).enqueue(new ApiCallBack<Void>(listener) {
            @Override
            void onDataBack(ApiResponse<Void> response) {
                listener.onMsgSend(response.getCode());
            }
        });
    }

    /**
     * 携带验证码注册
     */
    public void register(String phone, String code, String password, IAccount.ILoginListener listener) {
        mApi.register(phone, code, password).enqueue(new ApiCallBack<JsonObject>(listener) {
            @Override
            void onDataBack(ApiResponse<JsonObject> response) {
                JsonObject jsonObject = response.getData();
                String token = jsonObject.get(FIELD_TOKEN).getAsString();
                int code = response.getCode();
                SimpleCache.get(AppRuntime.getAppContext()).put(AccountConstants.KEY_TOKEN, token);
                listener.onLoginSuccess(token, code);
            }
        });
    }

    /**
     * 获取登录时验证码
     */
    public void getLoginVerify(String phone, IAccount.IVerifyListener listener) {
        mApi.getLoginVerify(phone).enqueue(new ApiCallBack<Void>(listener) {
            @Override
            void onDataBack(ApiResponse<Void> response) {
                listener.onMsgSend(response.getCode());
            }
        });
    }

    /**
     * 密码登录
     */
    public void loginWithPwd(String phone, String password, IAccount.ILoginListener listener) {
        mApi.loginWithPwd(phone, password).enqueue(new ApiCallBack<JsonObject>(listener) {
            @Override
            void onDataBack(ApiResponse<JsonObject> response) {
                JsonObject jsonObject = response.getData();
                String token = jsonObject.get(FIELD_TOKEN).getAsString();
                int code = response.getCode();
                SimpleCache.get(AppRuntime.getAppContext()).put(AccountConstants.KEY_TOKEN, token);
                listener.onLoginSuccess(token, code);
            }
        });
    }

    /**
     * 验证码登录
     */
    public void loginWithCode(String phone, String code, IAccount.ILoginListener listener) {
        mApi.loginWithCode(phone, code).enqueue(new ApiCallBack<JsonObject>(listener) {
            @Override
            void onDataBack(ApiResponse<JsonObject> response) {
                JsonObject jsonObject = response.getData();
                String token = jsonObject.get(FIELD_TOKEN).getAsString();
                int code = response.getCode();
                SimpleCache.get(AppRuntime.getAppContext()).put(AccountConstants.KEY_TOKEN, token);
                listener.onLoginSuccess(token, code);
            }
        });
    }

    /**
     * 修改密码时获取验证码
     */
    public void getResetVerify(String phone, IAccount.IVerifyListener listener) {
        mApi.getResetVerify(phone).enqueue(new ApiCallBack<Void>(listener) {
            @Override
            void onDataBack(ApiResponse<Void> response) {
                listener.onMsgSend(response.getCode());
            }
        });
    }

    /**
     * 修改密码时确认验证码正确性
     */
    public void verify(String password, String code, IAccount.IVerifyListener listener) {
        mApi.verify(password, code).enqueue(new ApiCallBack<Void>(listener) {
            @Override
            void onDataBack(ApiResponse<Void> response) {
                listener.onVerifySuccess(response.getCode());
            }
        });
    }

    /**
     * 修改密码
     */
    public void resetPassword(String phone, String code, String password, IAccount.IResetListener listener) {
        mApi.resetPassword(phone, code, password).enqueue(new ApiCallBack<Void>(listener) {
            @Override
            void onDataBack(ApiResponse<Void> response) {
                listener.onResetSuccess(response.getCode());
            }
        });
    }
}
