package com.temporary.unsplashdemo.data.network;


import com.temporary.unsplashdemo.data.network.model.Photos;
import com.temporary.unsplashdemo.data.network.model.Response;
import com.temporary.unsplashdemo.data.network.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {
    String CLIENT_ID = "client_id";
    String PAGE_COUNT = "page";
    String QUERY = "query";

    @GET("/photos/")
    Call<List<Photos>> getPhotos(
            @Query(CLIENT_ID) String clientId,
            @Query(PAGE_COUNT) int page
    );

    @GET("/photos/")
    Observable<List<Photos>> getPhotosRX(
            @Query(CLIENT_ID) String clientId,
            @Query(PAGE_COUNT) int page
    );

    @GET("/search/users/")
    Observable<Response<User>> searchUsersRX(
            @Query(CLIENT_ID) String access,
            @Query(QUERY) String query,
            @Query(PAGE_COUNT) int page
    );
}