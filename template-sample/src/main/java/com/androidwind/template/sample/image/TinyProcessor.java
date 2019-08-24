package com.androidwind.template.sample.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.androidwind.http.HttpUtil;
import com.androidwind.http.TinyHttp;
import com.androidwind.http.callback.BitmapHttpCallBack;
import com.androidwind.task.Task;
import com.androidwind.task.TinyTaskExecutor;

import java.io.File;

import la.xiong.androidquick.tool.StringUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TinyProcessor implements IImageProcessor {

    private ImageConfig mImageConfig;

    @Override
    public void init(Context context, ImageConfig imageConfig) {
        mImageConfig = imageConfig;
    }

    @Override
    public void loadNet(String url, ImageView imageView) {
        if (!StringUtil.isEmpty(url)) {
            TinyHttp.get()
                    .url(url)
                    .callback(new BitmapHttpCallBack() {
                        @Override
                        public void OnMainSuccess(final Bitmap bitmap) {
                            TinyTaskExecutor.execute(new Task<Bitmap>() {
                                @Override
                                public Bitmap doInBackground() {
                                    return bitmap;
                                }

                                @Override
                                public void onSuccess(Bitmap bitmap) {
                                    imageView.setImageBitmap(bitmap);
                                }

                                @Override
                                public void onFail(Throwable throwable) {

                                }
                            });
                        }

                        @Override
                        public void OnMainFail(String errorMessage) {

                        }
                    }).execute();
        }
    }

    @Override
    public void loadNet(String url, ImageView imageView, LoadOption loadOption) {

    }

    @Override
    public void loadRes(int resId, ImageView imageView) {

    }

    @Override
    public void loadRes(int resId, ImageView imageView, LoadOption loadOption) {

    }

    @Override
    public void loadAsset(String assetName, ImageView imageView) {

    }

    @Override
    public void loadAsset(String assetName, ImageView imageView, LoadOption loadOption) {

    }

    @Override
    public void loadFile(File file, ImageView imageView) {

    }

    @Override
    public void loadFile(File file, ImageView imageView, LoadOption loadOption) {

    }

    @Override
    public void preLoad(String url) {

    }

    @Override
    public void getBitmap(Context context, String url, ImageListener<Bitmap> imageListener) {

    }

    @Override
    public void downLoadImage(Context context, String url, File saveFile, ImageListener<File> imageListener) {

    }

    @Override
    public void clearMemoryCache() {

    }

    @Override
    public void clearDiskCache() {

    }
}
