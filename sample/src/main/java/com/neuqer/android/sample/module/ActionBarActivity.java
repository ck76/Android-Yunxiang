package com.neuqer.android.sample.module;


import com.neuqer.android.runtime.base.activity.AbsActivity;

import com.neuqer.android.sample.R;


public class ActionBarActivity extends AbsActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_action_bar;
    }

    @Override
    protected String getActivityTitle() {
        return "ActionAar";
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected boolean backButtonEnable() {
        return true;
    }
}
