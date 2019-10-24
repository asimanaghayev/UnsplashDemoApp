package com.temporary.unsplashdemo.ui.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.temporary.unsplashdemo.di.component.ActivityComponent;
import com.temporary.unsplashdemo.di.component.DaggerActivityComponent;
import com.temporary.unsplashdemo.di.module.ActivityModule;
import com.temporary.unsplashdemo.root.UnsplashDemoApp;

public class BaseActivity extends AppCompatActivity implements BaseContractor.View {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((UnsplashDemoApp) getApplication()).getAppComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }


    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}

