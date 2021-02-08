package com.neuqer.android.sample.module;

import android.Manifest;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import com.neuqer.android.permission.PermissionRequestCallback;
import com.neuqer.android.permission.SimplePermission;
import com.neuqer.android.runtime.base.activity.AbsActivity;

import com.neuqer.android.sample.R;


public class TestActivity extends AbsActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_test;
    }

    @Override
    protected String getActivityTitle() {
        return "测试页面";
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SimplePermission.Builder(TestActivity.this, Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS)
                        .setRationaleMessage("必须申请相册权限")
                        .setRationaleBtn("好的")
                        .setSettingMessage("必须设置相机权限")
                        .setSettingPositiveBtn("去设置")
                        .setSettingNegativeBtn("取消")
                        .setRequestCallback(new PermissionRequestCallback() {
                            @Override
                            public void onPermissionGranted() {
                                Toast.makeText(TestActivity.this, "允许", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onPermissionDenied(List<String> deniedPermissions) {
                                Toast.makeText(TestActivity.this, "拒绝", Toast.LENGTH_LONG).show();
                            }
                        })
                        .request();
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
