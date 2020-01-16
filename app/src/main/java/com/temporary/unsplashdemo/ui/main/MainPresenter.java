package com.temporary.unsplashdemo.ui.main;

import com.temporary.unsplashdemo.data.interactors.PhotosInteractor;
import com.temporary.unsplashdemo.data.network.BaseSubscriber;
import com.temporary.unsplashdemo.data.network.model.Photos;
import com.temporary.unsplashdemo.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter<V extends MainContractor.View> extends BasePresenter<V>
        implements MainContractor.Presenter<V> {

    private PhotosInteractor photosInteractor = new PhotosInteractor();

    @Inject
    public MainPresenter() {
    }

    @Override
    public void setView(V view) {
        super.setView(view);
        getFirstPage();
    }

    private void getFirstPage() {
        getNextPage(0);
        getNextPage(1);
    }

    public void getNextPage(int pageCount) {
        getView().showProgress();
//        photosInteractor.fetchPhotoList(new FetchPhotosCallBack(), pageCount);
        photosInteractor.fetchPhotoListRX(new FetchPhotosObserver(getView()), pageCount);
    }

    public class FetchPhotosObserver extends BaseSubscriber<List<Photos>> {

        public FetchPhotosObserver(MainContractor.View view) {
//            super(view);
        }

        @Override
        public void onComplete() {
            super.onComplete();
            getView().hideProgress();
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<Photos> photos) {
            super.onNext(photos);
            try {
                if (photos == null)
                    return;

                getView().addPhotos(photos);
            } catch (Exception e) {
//                HttpResponseHandlerUtil.onAPIError(view, null, e);
                e.printStackTrace();
            }
        }
    }

    public class FetchPhotosCallBack implements Callback<List<Photos>> {

        @Override
        public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
            if (response.isSuccessful())
                getView().addPhotos(response.body());
            else
                getView().showToast("Response Error");
        }

        @Override
        public void onFailure(Call<List<Photos>> call, Throwable t) {
            getView().showToast("API Error: " + t.getMessage());
        }
    }
}
