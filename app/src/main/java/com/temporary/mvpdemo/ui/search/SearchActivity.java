package com.temporary.mvpdemo.ui.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.temporary.mvpdemo.R;
import com.temporary.mvpdemo.data.network.model.User;
import com.temporary.mvpdemo.ui.base.BaseActivity;
import com.temporary.mvpdemo.util.PaginationListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchActivity extends BaseActivity implements SearchContractor.View {

    public static final String SEARCH_RESULT = "SEARCH_RESULTS";

    SearchContractor.Presenter presenter;

    @BindView(R.id.search_text)
    EditText search;

    @BindView(R.id.search_results)
    RecyclerView searchList;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private UserAdapter userAdapter;

    private StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL);

    private PaginationListener paginationListener = new PaginationListener(layoutManager) {
        @Override
        protected void loadMoreItems(int pageCount) {
            presenter.getSearchResult(search.getText(), pageCount);
        }

        @Override
        public boolean isLoading() {
            return progressBar.getVisibility() == View.VISIBLE;
        }
    };


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        userAdapter = new UserAdapter(this, new ArrayList<>(), user -> {
        });

        searchList.setLayoutManager(layoutManager);
        searchList.setAdapter(userAdapter);
        searchList.addOnScrollListener(paginationListener);

        presenter = new SearchPresenter(this);

        RxTextView.textChanges(search)
                .doOnNext(text -> userAdapter.clear())
                .filter(text -> text.length() >= 3)
                .debounce(150, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::subscriber);
    }

    private void subscriber(CharSequence charSequence) {
        userAdapter.clear();
        presenter.getSearchResult(charSequence, 1);
    }

    @Override
    public void addSearchList(List<User> users) {
        userAdapter.addUsers(users);
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void setLastPage(int lastPage) {
        paginationListener.setLastPage(lastPage);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(android.view.View.GONE);
    }
}
