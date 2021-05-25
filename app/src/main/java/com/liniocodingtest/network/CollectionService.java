package com.liniocodingtest.network;

import com.liniocodingtest.models.Collection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CollectionService {
    @GET("collections")
    Call<List<Collection>> getCollections();
}
