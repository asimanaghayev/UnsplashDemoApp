package com.temporary.unsplashdemo.root;

import android.app.Application;

import com.temporary.unsplashdemo.di.component.ApplicationComponent;
import com.temporary.unsplashdemo.di.component.DaggerApplicationComponent;
import com.temporary.unsplashdemo.di.module.ApplicationModule;

public class UnsplashDemoApp extends Application {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getAppComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }
}
