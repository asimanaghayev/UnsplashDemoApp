package com.temporary.mvpdemo.data.interactors;

import com.temporary.mvpdemo.data.network.NetworkService;
import com.temporary.mvpdemo.data.network.RetrofitClientInstance;
import com.temporary.mvpdemo.data.network.model.Response;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.temporary.mvpdemo.root.AppConstants.accessToken;

public class SearchInteractor {

    private NetworkService service;

    public SearchInteractor() {
        service = RetrofitClientInstance.getRetrofitInstance().create(NetworkService.class);
    }

    public void getSearchResult(Observer<Response> observer, String query) {
        service.searchUsersRX(accessToken, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
