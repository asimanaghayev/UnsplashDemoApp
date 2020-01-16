package com.temporary.unsplashdemo.ui.base;

import android.content.Context;

public interface BaseContractor {

    interface View {
        void showToast(String message);

        void showProgress();

        void hideProgress();

        Context getContext();
    }

    interface Presenter<V extends BaseContractor.View> {
        V getView();

        void setView(V view);
    }
}
