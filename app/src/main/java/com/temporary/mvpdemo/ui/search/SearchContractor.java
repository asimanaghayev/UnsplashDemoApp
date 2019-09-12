package com.temporary.mvpdemo.ui.search;

import com.temporary.mvpdemo.data.network.model.User;
import com.temporary.mvpdemo.ui.base.BaseContractor;

import java.util.List;

public interface SearchContractor {

    interface View extends BaseContractor.View {
        void setSearchList(List<User> users);
    }

    interface Presenter {
        void searchListener(CharSequence query);
    }

}
