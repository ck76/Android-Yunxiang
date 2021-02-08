package com.yunxiang.android.account.view;

import android.widget.Button;
import android.widget.EditText;

import com.yunxiang.android.R;
import com.yunxiang.android.account.AccountConstants;
import com.neuqer.android.util.ToastUtil;
import com.yunxiang.android.account.data.IAccount;
import com.yunxiang.android.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class ResetActivity extends AccountBaseActivity implements IAccount.IResetListener {

    @BindView(R.id.edit_retrive_input_new_password)
    EditText inputPwdEdit;
    @BindView(R.id.edit_retrive_input_again)
    EditText inputPwdAgainEdit;
    @BindView(R.id.btn_retrive_confirm_reset_password)
    Button confirmResetPwdBtn;

    private String mPhoneNum;
    private String mCode;

    @Override
    protected boolean toolbarEnable() {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_retrieve_password;
    }

    @Override
    protected String getActivityTitle() {
        return null;
    }

    @Override
    protected void initVariable() {
        mPhoneNum = mDataCenter.get(AccountConstants.KEY_PHONE_NUMBER);
        mCode = mDataCenter.get(AccountConstants.KEY_VERIFY_CODE);
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.btn_retrive_confirm_reset_password)
    public void onViewClicked() {
        String password = inputPwdEdit.getText().toString().trim();
        String passwordRepeat = inputPwdAgainEdit.getText().toString().trim();
        if (mFormatHelper.checkRepeatPassword(password, passwordRepeat)) {
            mLoadingDialog.show();
            mRepository.resetPassword(mPhoneNum, mCode, password, this);
        }
    }

    @Override
    public void onResetSuccess(int status) {
        mLoadingDialog.dismiss();
        ToastUtil.show(mContext, R.string.reset_password_success);
        startActivity(MainActivity.class, true);
    }

    @Override
    public void onError(int status) {
        mLoadingDialog.dismiss();
    }
}
