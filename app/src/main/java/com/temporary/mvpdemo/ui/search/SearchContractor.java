package com.temporary.mvpdemo.ui.search;

import com.temporary.mvpdemo.data.network.model.User;
import com.temporary.mvpdemo.ui.base.BaseContractor;

import java.util.List;

public interface SearchContractor {

    interface View extends BaseContractor.View {
        void addSearchList(List<User> users);

        void setLastPage(int LastPage);
    }

    interface Presenter {
        void getSearchResult(CharSequence query, int page);
    }

}
