package com.liniocodingtest.network;

import com.liniocodingtest.models.Collection;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

import retrofit2.http.GET;

public interface CollectionService {
    @GET("collections")
    Observable<List<Collection>> getCollections();
}
