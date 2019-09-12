package com.temporary.mvpdemo.ui.main;

import com.temporary.mvpdemo.data.interactors.PhotosInteractor;
import com.temporary.mvpdemo.data.network.BaseSubscriber;
import com.temporary.mvpdemo.data.network.model.Photos;
import com.temporary.mvpdemo.ui.base.BasePresenter;
import com.temporary.mvpdemo.util.DataConverterUtil;

import java.util.List;

import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends BasePresenter implements MainContractor.Presenter {

    private MainContractor.View view;

    private PhotosInteractor photosInteractor = new PhotosInteractor();

    public MainPresenter(MainContractor.View view) {
        this.view = view;
        getFirstPage();
    }

    public MainContractor.View getView() {
        return view;
    }

    public void setView(MainContractor.View view) {
        this.view = view;
    }

    private void getFirstPage() {
        getNextPage(1);
    }

    public void getNextPage(int pageCount) {
        view.showProgress();
//        photosInteractor.fetchPhotoList(new FetchPhotosCallBack(), pageCount);
        photosInteractor.fetchPhotoListRX(new FetchPhotosObserver(view), pageCount);
    }

    public class FetchPhotosObserver extends BaseSubscriber<Object> {

        public FetchPhotosObserver(MainContractor.View view) {
//            super(view);
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.hideProgress();
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object o) {
            super.onNext(o);
            try {
                List<Photos> photos = DataConverterUtil.parsePhotoSuccessResponse(o);

                if (photos == null)
                    return;

                view.addPhotos(photos);
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
                view.addPhotos(response.body());
            else
                view.showToast("Response Error");
        }

        @Override
        public void onFailure(Call<List<Photos>> call, Throwable t) {
            view.showToast("API Error: " + t.getMessage());
        }
    }
}
