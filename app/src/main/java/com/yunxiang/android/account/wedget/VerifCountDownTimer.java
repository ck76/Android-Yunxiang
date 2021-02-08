package com.yunxiang.android.account.wedget;

import android.os.CountDownTimer;

public class VerifCountDownTimer extends CountDownTimer {

    public static final long INTERVAL_ONE_SECOND = 1000;
    public static final long ONE_MINUTE = 60000;
    public static long CURRENT_TIME_MILLS = 0;
    public static boolean FLAG_FIRST_IN = true;

    public VerifCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    /**
     * 当前任务每完成一次倒计时间隔时间时回调
     */
    @Override
    public void onTick(long millisUntilFinished) {

    }

    /**
     * 当前任务完成的时候回调
     */
    @Override
    public void onFinish() {

    }

    public void timerStart(boolean onClick) {

        if (onClick) {
            VerifCountDownTimer.CURRENT_TIME_MILLS = System.currentTimeMillis();
        }
        VerifCountDownTimer.FLAG_FIRST_IN = false;
        this.start();
    }
}
