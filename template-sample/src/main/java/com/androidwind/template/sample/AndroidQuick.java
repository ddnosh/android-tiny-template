package com.androidwind.template.sample;

import com.androidwind.template.sample.log.DefaultLogProcessor;
import com.androidwind.template.sample.log.ILogProcessor;
import com.androidwind.template.sample.log.LogConfig;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class AndroidQuick {

    private static ILogProcessor mILogProcessor;
    private static LogConfig mLogConfig;

    //配置生效
    public static void launch() {
        //log
        if (mILogProcessor == null) {
            mILogProcessor = new DefaultLogProcessor();
        }
        mILogProcessor.init(mLogConfig);
    }

    //log调用入口
    public static <T extends ILogProcessor> T logProcessor() {
        if (mILogProcessor != null) {
            return (T) mILogProcessor;
        }
        return (T) new DefaultLogProcessor();
    }

    //log配置入口
    public static LogConfig configLog() {
        return configLog(null);
    }

    //log配置入口
    public static LogConfig configLog(ILogProcessor processor) {
        if (processor == null) {
            mLogConfig = new LogConfig();
        }
        else {
            mILogProcessor = processor;
            mLogConfig = new LogConfig();
        }
        return mLogConfig;
    }
}
