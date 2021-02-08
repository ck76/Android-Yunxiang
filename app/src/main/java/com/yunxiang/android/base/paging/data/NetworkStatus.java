package com.yunxiang.android.base.paging.data;


public class NetworkStatus {


    private NetworkStatus.Status mStatus;

    public NetworkStatus(Status status) {
        mStatus = status;
    }

    public boolean isLoading() {
        return this.mStatus == Status.RUNNING;
    }

    public boolean isSuccess() {
        return this.mStatus == Status.SUCCESS;
    }

    public boolean isFailed() {
        return this.mStatus == Status.FAILED;
    }

    public NetworkStatus.Status getStatus() {
        return mStatus;
    }

    public static NetworkStatus loading() {
        return new NetworkStatus(Status.RUNNING);
    }

    public static NetworkStatus error() {
        return new NetworkStatus(Status.FAILED);
    }

    public static NetworkStatus success() {
        return new NetworkStatus(Status.SUCCESS);
    }

    public enum Status {

        /** 正在请求 */
        RUNNING(1),
        /** 请求成功 */
        SUCCESS(0),
        /** 请求失败 */
        FAILED(-1);

        int mStatus;

        Status(int status) {
            mStatus = status;
        }
    }
}
