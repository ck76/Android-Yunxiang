package com.neuqer.android.permission;


interface TypeListener<T> {

    /**
     * 接口回调
     *
     * @param params  参数
     * @param process 进度回调
     */
    void doAction(T params, Process process);

    /**
     * 进度回调接口
     */
    interface Process {
        /**
         * 下一步操作
         */
        void onNext();

        /**
         * 取消操作
         */
        void onCancel();
    }
}
