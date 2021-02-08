package com.yunxiang.android.account;


public class AccountConstants {

    /** 用户手机号 */
    public static final String KEY_PHONE_NUMBER = "key_phone_number";

    /** 用户密码 */
    public static final String KEY_PASSWORD = "key_password";

    /** 验证码 */
    public static final String KEY_VERIFY_CODE = "key_verify_code";

    /** token */
    public static final String KEY_TOKEN = "key_token";

    /** 验证码类型 */
    public static final String KEY_VERIFY_TYPE = "key_verify_type";

    public interface   VerifyType {

        /** 登录验证码 */
        int LOGIN = 0;

        /** 注册验证码 */
        int REGISTER = 1;

        /** 重置验证 */
        int RESET = 2;
    }
}
