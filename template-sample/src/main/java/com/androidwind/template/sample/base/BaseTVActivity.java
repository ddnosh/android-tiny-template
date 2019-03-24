package com.androidwind.template.sample.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.androidwind.template.sample.MyApplication;
import com.androidwind.template.sample.injector.component.ActivityComponent;
import com.androidwind.template.sample.injector.component.DaggerActivityComponent;
import com.androidwind.template.sample.injector.module.ActivityModule;

import javax.inject.Inject;
import la.xiong.androidquick.tool.LogUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTVActivity<T extends BasePresenter, V extends ViewDataBinding> extends BaseActivity {
    @Inject
    public T mPresenter;
    protected Context mContext;
    protected String TAG = "BaseTVFragment";

    public V getBinding() {
        return (V) binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreate:" + getClass().getSimpleName());
        //dagger2
        initInjector();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy:" + getClass().getSimpleName());
    }

    protected void initInjector() {

    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
