package com.temporary.unsplashdemo.ui.search;

import com.temporary.unsplashdemo.data.network.model.User;
import com.temporary.unsplashdemo.ui.base.BaseContractor;
import com.temporary.unsplashdemo.ui.main.MainContractor;

import java.util.List;

public interface SearchContractor {

    interface View extends BaseContractor.View {
        void addSearchList(List<User> users);

        void setLastPage(int LastPage);
    }

    interface Presenter<V extends SearchContractor.View> extends BaseContractor.Presenter<V> {
        void getSearchResult(CharSequence query, int page);
    }
}
