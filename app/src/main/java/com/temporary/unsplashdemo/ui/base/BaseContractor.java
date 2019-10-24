package com.temporary.unsplashdemo.ui.base;

public interface BaseContractor {

    interface View {
        void showToast(String message);

        void showProgress();

        void hideProgress();
    }

    interface Presenter<V extends BaseContractor.View> {
        V getView();

        void setView(V view);
    }
}
