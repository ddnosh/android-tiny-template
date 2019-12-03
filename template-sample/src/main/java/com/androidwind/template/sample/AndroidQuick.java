package com.androidwind.template.sample;

import com.androidwind.template.sample.image.GlideProcessor;
import com.androidwind.template.sample.image.IImageProcessor;
import com.androidwind.template.sample.image.ImageConfig;
import com.androidwind.template.sample.image.TinyImageProcessor;
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
    private static IImageProcessor mIImageProcessor;
    private static ImageConfig mImageConfig;

    public static ImageConfig getImageConfig() {
        return mImageConfig;
    }

    //配置生效
    public static void launch() {
        //log
        if (mILogProcessor == null) {
            mILogProcessor = new DefaultLogProcessor();
        }
        mILogProcessor.init(mLogConfig);
        //image
        if (mIImageProcessor == null) {
            mIImageProcessor = new GlideProcessor();
        }
        mIImageProcessor.init(MyApplication.getInstance(), mImageConfig);
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

    //image调用入口
    public static <T extends IImageProcessor> T imageProcessor() {
        if (mIImageProcessor != null) {
            return (T) mIImageProcessor;
        }
        // return (T) new GlideProcessor();
        return (T) new TinyImageProcessor();
    }

    //image配置入口
    public static ImageConfig configImage() {
        return configImage(null);
    }

    //image配置入口
    public static ImageConfig configImage(IImageProcessor processor) {
        if (processor == null) {
            mImageConfig = new ImageConfig();
        }
        else {
            mIImageProcessor = processor;
            mImageConfig = new ImageConfig();
        }
        return mImageConfig;
    }
}
