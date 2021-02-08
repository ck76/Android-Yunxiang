package com.yunxiang.android.account.view;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunxiang.android.R;
import com.yunxiang.android.account.AccountConstants;

import butterknife.BindView;
import butterknife.OnClick;


public class RegisterActivity extends AccountBaseActivity {

    @BindView(R.id.edit_register_phone_number)
    EditText mPhoneEdit;
    @BindView(R.id.edit_register_password)
    EditText mPasswordEdit;
    @BindView(R.id.edit_register_password_again)
    EditText mPwdRepeatEdit;
    @BindView(R.id.btn_register_next)
    Button mNextBtn;
    @BindView(R.id.txt_register_previous)
    TextView mPreviousTxt;

    private String mPhoneNum;

    @Override
    protected boolean toolbarEnable() {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register_main;
    }

    @Override
    protected String getActivityTitle() {
        return null;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        mPhoneNum = mDataCenter.get(AccountConstants.KEY_PHONE_NUMBER);
        mPhoneEdit.setText(mPhoneNum);
        mPhoneEdit.setClickable(false);
    }

    @OnClick(R.id.btn_register_next)
    public void onBtnRegisterNextClicked() {
        mPhoneNum = mPhoneEdit.getText().toString().trim();
        String password = mPasswordEdit.getText().toString().trim();
        String pwdRepeat = mPwdRepeatEdit.getText().toString().trim();
        if (mFormatHelper.checkPhoneNumber(mPhoneNum)
                && mFormatHelper.checkRepeatPassword(password, pwdRepeat)) {
            mDataCenter.put(AccountConstants.KEY_PHONE_NUMBER, mPhoneNum)
                    .put(AccountConstants.KEY_PASSWORD, password)
                    .put(AccountConstants.KEY_VERIFY_TYPE, AccountConstants.VerifyType.REGISTER);
            startActivity(VerifyActivity.class, true);
        }
    }

    @OnClick(R.id.txt_register_previous)
    public void onTxtRegisterPreviousClicked() {
        startActivity(AccountActivity.class, true);
    }
}
