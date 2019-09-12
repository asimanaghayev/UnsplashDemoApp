package com.temporary.mvpdemo.ui.base;

public interface BaseContractor {

    interface View {
        void showToast(String message);

        void showProgress();

        void hideProgress();
    }

    interface Presenter {

    }
}
