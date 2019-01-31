package com.androidwind.template.sample.injector.component;

import com.androidwind.template.sample.injector.FragmentScope;
import com.androidwind.template.sample.injector.module.FragmentModule;

import dagger.Component;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

//    void inject(Network1Fragment network1Fragment);
//    void inject(MvpFragment mvpFragment);
}
