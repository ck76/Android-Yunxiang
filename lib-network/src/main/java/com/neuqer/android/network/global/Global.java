
package com.neuqer.android.network.global;


public class Global {


    private Global() {
    }

    public static Global getInstance() {
        return Holder.sInstance;
    }

    /**
     * 使用全局参数创建请求
     */
    public <K> K createService(Class<K> service) {
        return GlobalRetrofit.getInstance().createService(service);
    }

    private static class Holder {
        private static final Global sInstance = new Global();
    }
}
