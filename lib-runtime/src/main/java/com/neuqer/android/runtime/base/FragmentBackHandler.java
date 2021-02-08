
package com.neuqer.android.runtime.base;


public interface FragmentBackHandler {

    /**
     * 返回键被按下
     *
     * @return true: 消费返回键
     */
    boolean onBackPressed();
}
