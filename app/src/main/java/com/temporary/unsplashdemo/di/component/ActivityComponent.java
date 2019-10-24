package com.temporary.unsplashdemo.di.component;


import com.temporary.unsplashdemo.di.PerActivity;
import com.temporary.unsplashdemo.di.module.ActivityModule;
import com.temporary.unsplashdemo.ui.main.MainActivity;
import com.temporary.unsplashdemo.ui.search.SearchActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SearchActivity searchActivity);

    void inject(MainActivity mainActivity);
}