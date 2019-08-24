package com.androidwind.template.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidwind.log.TinyLog;
import com.androidwind.permission.OnPermission;
import com.androidwind.permission.Permission;
import com.androidwind.permission.TinyPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //permission
        TinyPermission.start(this)
                .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean permanent) {
                        TinyLog.config()
                                .setEnable(BuildConfig.DEBUG).setWritable(false).setLogLevel(TinyLog.LOG_I);
                    }
                });
    }

    public void clickLog(View view) {
        AndroidQuick.logProcessor().loadD("this is tiny log");
    }

    public void clickImage(View view) {
        mImageView = findViewById(R.id.iv_demo);
        AndroidQuick
                .imageProcessor()
                .loadNet("https://hbimg.huabanimg.com/5cdceea9b410bc159f06d6593eaceed3c82d0f4c302dc-qqGjbf_fw658", mImageView);
    }
}
