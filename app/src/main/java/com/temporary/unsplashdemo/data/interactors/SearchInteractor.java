package com.temporary.unsplashdemo.data.interactors;

import com.temporary.unsplashdemo.BuildConfig;
import com.temporary.unsplashdemo.data.network.NetworkService;
import com.temporary.unsplashdemo.data.network.RetrofitClientInstance;
import com.temporary.unsplashdemo.data.network.model.Response;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchInteractor {

    private NetworkService service;

    public SearchInteractor() {
        service = RetrofitClientInstance.getRetrofitInstance().create(NetworkService.class);
    }

    public void getSearchResult(Observer<Response> observer, String query, int page) {
        service.searchUsersRX(BuildConfig.ACCESS_TOKEN, query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
