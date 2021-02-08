
package com.neuqer.android.permission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


public class PermissionSettingActivity extends AgentActivity {

    private static final int REQUEST_CODE = 0x1111;
    private static PermissionSettingResponder sSettingResponder;

    public static void startPermissionSetting(Context ctx, PermissionSettingResponder responder) {
        sSettingResponder = responder;
        Intent intent = new Intent(ctx, PermissionSettingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent settingIntent = SettingIntent.getCanResolvedSettingIntent(this);
        if (settingIntent != null) {
            startActivityForResult(settingIntent, REQUEST_CODE);
        } else {
            // 无法打开设置界面，回调以便继续后面流程
            notifySettingResult();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            notifySettingResult();
        }
    }

    private void notifySettingResult() {
        if (sSettingResponder != null) {
            sSettingResponder.onSettingResult();
        }
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        sSettingResponder = null;
    }
}
