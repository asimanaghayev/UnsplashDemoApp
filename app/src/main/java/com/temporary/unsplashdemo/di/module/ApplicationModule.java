package com.temporary.unsplashdemo.di.module;

import android.app.Application;
import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.temporary.unsplashdemo.BuildConfig;
import com.temporary.unsplashdemo.data.network.NetworkService;
import com.temporary.unsplashdemo.data.prefs.PreferencesHelper;
import com.temporary.unsplashdemo.data.prefs.PreferencesManager;
import com.temporary.unsplashdemo.di.ApiInfo;
import com.temporary.unsplashdemo.di.ApplicationContext;
import com.temporary.unsplashdemo.di.DatabaseInfo;
import com.temporary.unsplashdemo.di.PreferenceInfo;
import com.temporary.unsplashdemo.root.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.ACCESS_TOKEN;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesManager preferencesManager) {
        return preferencesManager;
    }

    @Provides
    @Singleton
    FirebaseAnalytics provideFirebase(Context context) {
        return FirebaseAnalytics.getInstance(context);
    }

    @Provides
    public OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(chain -> {
            Request request = chain.request();
            return chain.proceed(request);
        }).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public NetworkService provideApiService() {
        return provideRetrofit(BuildConfig.BASE_URL, provideClient()).create(NetworkService.class);
    }
}
