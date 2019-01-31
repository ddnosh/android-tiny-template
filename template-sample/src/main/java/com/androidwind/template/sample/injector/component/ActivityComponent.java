package com.androidwind.template.sample.injector.component;

import android.app.Activity;
import android.content.Context;

import com.androidwind.template.sample.injector.ActivityScope;
import com.androidwind.template.sample.injector.module.ActivityModule;

import dagger.Component;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();
    Context getContext();

//    void inject(NetworkActivity networkActivity);
//    void inject(MvpActivity mvpActivity);
}
