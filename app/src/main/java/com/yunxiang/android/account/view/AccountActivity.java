package com.yunxiang.android.account.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;

import com.yunxiang.android.R;
import com.yunxiang.android.account.AccountConstants;
import com.yunxiang.android.account.data.IAccount;


public class AccountActivity extends AccountBaseActivity implements IAccount.ICheckSignedListener {

    @BindView(R.id.edit_login_phone_number)
    EditText mPhoneEdit;
    @BindView(R.id.btn_login_next)
    Button mNextBtn;

    private String mPhoneNum;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login_main;
    }

    @Override
    protected String getActivityTitle() {
        return null;
    }

    @Override
    protected void initVariable() {
        mDataCenter.onCleared();
    }

    @Override
    protected void initView() {
        initEdit();
    }

    private void initEdit() {
        mPhoneEdit.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                mPhoneEdit.setCursorVisible(true);
                mPhoneEdit.setHint("");
            } else {
                mPhoneEdit.setCursorVisible(false);
                mPhoneEdit.setHint(R.string.edit_login_main_hint);
            }
        });
    }

    @Override
    protected boolean toolbarEnable() {
        return false;
    }

    @OnClick(R.id.btn_login_next)
    public void onViewClicked(View view) {
        mPhoneNum = mPhoneEdit.getText().toString().trim();
        if (mFormatHelper.checkPhoneNumber(mPhoneNum)) {
            mLoadingDialog.show();
            mRepository.checkHasSigned(mPhoneNum, this);
        }
    }

    @Override
    public void hasSigned(boolean isSignedUp) {
        mLoadingDialog.dismiss();
        mDataCenter.put(AccountConstants.KEY_PHONE_NUMBER, mPhoneNum);
        startActivity(isSignedUp ? LoginActivity.class : RegisterActivity.class, true);
    }

    @Override
    public void onError(int status) {
        mLoadingDialog.dismiss();
    }
}
