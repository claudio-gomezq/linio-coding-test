package com.liniocodingtest.di;

import android.content.Context;

import com.liniocodingtest.network.CollectionClient;
import com.liniocodingtest.network.CollectionService;
import com.liniocodingtest.network.MockClientInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    public String provideBaseUrl() {
        return "http://test-api.com";
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(@ApplicationContext Context context) {
        return new OkHttpClient
                .Builder()
                .addInterceptor(new MockClientInterceptor(context))
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitClient(OkHttpClient client, String baseURL) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public CollectionService provideCollectionService(Retrofit retrofit) {
        return retrofit.create(CollectionService.class);
    }

    @Provides
    @Singleton
    public CollectionClient provideCollectionClient(CollectionService collectionService) {
        return new CollectionClient(collectionService);
    }
}
