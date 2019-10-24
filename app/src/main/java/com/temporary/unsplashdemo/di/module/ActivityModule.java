package com.temporary.unsplashdemo.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.temporary.unsplashdemo.di.ActivityContext;
import com.temporary.unsplashdemo.di.PerActivity;
import com.temporary.unsplashdemo.ui.main.MainContractor;
import com.temporary.unsplashdemo.ui.main.MainPresenter;
import com.temporary.unsplashdemo.ui.search.SearchActivity;
import com.temporary.unsplashdemo.ui.search.SearchContractor;
import com.temporary.unsplashdemo.ui.search.SearchPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    MainContractor.Presenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @PerActivity
    SearchContractor.Presenter provideSearchPresenter() {
        return new SearchPresenter();
    }
}