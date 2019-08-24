package com.androidwind.template.sample;

import com.androidwind.template.sample.image.FrescoProcessor;
import com.androidwind.template.sample.image.TinyProcessor;
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
        //image
        //图片加载模块（可替换，demo演示了如何使用Fresco替换默认的Glide）
        AndroidQuick.configImage(new TinyProcessor())//配置默认的Glide
                // AndroidQuick.configImage()//配置默认的Glide
                // AndroidQuick.configImage(new FrescoProcessor()) //传入Fresco管理者进行替换，注意，替换为Fresco后，相关的ImageView需换成SimpleDraweeView
                .setLoadingResId(R.mipmap.ic_image_loading)//设置“加载中”状态时显示的图片
                .setErrorResId(R.mipmap.ic_image_loading);//设置“加载失败”状态时显示的图片
//                .setIsShowTransition(true)//设置是否开启状态切换时的过渡动画，默认false（有些自定义控件如果开启了过度动画，会加载不出图片）
//                .setIsUseOkhttp(false)//设置是否使用okhttp3作为网络组件，默认true
//                .setIsUseMemoryCache(false)//设置是否使用内存缓存，默认true
//                .setMemoryCacheSize(size)//设置内存缓存大小，不建议设置，使用框架默认设置的大小即可
//                .setBitmapPoolSize(size)//设置Bitmap池大小，设置内存缓存大小的话一般这个要一起设置，不建议设置，使用框架默认设置的大小即可
//                .setIsUseDiskCache(false)//设置是否使用磁盘缓存，默认true
//                .setDiskCacheFile(file)//设置具体的磁盘缓存地址，传入的file需为文件夹
//                .setDiskCacheSize(200*1024*1024)//设置磁盘缓存大小，单位byte，默认250M
//                .setIsDiskCacheExternal(true);//设置磁盘缓存地址是否在外部存储中，默认false
        //make effective
        AndroidQuick.launch();
    }

    public static synchronized MyApplication getInstance() {
        return INSTANCE;
    }
}
