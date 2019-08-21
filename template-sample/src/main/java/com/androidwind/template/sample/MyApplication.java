package com.androidwind.template.sample;

import com.androidwind.template.sample.log.LogConfig;
import com.androidwind.template.sample.log.TinyLogProcessor;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MyApplication extends android.support.multidex.MultiDexApplication {

    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        //get application
        if (INSTANCE == null) {
            INSTANCE = this;
        }
        //log
        AndroidQuick
                // .configLog()
                .configLog(new TinyLogProcessor())
                .setEnable(BuildConfig.DEBUG)
                .setWritable(true)
                .setLevel(LogConfig.LOG_V);
        //make effective
        AndroidQuick.launch();
    }

    public static synchronized MyApplication getInstance() {
        return INSTANCE;
    }
}
