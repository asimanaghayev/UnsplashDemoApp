package com.temporary.unsplashdemo.data.network;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.temporary.unsplashdemo.data.network.model.Error;
import com.temporary.unsplashdemo.ui.base.BaseContractor;
import com.temporary.unsplashdemo.ui.search.SearchContractor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.Response;

public abstract class BaseSubscriber<R extends Object> implements Subscriber<R>, Observer<R> {

    private BaseContractor.View view;

    public BaseSubscriber() {
    }

    public BaseSubscriber(SearchContractor.View view) {
        this.view = view;
    }

    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(R r) {

    }

    @Override
    public void onError(Throwable e) {
        if (view == null) {
            return;
        }
//
        try {
            Gson gson = new Gson();
            if (e instanceof HttpException) {
                Response response = ((HttpException) e).response();
                ResponseBody body = response.errorBody();
                if (response.code() == 403) {
                    Error error = new Error();
                    List<String> errors = new ArrayList<>();
                    errors.add(response.errorBody().string());
                    error.setErrors(errors);
                } else {
                    Error error = gson.fromJson(body.string(), Error.class);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
        if (view == null) {
            return;
        }

//        view.hideProgress();
    }
}
