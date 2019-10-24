package com.temporary.unsplashdemo.ui.base;

public class BasePresenter<V extends BaseContractor.View> implements BaseContractor.Presenter<V> {

    private V view;

    public void setView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }


}
