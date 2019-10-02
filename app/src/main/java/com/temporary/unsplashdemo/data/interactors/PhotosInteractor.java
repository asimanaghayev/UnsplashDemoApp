package com.temporary.unsplashdemo.data.interactors;

import com.temporary.unsplashdemo.data.network.NetworkService;
import com.temporary.unsplashdemo.data.network.RetrofitClientInstance;
import com.temporary.unsplashdemo.data.network.model.Photos;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Callback;

import static com.temporary.unsplashdemo.root.AppConstants.accessToken;

public class PhotosInteractor {

    private NetworkService service;

    public PhotosInteractor() {
        service = RetrofitClientInstance.getRetrofitInstance().create(NetworkService.class);
    }

    public void fetchPhotoList(int pageCount, Callback<List<Photos>> callback) {
        service.getPhotos(accessToken, pageCount).enqueue(callback);

    }

    public void fetchPhotoListRX(Observer<Object> observer, int pageCount) {
        service.getPhotosRX(accessToken, pageCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
