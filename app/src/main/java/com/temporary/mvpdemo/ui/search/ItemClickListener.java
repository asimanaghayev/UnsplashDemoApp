package com.temporary.mvpdemo.ui.search;

import com.temporary.mvpdemo.data.network.model.User;

public interface ItemClickListener<T> {

    void onItemClick(User user);

}
