package com.temporary.unsplashdemo.ui.search;

import com.temporary.unsplashdemo.data.network.model.User;

public interface ItemClickListener<T> {

    void onItemClick(User user);

}
