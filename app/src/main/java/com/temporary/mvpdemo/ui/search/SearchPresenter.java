package com.temporary.mvpdemo.ui.search;

import com.temporary.mvpdemo.data.interactors.SearchInteractor;
import com.temporary.mvpdemo.data.network.model.Response;
import com.temporary.mvpdemo.ui.base.BasePresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchPresenter extends BasePresenter implements SearchContractor.Presenter {

    private SearchContractor.View view;

    private SearchInteractor searchInteractor = new SearchInteractor();

    SearchPresenter(SearchContractor.View view) {
        this.view = view;
    }

    public void getSearchResult(CharSequence query, int page) {
        view.showProgress();
        searchInteractor.getSearchResult(new SearchUsersObserver(view), query.toString(), page);
    }

    public class SearchUsersObserver implements Subscriber<Response>, Observer<Response> {

        SearchUsersObserver(SearchContractor.View view) {

        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onSubscribe(Subscription s) {

        }

        @Override
        public void onNext(Response results) {
            try {
                assert results != null;
                view.setLastPage(results.getTotalPages());
                view.addSearchList(results.getResults());
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {
            view.hideProgress();
        }
    }
}
