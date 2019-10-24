package com.temporary.unsplashdemo.ui.search;

import com.temporary.unsplashdemo.data.interactors.SearchInteractor;
import com.temporary.unsplashdemo.data.network.model.Response;
import com.temporary.unsplashdemo.ui.base.BasePresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchPresenter<V extends SearchContractor.View> extends BasePresenter<V>
        implements SearchContractor.Presenter<V> {

    private SearchInteractor searchInteractor = new SearchInteractor();

    @Inject
    public SearchPresenter() {

    }

    public void getSearchResult(CharSequence query, int page) {
        getView().showProgress();
        searchInteractor.getSearchResult(new SearchUsersObserver(getView()), query.toString(), page);
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
                getView().setLastPage(results.getTotalPages());
                getView().addSearchList(results.getResults());
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {
            getView().hideProgress();
        }
    }
}
