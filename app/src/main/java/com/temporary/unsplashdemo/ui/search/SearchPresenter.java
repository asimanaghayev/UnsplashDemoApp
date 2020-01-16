package com.temporary.unsplashdemo.ui.search;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.temporary.unsplashdemo.data.interactors.SearchInteractor;
import com.temporary.unsplashdemo.data.network.BaseSubscriber;
import com.temporary.unsplashdemo.data.network.model.Response;
import com.temporary.unsplashdemo.ui.base.BasePresenter;
import com.temporary.unsplashdemo.util.FirebaseEvent;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class SearchPresenter<V extends SearchContractor.View> extends BasePresenter<V>
        implements SearchContractor.Presenter<V> {

    private SearchInteractor searchInteractor = new SearchInteractor();

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    public SearchPresenter() {

    }

    @Override
    public void setView(V view) {
        super.setView(view);
//        firebaseAnalytics = FirebaseAnalytics.getInstance(view.getContext());
    }

    public void getSearchResult(CharSequence query, int page) {
        getView().showProgress();
        searchInteractor.getSearchResult(new SearchUsersObserver(getView()), query.toString(), page);
        firebaseAnalytics.logEvent(FirebaseEvent.SEARCH_REQUEST, null);
    }

    public class SearchUsersObserver extends BaseSubscriber<Response> {

        SearchUsersObserver(SearchContractor.View view) {

        }

        @Override
        public void onSubscribe(Disposable d) {

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
