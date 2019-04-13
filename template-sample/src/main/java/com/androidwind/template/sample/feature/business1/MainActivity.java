package com.androidwind.template.sample.feature.business1;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.androidwind.template.sample.R;
import com.androidwind.template.sample.base.BaseActivity;
import com.androidwind.template.sample.constant.Constants;

import java.util.List;

import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ToastUtil;
import la.xiong.androidquick.ui.dialog.dialogactivity.CommonDialog;
import la.xiong.androidquick.ui.permission.EasyPermissions;

public class MainActivity extends BaseActivity {

    private String deniedPermsString;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        permissionsCheck();
    }

    private void permissionsCheck() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        performCodeWithPermission(1, Constants.RC_PERMISSION_PERMISSION_ACTIVITY, perms, new PermissionCallback() {
            @Override
            public void hasPermission(List<String> allPerms) {
                LogUtil.d(TAG, "allPerms:" + allPerms.toString());
                ToastUtil.showToast("Granted");
            }

            @Override
            public void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied) {
                LogUtil.d(TAG, "deniedPerms:" + deniedPerms.toString());
                if (hasPermanentlyDenied) {
                    StringBuilder denied = new StringBuilder();
                    if (deniedPerms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE) || deniedPerms.contains(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        denied.append("存储文件使用");
                    }
                    deniedPermsString = denied.toString();
                    EasyPermissions.goSettingsPermissions(MainActivity.this, 2, Constants.RC_PERMISSION_PERMISSION_ACTIVITY, Constants.RC_PERMISSION_BASE);
                }
            }

            @Override
            public void showDialog(int dialogType, final EasyPermissions.DialogCallback callback) {
                switch (dialogType) {
                    case 1:
                        getDialogBuilder(mContext).
                                setTitle(getString(R.string.app_name)).
                                setMessage(getString(R.string.dialog_storage_permission)).
                                setPositiveButton("OK").
                                setBtnClickCallBack(new CommonDialog.DialogBtnCallBack() {
                                    @Override
                                    public void onDialogButClick(boolean isConfirm) {
                                        if (isConfirm) {
                                            callback.onGranted();
                                        }
                                    }
                                }).show().setCancelable(false);
                        break;
                    case 2:
                        getDialogBuilder(mContext).
                                setTitle(getString(R.string.app_name)).
                                setMessage(getString(R.string.dialog_rationale_ask_again, deniedPermsString)).
                                setPositiveButton("Go to setting").
                                setBtnClickCallBack(new CommonDialog.DialogBtnCallBack() {
                                    @Override
                                    public void onDialogButClick(boolean isConfirm) {
                                        if (isConfirm) {
                                            callback.onGranted();
                                        }
                                    }
                                }).show().setCancelable(false);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.d(TAG, "requestCode:" + requestCode);
        if (requestCode == Constants.RC_PERMISSION_BASE) {
            permissionsCheck();
        }
    }
}
