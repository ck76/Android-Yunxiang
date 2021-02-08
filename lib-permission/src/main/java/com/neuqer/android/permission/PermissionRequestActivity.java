
package com.neuqer.android.permission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import java.util.Arrays;
import java.util.List;


public class PermissionRequestActivity extends AgentActivity {

    /** Extra 数据，权限列表 */
    private static final String EXTRA_PERMISSION = "extra_permission";
    /** 权限申请Code */
    private static final int PERMISSION_REQUEST_CODE = 0x1111;
    /** 申请完成后回调结果 */
    private static PermissionRequestResponder sRequestResponder;
    /** 权限列表 */
    private String[] mPermissions;

    /**
     * 调起权限申请页面
     *
     * @param context     上下文
     * @param permissions 权限列表
     */
    public static void startPermissionRequest(Context context, PermissionRequestResponder requestResponder,
                                              List<String> permissions) {
        if (context == null) {
            return;
        }
        sRequestResponder = requestResponder;
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.putExtra(EXTRA_PERMISSION, permissions.toArray());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPermissions = getIntent().getStringArrayExtra(EXTRA_PERMISSION);

        if (mPermissions != null && mPermissions.length > 0) {
            ActivityCompat.requestPermissions(this, mPermissions, PERMISSION_REQUEST_CODE);
        } else {
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (sRequestResponder != null) {
            sRequestResponder.onPermissionResult(Arrays.asList(permissions));
        }
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        sRequestResponder = null;
    }
}
