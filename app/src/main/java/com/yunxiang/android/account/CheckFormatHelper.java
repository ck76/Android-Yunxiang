package com.yunxiang.android.account;

import android.content.Context;
import android.text.TextUtils;

import com.neuqer.android.util.RegexpUtil;
import com.neuqer.android.util.ToastUtil;
import com.yunxiang.android.R;


public class CheckFormatHelper {

    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 20;
    private Context mContext;


    public CheckFormatHelper(Context context) {
        mContext = context;
    }

    /**
     * 手机号格式校验
     *
     * @param phoneNumber 手机号
     * @return 是否合规
     */
    public boolean checkPhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtil.show(mContext, R.string.phone_num_empty_error);
            return false;
        } else if (!RegexpUtil.checkMobile(phoneNumber)) {
            ToastUtil.show(mContext, R.string.please_enter_the_correct_phone_number);
            return false;
        }
        return true;
    }

    /**
     * 验证码格式校验
     *
     * @param code 验证码
     * @return 是否合规
     */
    public boolean checkVerifyCode(String code) {
        if (TextUtils.isEmpty(code)) {
            ToastUtil.show(mContext, R.string.verify_code_empty_error);
            return false;
        }
        return true;
    }

    /**
     * 密码格式校验
     *
     * @param password 密码
     * @return 是否合规
     */
    public boolean checkPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show(mContext, R.string.pwd_empty_error);
            return false;
        } else if (password.length() < PASSWORD_MIN_LENGTH
                || password.length() > PASSWORD_MAX_LENGTH) {
            ToastUtil.show(mContext, R.string.please_input_correct_length_password);
            return false;
        }
        return true;
    }

    /**
     * 确认密码校验
     *
     * @param password       输入密码
     * @param passwordRepeat 确认密码
     * @return 是否合规且相等
     */
    public boolean checkRepeatPassword(String password, String passwordRepeat) {
        if (!checkPassword(password)) {
            return false;
        } else if (TextUtils.isEmpty(passwordRepeat)) {
            ToastUtil.show(mContext, R.string.pwd_again_empty_error);
            return false;
        } else if (!TextUtils.equals(password, passwordRepeat)) {
            ToastUtil.show(mContext, R.string.inconsistent_password);
            return false;
        }
        return true;
    }
}
