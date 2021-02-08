
package com.neuqer.android.permission;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import java.util.List;


public class SimplePermission extends PermissionRequest {

    private SimplePermission(Builder builder) {
        super(builder);
    }

    public static class Builder extends PermissionRequest.Builder {

        private String mRationaleMessage;
        private String mRationaleBtn;
        private String mSettingMessage;
        private String mSettingPositiveBtn;
        private String mSettingNegativeBtn;

        public Builder(Context context, String... permissions) {
            super(context, permissions);
        }

        public Builder setRationaleMessage(String rationaleMessage) {
            mRationaleMessage = rationaleMessage;
            return this;
        }

        public Builder setRationaleBtn(String rationaleBtn) {
            mRationaleBtn = rationaleBtn;
            return this;
        }

        public Builder setSettingMessage(String settingMessage) {
            mSettingMessage = settingMessage;
            return this;
        }

        public Builder setSettingPositiveBtn(String settingPositiveBtn) {
            mSettingPositiveBtn = settingPositiveBtn;
            return this;
        }

        public Builder setSettingNegativeBtn(String settingNegativeBtn) {
            mSettingNegativeBtn = settingNegativeBtn;
            return this;
        }

        public void request() {
            // 默认Rationale Dialog
            if (!TextUtils.isEmpty(mRationaleMessage)) {
                setRationaleRender(new PermissionRationaleRender() {
                    @Override
                    public void doAction(List<String> params, final Process process) {
                        String positiveBtn = TextUtils.isEmpty(mRationaleBtn) ?
                                mContext.getString(R.string.permission_rationale_positive_btn) : mRationaleBtn;
                        new AlertDialog.Builder(mContext)
                                .setMessage(mRationaleMessage)
                                .setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        process.onNext();
                                    }
                                })
                                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        process.onCancel();
                                    }
                                }).show();
                    }
                });
            }
            // 权限设置页面
            if (!TextUtils.isEmpty(mSettingMessage)) {
                setSettingRender(new PermissionSettingRender() {
                    @Override
                    public void doAction(List<String> params, final Process process) {
                        String positiveBtn = TextUtils.isEmpty(mSettingPositiveBtn) ?
                                mContext.getString(R.string.permission_setting_positive_btn) : mSettingPositiveBtn;
                        String negativeBtn = TextUtils.isEmpty(mSettingNegativeBtn) ?
                                mContext.getString(R.string.permission_setting_negative_btn) : mSettingNegativeBtn;
                        new AlertDialog.Builder(mContext)
                                .setMessage(mSettingMessage)
                                .setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        process.onNext();
                                    }
                                })
                                .setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        process.onCancel();
                                    }
                                })
                                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        process.onCancel();
                                    }
                                }).show();
                    }
                });
            }
            // 发起请求
            new SimplePermission(this).start();
        }

    }

}
