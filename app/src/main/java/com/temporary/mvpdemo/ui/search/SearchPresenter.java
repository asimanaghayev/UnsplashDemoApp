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

    public SearchPresenter(SearchContractor.View view) {
        this.view = view;
    }

    public SearchContractor.View getView() {
        return view;
    }

    public void setView(SearchContractor.View view) {
        this.view = view;
    }

    public void searchListener(CharSequence query) {
        searchInteractor.getSearchResult(new SearchUsersObserver(view), query.toString());
    }

    public class SearchUsersObserver implements Subscriber<Response>, Observer<Response> {

        public SearchUsersObserver(SearchContractor.View view) {

        }


        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onSubscribe(Subscription s) {

        }

        @Override
        public void onNext(Response searchUsers) {
            try {
                if (searchUsers.getResults() != null)
                    view.setSearchList(searchUsers.getResults());
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    }
}
