package com.neuqer.android.ui.searchview;

import android.view.View;


public interface AnimationListener {
    /**
     * @return true to override parent. Else execute Parent method
     */
    boolean onAnimationStart(View view);

    boolean onAnimationEnd(View view);

    boolean onAnimationCancel(View view);
}