package com.yunxiang.android.account.view;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.neuqer.android.ui.countdowntimer.CustomCountDownTimer;
import com.neuqer.android.util.ToastUtil;
import com.yunxiang.android.R;
import com.yunxiang.android.account.AccountConstants;
import com.yunxiang.android.account.data.AccountRepository;
import com.yunxiang.android.account.data.IAccount;
import com.yunxiang.android.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yunxiang.android.account.AccountConstants.KEY_VERIFY_CODE;
import static com.yunxiang.android.account.AccountConstants.VerifyType.LOGIN;
import static com.yunxiang.android.account.AccountConstants.VerifyType.REGISTER;
import static com.yunxiang.android.account.AccountConstants.VerifyType.RESET;


public class VerifyActivity extends AccountBaseActivity implements IAccount.IVerifyListener, IAccount.ILoginListener {

    @BindView(R.id.edit_login_input_verification_code)
    EditText mVerifyEdit;
    @BindView(R.id.txt_login_obtain_verification_code)
    CustomCountDownTimer mTimerTxt;
    @BindView(R.id.btn_login_next)
    Button mNextBtn;
    @BindView(R.id.txt_login_previous)
    TextView mPreviousTxt;

    private String mPhoneNum;
    private String mPassword;
    private String mCode;
    private int mVerifyType;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login_verification;
    }

    @Override
    protected boolean toolbarEnable() {
        return false;
    }

    @Override
    protected String getActivityTitle() {
        return null;
    }

    @Override
    protected void initVariable() {
        mPhoneNum = mDataCenter.get(AccountConstants.KEY_PHONE_NUMBER);
        mPassword = mDataCenter.get(AccountConstants.KEY_PASSWORD);
        mVerifyType = mDataCenter.get(AccountConstants.KEY_VERIFY_TYPE);
    }

    @Override
    protected void initView() {
        startTimer(false);
    }


    @OnClick(R.id.txt_login_obtain_verification_code)
    public void onTxtTimerClicked() {
        mTimerTxt.setClickable(false);
        mLoadingDialog.show();
        switch (mVerifyType) {
            case LOGIN:
                mRepository.getLoginVerify(mPhoneNum, this);
                break;
            case REGISTER:
                mRepository.getRegisterVerify(mPhoneNum, this);
                break;
            case RESET:
                mRepository.getResetVerify(mPhoneNum, this);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btn_login_next)
    public void onBtnNextClicked() {
        mCode = mVerifyEdit.getText().toString().trim();
        if (mFormatHelper.checkVerifyCode(mCode)) {
            mLoadingDialog.show();
        }
        switch (mVerifyType) {
            case LOGIN:
                mRepository.loginWithCode(mPhoneNum, mCode, this);
                break;
            case REGISTER:
                mRepository.register(mPhoneNum, mCode, mPassword, this);
                break;
            case RESET:
                mRepository.verify(mPhoneNum, mCode, this);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.txt_login_previous)
    public void onTxtPreviousClicked() {
        startActivity(mVerifyType == REGISTER
                ? RegisterActivity.class : LoginActivity.class, true);
    }

    @Override
    public void onLoginSuccess(String token, int status) {
        mLoadingDialog.dismiss();
        startActivity(MainActivity.class, true);
    }


    @Override
    public void onVerifySuccess(int status) {
        mLoadingDialog.dismiss();
        mDataCenter.put(KEY_VERIFY_CODE, mCode);
        startActivity(ResetActivity.class, true);
    }

    @Override
    public void onMsgSend(int status) {
        mLoadingDialog.dismiss();
        ToastUtil.show(mContext, R.string.verify_code_send_success);
        startTimer(true);
    }

    @Override
    public void onError(int status) {
        mLoadingDialog.dismiss();
        switch (status) {
            case AccountRepository.VERIFY_NOT_EXIST:
                ToastUtil.show(mContext, R.string.verify_code_not_exist);
                break;
            case AccountRepository.VERIFY_INCORRECT:
                ToastUtil.show(mContext, R.string.verify_code_incorrect_error);
                break;
            case AccountRepository.FREQUENT_REQUESTS:
                ToastUtil.show(mContext, R.string.frequent_requests);
                break;
            default:
                break;
        }
    }


    private void startTimer(boolean onCLick) {
        mTimerTxt.initTimer(onCLick);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimerTxt.cancle();
    }
}
