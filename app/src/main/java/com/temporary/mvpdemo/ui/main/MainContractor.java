package com.temporary.mvpdemo.ui.main;

import com.temporary.mvpdemo.data.network.model.Photos;
import com.temporary.mvpdemo.ui.base.BaseContractor;

import java.util.List;

public interface MainContractor {

    interface View extends BaseContractor.View{
        void addPhotos(List<Photos> photos);
    }

    interface Presenter {
        void setView(MainContractor.View view);

        void getNextPage(int pageCount);
    }

}
