package com.temporary.mvpdemo.ui.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.temporary.mvpdemo.R;
import com.temporary.mvpdemo.data.network.model.User;
import com.temporary.mvpdemo.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchActivity extends BaseActivity implements SearchContractor.View {
    SearchContractor.Presenter presenter;

    @BindView(R.id.search_text)
    EditText search;

    @BindView(R.id.search_results)
    RecyclerView searchList;

    private UserAdapter userAdapter;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        userAdapter = new UserAdapter(this, new ArrayList<>(), new UserItemClickListener() {
            @Override
            public void onItemClick(User user) {

            }
        });
        searchList.setLayoutManager(new LinearLayoutManager(this));
        searchList.setAdapter(userAdapter);

        presenter = new SearchPresenter(this);

        RxTextView.textChanges(search)
                .doOnNext(text -> setSearchList(new ArrayList<>()))
                .filter(text -> text.length() >= 3)
                .debounce(150, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(presenter::searchListener);
    }

    public void setSearchList(List<User> users) {
        userAdapter.updateUsers(users);
        userAdapter.notifyDataSetChanged();
    }
}
