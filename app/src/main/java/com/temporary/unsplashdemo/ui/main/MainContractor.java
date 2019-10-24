package com.temporary.unsplashdemo.ui.main;

import com.temporary.unsplashdemo.data.network.model.Photos;
import com.temporary.unsplashdemo.ui.base.BaseContractor;
import com.temporary.unsplashdemo.ui.base.BasePresenter;

import java.util.List;

public interface MainContractor {

    interface View extends BaseContractor.View{
        void addPhotos(List<Photos> photos);
    }

    interface Presenter<V extends MainContractor.View> extends BaseContractor.Presenter<V> {
        void getNextPage(int pageCount);
    }

}
