package com.yunxiang.android.account.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.neuqer.android.util.ToastUtil;
import com.yunxiang.android.R;
import com.yunxiang.android.account.AccountConstants;
import com.yunxiang.android.account.data.AccountRepository;
import com.yunxiang.android.account.data.IAccount;
import com.yunxiang.android.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends AccountBaseActivity implements IAccount.ILoginListener {

    @BindView(R.id.edit_login_password)
    EditText mPasswordEdit;
    @BindView(R.id.btn_login_next)
    Button mNextBtn;
    @BindView(R.id.txt_login_previous)
    TextView mPreviousTxt;
    @BindView(R.id.txt_login_verification_code_login)
    TextView mVerifyCodeTxt;
    @BindView(R.id.txt_login_forget_password)
    TextView mForgetPwdTxt;

    @Override
    protected boolean toolbarEnable() {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login_password;
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
    }

    @OnClick({R.id.btn_login_next,
            R.id.txt_login_previous,
            R.id.txt_login_verification_code_login,
            R.id.txt_login_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_next:
                login(mPasswordEdit.getText().toString().trim());
                break;
            case R.id.txt_login_previous:
                startActivity(AccountActivity.class, true);
                break;
            case R.id.txt_login_verification_code_login:
                mDataCenter.put(AccountConstants.KEY_VERIFY_TYPE,
                        AccountConstants.VerifyType.LOGIN);
                startActivity(VerifyActivity.class, true);
                break;
            case R.id.txt_login_forget_password:
                mDataCenter.put(AccountConstants.KEY_VERIFY_TYPE,
                        AccountConstants.VerifyType.RESET);
                startActivity(VerifyActivity.class, true);
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     *
     * @param password 用户密码
     */
    private void login(String password) {
        if (mFormatHelper.checkPassword(password)) {
            mLoadingDialog.show();
            String phone = mDataCenter.get(AccountConstants.KEY_PHONE_NUMBER);
            mRepository.loginWithPwd(phone, password, this);
        }
    }

    @Override
    public void onLoginSuccess(String token, int status) {
        mLoadingDialog.dismiss();
        mDataCenter.put(AccountConstants.KEY_TOKEN, token);
        startActivity(MainActivity.class, true);
    }

    @Override
    public void onError(int status) {
        mLoadingDialog.dismiss();
        switch (status) {
            case AccountRepository.PHONE_PWD_MISMATCH:
                ToastUtil.show(mContext, R.string.password_incorrect_error);
                break;
            case AccountRepository.ERROR_TIME_LIMIT:
                ToastUtil.show(mContext, R.string.errors_exceeded);
                startActivity(VerifyActivity.class, true);
                break;
            default:
                break;
        }
    }
}
