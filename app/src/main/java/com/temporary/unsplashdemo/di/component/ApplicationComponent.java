package com.temporary.unsplashdemo.di.component;

import android.app.Application;
import android.content.Context;

import com.temporary.unsplashdemo.di.ApplicationContext;
import com.temporary.unsplashdemo.di.module.ApplicationModule;
import com.temporary.unsplashdemo.root.UnsplashDemoApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(UnsplashDemoApp app);

    @ApplicationContext
    Context context();

    Application application();

//    DataManager getDataManager();
}
